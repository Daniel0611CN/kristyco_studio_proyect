package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.domain.Categoria;
import org.iesvdm.proyecto_servidor.domain.Pago;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> all() { return this.pagoRepository.findAll(); }

    @Transactional
    public Pago saveOrGetIfExists(Pago pago) {
        if (pago == null) throw new IllegalArgumentException("El pago no puede ser null");

        return pago.getId() == null ?
                pagoRepository.save(pago) :
                pagoRepository.findById(pago.getId())
                        .orElseThrow(() -> new EntityNotFoundException(pago.getId(), Pago.class));
    }

    public Pago one(Long id) {
        return this.pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Pago.class));
    }

    public Pago replace(Long id, Pago pago) {
        return this.pagoRepository.findById(id).map( p -> {
                    if (id.equals(pago.getId())) return this.pagoRepository.save(pago);
                    else throw new NotCouplingIdException(id, pago.getId(), Pago.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Pago.class));
    }

    public void delete(Long id) {
        this.pagoRepository.findById(id).map(p -> {
                    this.pagoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Pago.class));
    }

}
