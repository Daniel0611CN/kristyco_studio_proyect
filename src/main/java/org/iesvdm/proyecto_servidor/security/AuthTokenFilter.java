package org.iesvdm.proyecto_servidor.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.iesvdm.proyecto_servidor.service.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
import org.iesvdm.proyecto_servidor.model.record.TokenData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import org.slf4j.Logger;
import java.util.Date;
import lombok.NonNull;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Value("${time.token}")
    private long tiempoToken;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    private String parseToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = parseToken(request);
        if (token != null) {
            TokenData tokenData = tokenUtils.parseTokenData(token);

            long currentTime = new Date().getTime();
            long creationTime = tokenData.timestamp();

            if (currentTime - creationTime < tiempoToken) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(tokenData.username());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
