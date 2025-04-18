package org.iesvdm.proyecto_servidor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.iesvdm.proyecto_servidor.service.ProductoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping({"", "/"})
    public Page<Producto> all(Pageable pageable) {
        return this.productoService.all(pageable);
    }

    @PostMapping({"", "/"})
    public Producto newProducto(@RequestBody Producto producto) {
        return this.productoService.saveOrGetIfExists(producto);
    }

    @GetMapping("/{id}")
    public Producto one(@PathVariable("id") Long id) {
        return this.productoService.one(id);
    }

    @PutMapping("/{id}")
    public Producto replaceProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
        return this.productoService.replace(id, producto);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable("id") Long id) {
        this.productoService.delete(id);
    }

    @GetMapping("/filterByIdOrNombreOrProvIdOrCatId")
    public List<Producto> filtrado(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "proveedorId", required = false) Long provId,
            @RequestParam(value = "categoriaId", required = false) Long catId) {
        return productoService.filterByIdOrNombreOrProvIdOrCatId(id, nombre, provId, catId);
    }

}
