package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.ProveedorRepository;
import org.iesvdm.proyecto_servidor.model.domain.Proveedor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProveedorService implements BasicServiceInterface<Proveedor> {

    private final ProveedorRepository proveedorRepository;

    @Override
    public List<Proveedor> all() {
        return this.proveedorRepository.findAll();
    }

    @Override
    public Page<Proveedor> all(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Proveedor saveOrGetIfExists(Proveedor proveedor) {
        if (proveedor == null) throw new IllegalArgumentException("El proveedor no puede ser null");

        return proveedor.getId() == null ?
                proveedorRepository.save(proveedor) :
                proveedorRepository.findById(proveedor.getId())
                        .orElseThrow(() -> new EntityNotFoundException(proveedor.getId(), Proveedor.class));
    }

    @Override
    public Proveedor one(Long id) {
        return this.proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Proveedor.class));
    }

    @Override
    public Proveedor replace(Long id, Proveedor proveedor) {
        return this.proveedorRepository.findById(id).map( p -> {
                    if (id.equals(proveedor.getId())) return this.proveedorRepository.save(proveedor);
                    else throw new NotCouplingIdException(id, proveedor.getId(), Proveedor.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Proveedor.class));
    }

    @Override
    public void delete(Long id) {
        this.proveedorRepository.findById(id).map(p -> {
                    this.proveedorRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Proveedor.class));
    }

}
