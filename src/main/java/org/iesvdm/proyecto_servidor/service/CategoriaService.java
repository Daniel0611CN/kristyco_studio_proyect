package org.iesvdm.proyecto_servidor.service;

import jakarta.transaction.Transactional;
import org.iesvdm.proyecto_servidor.exception.EntityNotFoundException;
import org.iesvdm.proyecto_servidor.exception.NotCouplingIdException;
import org.iesvdm.proyecto_servidor.repository.CategoriaRepository;
import org.iesvdm.proyecto_servidor.model.domain.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CategoriaService implements BasicServiceInterface<Categoria> {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> all() {
        return this.categoriaRepository.findAll();
    }

    @Override
    public Page<Categoria> all(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Categoria saveOrGetIfExists(Categoria categoria) {
        if (categoria == null) throw new IllegalArgumentException("La categoría no puede ser null");

        return categoria.getId() == null ?
                categoriaRepository.save(categoria) :
                categoriaRepository.findById(categoria.getId())
                        .orElseThrow(() -> new EntityNotFoundException(categoria.getId(), Categoria.class));
    }

    @Override
    public Categoria one(Long id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

    @Override
    public Categoria replace(Long id, Categoria categoria) {
        return this.categoriaRepository.findById(id).map( p -> {
                    if (id.equals(categoria.getId())) return this.categoriaRepository.save(categoria);
                    else throw new NotCouplingIdException(id, categoria.getId(), Categoria.class);
                }
        ).orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

    @Override
    public void delete(Long id) {
        this.categoriaRepository.findById(id).map(p -> {
                    this.categoriaRepository.delete(p);
                    return p;
                })
                .orElseThrow(() -> new EntityNotFoundException(id, Categoria.class));
    }

}
