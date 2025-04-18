package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.model.domain.Usuario;

import java.util.List;

public interface BasicServiceInterface<T> {

    List<T> all();

    T saveOrGetIfExists(T t);

    T one(Long id);

    T replace(Long id, T t);

    void delete(Long id);

}
