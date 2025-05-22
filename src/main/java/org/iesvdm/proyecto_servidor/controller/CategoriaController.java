package org.iesvdm.proyecto_servidor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto_servidor.model.domain.Categoria;
import org.iesvdm.proyecto_servidor.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/categorias")
@AllArgsConstructor
public class CategoriaController implements BasicControllerInterface<Categoria> {

    private final CategoriaService categoriaService;

    @Override
    public List<Categoria> all() {
        return this.categoriaService.all();
    }

    @Override
    public Categoria create(Categoria categoria) {
        return this.categoriaService.saveOrGetIfExists(categoria);
    }

    @Override
    public Categoria one(Long id) {
        return this.categoriaService.one(id);
    }

    @Override
    public Categoria update(Long id, Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }

    @Override
    public void delete(Long id) {
        this.categoriaService.delete(id);
    }

}
