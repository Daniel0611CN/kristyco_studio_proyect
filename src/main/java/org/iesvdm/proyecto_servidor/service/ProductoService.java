package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.model.domain.Categoria;
import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.iesvdm.proyecto_servidor.model.domain.Proveedor;
import org.iesvdm.proyecto_servidor.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProveedorService proveedorService;

    public Page<Producto> all(Pageable pageable) { return this.productoRepository.findAll(pageable); }

    @Transactional
    public Producto save(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("El producto no puede ser null");

        if (producto.getId() == null) {
            Categoria categoria = producto.getCategoria();
            categoriaService.saveOrGetIfExists(categoria);
            categoria.getProductosCategoria().add(producto);
            producto.setCategoria(categoria);

            Proveedor proveedor = producto.getProveedor();
            proveedorService.saveOrGetIfExists(proveedor);
            proveedor.getProductosProveedor().add(producto);
            producto.setProveedor(proveedor);

            return productoRepository.save(producto);
        } else {
            return productoRepository.findById(producto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(producto.getId(), Producto.class));
        }
    }

    public Producto one(Long id) {
        return this.productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Producto.class));
    }

    public Optional<Producto> oneOptional(Long id) {
        return this.productoRepository.findById(id);
    }

    public Producto replace(Long id, Producto producto) {
        return this.productoRepository.findById(id).map( p -> {
                    if (id.equals(producto.getId())) return this.productoRepository.save(producto);
                    else throw new NotCouplingIdException(id, producto.getId(), Producto.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Producto.class));
    }

    public void delete(Long id) {
        this.productoRepository.findById(id).map(p -> {
                    this.productoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Producto.class));
    }

    public List<Producto> filterByIdOrNombreOrProvIdOrCatId(Long id, String nombre, Long proveedorId, Long categoriaId) {
        if (id != null) {
            return productoRepository.findByIdOrNombreOrProveedorIdOrCategoriaIdOrderByIdAsc(id, null, null, null);
        } else if (nombre != null) {
            return productoRepository.findByIdOrNombreOrProveedorIdOrCategoriaIdOrderByIdAsc(null, nombre, null, null);
        } else if (proveedorId != null) {
            return productoRepository.findByIdOrNombreOrProveedorIdOrCategoriaIdOrderByIdAsc(null, null, proveedorId, null);
        } else {
            return productoRepository.findByIdOrNombreOrProveedorIdOrCategoriaIdOrderByIdAsc(null, null, null, categoriaId);
        }
    }

}
