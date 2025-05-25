package org.iesvdm.proyecto_servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findAll(Pageable pageable);
    List<Producto> findByIdOrNombreOrProveedorIdOrCategoriaIdOrderByIdAsc(Long id, String nombre, Long proveedorId, Long categoriaId);
    Page<Producto> findByNombreContaining(String nombre, Pageable pageable);
}
