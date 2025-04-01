package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.domain.Categoria;
import org.iesvdm.proyecto_servidor.domain.Proveedor;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<Proveedor> all() { return this.proveedorRepository.findAll(); }

    @Transactional
    public Proveedor saveOrGetIfExists(Proveedor proveedor) {
        if (proveedor == null) throw new IllegalArgumentException("El proveedor no puede ser null");

        return proveedor.getId() == null ?
                proveedorRepository.save(proveedor) :
                proveedorRepository.findById(proveedor.getId())
                        .orElseThrow(() -> new EntityNotFoundException(proveedor.getId(), Proveedor.class));
    }

    public Proveedor one(Long id) {
        return this.proveedorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Proveedor.class));
    }

    public Proveedor replace(Long id, Proveedor proveedor) {
        return this.proveedorRepository.findById(id).map( p -> {
                    if (id.equals(proveedor.getId())) return this.proveedorRepository.save(proveedor);
                    else throw new NotCouplingIdException(id, proveedor.getId(), Proveedor.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Proveedor.class));
    }

    public void delete(Long id) {
        this.proveedorRepository.findById(id).map(p -> {
                    this.proveedorRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Proveedor.class));
    }

}
