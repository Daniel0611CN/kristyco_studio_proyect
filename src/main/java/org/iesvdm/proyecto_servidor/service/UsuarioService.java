package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.UsuarioRepository;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioService implements BasicServiceInterface<Usuario> {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> all() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> all(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Usuario saveOrGetIfExists(Usuario usuario) {
        if (usuario == null) throw new IllegalArgumentException("El usuario no puede ser null");

        return usuario.getId() == null ?
                usuarioRepository.save(usuario) :
                usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new EntityNotFoundException(usuario.getId(), Usuario.class));
    }

    @Override
    public Usuario one(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Usuario.class));
    }

    @Override
    public Usuario replace(Long id, Usuario usuario) {
        return this.usuarioRepository.findById(id).map( p -> {
                    if (id.equals(usuario.getId())) return this.usuarioRepository.save(usuario);
                    else throw new NotCouplingIdException(id, usuario.getId(), Usuario.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Usuario.class));
    }

    @Override
    public void delete(Long id) {
        this.usuarioRepository.findById(id).map(p -> {
                    this.usuarioRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Usuario.class));
    }

    public Usuario oneEmail(String email) {
        return this.usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con email " + email));
    }

    public void changePassword(Long id, String pswd) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id " + id));
        usuario.setPassword(pswd);
        usuarioRepository.save(usuario);
    }

}
