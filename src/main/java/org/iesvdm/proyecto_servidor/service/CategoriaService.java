package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.domain.Categoria;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> all() { return this.categoriaRepository.findAll(); }

    @Transactional
    public Categoria saveOrGetIfExists(Categoria categoria) {
        if (categoria == null) throw new IllegalArgumentException("La categoría no puede ser null");

        return categoria.getId() == null ?
                categoriaRepository.save(categoria) :
                categoriaRepository.findById(categoria.getId())
                        .orElseThrow(() -> new EntityNotFoundException(categoria.getId(), Categoria.class));
    }

    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

    public Categoria replace(Long id, Categoria categoria) {
        return this.categoriaRepository.findById(id).map( p -> {
                    if (id.equals(categoria.getId())) return this.categoriaRepository.save(categoria);
                    else throw new NotCouplingIdException(id, categoria.getId(), Categoria.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {
                    this.categoriaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

}
