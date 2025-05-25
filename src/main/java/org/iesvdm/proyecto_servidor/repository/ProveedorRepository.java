package org.iesvdm.proyecto_servidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.iesvdm.proyecto_servidor.model.domain.Proveedor;
import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    @Query("SELECT p.productosProveedor FROM Proveedor p WHERE p.id = :proveedorId")
    List<Producto> getProductosByProveedor_Id(@Param("proveedorId") Long proveedorId);
}
