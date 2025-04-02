package org.iesvdm.proyecto_servidor.controller;


import org.iesvdm.proyecto_servidor.dto.DTOMessageResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/prueba")
public class PruebaController {

    @GetMapping({"","/"})
    public DTOMessageResponse prueba() {

        return new DTOMessageResponse("Prueba security con éxito");

    }

    @GetMapping("/solo-admin")
    public DTOMessageResponse pruebaSoloAdmin() {

        return new DTOMessageResponse("Prueba security solo-admin con éxito");

    }

}
