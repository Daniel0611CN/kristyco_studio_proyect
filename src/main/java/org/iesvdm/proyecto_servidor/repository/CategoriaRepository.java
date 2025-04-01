package org.iesvdm.proyecto_servidor.repository;

import org.iesvdm.proyecto_servidor.domain.Categoria;
import org.iesvdm.proyecto_servidor.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT p FROM Pedido p " +
            "WHERE EXISTS (SELECT 1 FROM p.productos pd " +
            "              JOIN pd.categoria c " +
            "              WHERE c.id = :categoriaId)")
    Set<Pedido> getPedidosHasProductoByCategoriaId(@Param("categoriaId") Long id);



}