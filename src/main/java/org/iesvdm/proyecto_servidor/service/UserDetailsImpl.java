package org.iesvdm.proyecto_servidor.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String email;

    private Long telefono;

    private String direccion;

    @JsonIgnore
    private String password;

    private Boolean locked = false;

    private Boolean enabled = false;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl( Long id, String nombre, String apellido1, String apellido2, String email, String direccion,
                            Long telefono, String password, Boolean locked, Boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getRol().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getNombre(),
                user.getApellido1(),
                user.getApellido2(),
                user.getEmail(),
                user.getDireccion(),
                user.getTelefono(),
                user.getPassword(),
                user.getLocked(),
                user.getEnabled(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public Long getTelefono() {
        return telefono;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) object;
        return Objects.equals(id, that.id);
    }

}
