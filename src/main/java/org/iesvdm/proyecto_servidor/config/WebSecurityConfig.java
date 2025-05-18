package org.iesvdm.proyecto_servidor.config;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.ProviderManager;
import org.iesvdm.proyecto_servidor.security.AuthEntryPointToken;
import org.iesvdm.proyecto_servidor.security.AuthTokenFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private final AuthEntryPointToken unauthorizedHandler;

    public WebSecurityConfig(AuthEntryPointToken unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }

    @Bean
    public AuthTokenFilter authenticationTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(unauthorizedHandler) )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                            .requestMatchers("/api/v1/auth/**").permitAll()
                            .requestMatchers(HttpMethod.GET,"/api/v1/confirmation_token/**").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/api/v1/confirmation_token/**").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority("ROL_ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/api/v1/**").hasAuthority("ROL_ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority("ROL_ADMIN")
                            .requestMatchers(HttpMethod.GET, "/api/v1/**").hasAnyAuthority("ROL_ADMIN", "ROL_USER")
                            .anyRequest().authenticated();
                })
                // .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();

        //https://stackoverflow.com/questions/59302026/spring-security-why-adding-the-jwt-filter-before-usernamepasswordauthenticatio
        //http.addFilterAfter(authenticationJwtTokenFilter(), ExceptionTranslationFilter.class);
    }

}
