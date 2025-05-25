package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.service.CategoriaService;
import org.iesvdm.proyecto_servidor.model.domain.Categoria;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @GetMapping({"", "/"})
    public List<Categoria> all() {
        return this.categoriaService.all();
    }

    @PostMapping({"", "/"})
    public Categoria create(@RequestBody Categoria categoria) {
        return this.categoriaService.saveOrGetIfExists(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.categoriaService.delete(id);
    }

}
