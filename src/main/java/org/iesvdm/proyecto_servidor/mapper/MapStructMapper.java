package org.iesvdm.proyecto_servidor.mapper;

import org.iesvdm.proyecto_servidor.dto.DTORegister;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "nombre", source = "dto.username")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    @Mapping(target = "pedidos", ignore = true)
    @Mapping(target = "roles", ignore = true)
    Usuario registroToUsuario(DTORegister dto, @Context PasswordEncoder passwordEncoder);

}
