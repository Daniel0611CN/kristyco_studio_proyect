package org.iesvdm.proyecto_servidor.service;

import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.PagoRepository;
import org.iesvdm.proyecto_servidor.model.domain.Pago;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PagoService implements BasicServiceInterface<Pago> {

    private final PagoRepository pagoRepository;

    @Override
    public List<Pago> all() {
        return this.pagoRepository.findAll();
    }

    @Override
    public Page<Pago> all(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Pago saveOrGetIfExists(Pago pago) {
        if (pago == null) throw new IllegalArgumentException("El pago no puede ser null");

        return pago.getId() == null ?
                pagoRepository.save(pago) :
                pagoRepository.findById(pago.getId())
                        .orElseThrow(() -> new EntityNotFoundException(pago.getId(), Pago.class));
    }

    @Override
    public Pago one(Long id) {
        return this.pagoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Pago.class));
    }

    @Override
    public Pago replace(Long id, Pago pago) {
            return this.pagoRepository.findById(id).map( p -> {
                        if (id.equals(pago.getId())) return this.pagoRepository.save(pago);
                        else throw new NotCouplingIdException(id, pago.getId(), Pago.class);
                    }
            ).orElseThrow(() -> new EntityNotFoundException(id, Pago.class));
    }

    @Override
    public void delete(Long id) {
        this.pagoRepository.findById(id).map(p -> {
                    this.pagoRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Pago.class));
    }

}
