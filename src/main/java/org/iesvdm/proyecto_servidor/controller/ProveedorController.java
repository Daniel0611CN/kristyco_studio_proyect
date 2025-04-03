package org.iesvdm.proyecto_servidor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto_servidor.domain.Proveedor;
import org.iesvdm.proyecto_servidor.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/proveedores")
@AllArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    @GetMapping({"", "/"})
    public List<Proveedor> all() {
        return this.proveedorService.all();
    }

    @PostMapping({"", "/"})
    public Proveedor newProveedor(@RequestBody Proveedor proveedor) {
        return this.proveedorService.saveOrGetIfExists(proveedor);
    }

    @GetMapping("/{id}")
    public Proveedor one(@PathVariable("id") Long id) {
        return this.proveedorService.one(id);
    }

    @PutMapping("/{id}")
    public Proveedor replaceProveedor(@PathVariable("id") Long id, @RequestBody Proveedor proveedor) {
        return this.proveedorService.replace(id, proveedor);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable("id") Long id) {
        this.proveedorService.delete(id);
    }

}
