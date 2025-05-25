package org.iesvdm.proyecto_servidor.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByNombre(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuario no encontrado con username: %s", username)));

        log.info("Usuario cargado: {}", user);
        return UserDetailsImpl.build(user);
    }
}
