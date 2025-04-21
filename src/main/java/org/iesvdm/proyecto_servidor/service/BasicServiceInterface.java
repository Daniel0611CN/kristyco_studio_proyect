package org.iesvdm.proyecto_servidor.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import jakarta.transaction.Transactional;
import java.util.List;

public interface BasicServiceInterface<T> {

    List<T> all();

    Page<T> all(Pageable pageable);

    T saveOrGetIfExists(T t);

    T one(Long id);

    T replace(Long id, T t);

    void delete(Long id);

}
