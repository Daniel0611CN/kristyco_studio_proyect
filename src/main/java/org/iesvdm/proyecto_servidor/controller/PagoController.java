package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.service.PagoService;
import org.iesvdm.proyecto_servidor.model.domain.Pago;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pagos")
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
public class PagoController {

    private final PagoService pagoService;

    @GetMapping({"", "/"})
    public List<Pago> all() {
        return this.pagoService.all();
    }

    @PostMapping({"", "/"})
    public Pago newPago(@RequestBody Pago pago) {
        return this.pagoService.saveOrGetIfExists(pago);
    }

    @GetMapping("/{id}")
    public Pago one(@PathVariable("id") Long id) {
        return this.pagoService.one(id);
    }

    @PutMapping("/{id}")
    public Pago replacePago(@PathVariable("id") Long id, @RequestBody Pago pago) {
        return this.pagoService.replace(id, pago);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePago(@PathVariable("id") Long id) {
        this.pagoService.delete(id);
    }

}
