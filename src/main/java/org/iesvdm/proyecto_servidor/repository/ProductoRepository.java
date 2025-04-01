package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.domain.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Page<Producto> findAll(Pageable pageable);

    List<Producto> findByIdOrNombreOrProveedorIdOrCategoriaIdOrderByIdAsc(Long id, String nombre, Long proveedorId, Long categoriaId);

}
