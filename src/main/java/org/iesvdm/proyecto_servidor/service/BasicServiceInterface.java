package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.model.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasicServiceInterface<T> {

    List<T> all();

    Page<T> all(Pageable pageable);

    @Transactional
    T saveOrGetIfExists(T t);

    T one(Long id);

    T replace(Long id, T t);

    void delete(Long id);

}
