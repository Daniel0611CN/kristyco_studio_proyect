package org.iesvdm.miproyecto.controller;


import org.iesvdm.miproyecto.domain.MessageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/v1/prueba")
public class PruebaController {

    @GetMapping({"","/"})
    public MessageResponse prueba() {

        return new MessageResponse("Prueba security con éxito");

    }

    @GetMapping("/solo-admin")
    public MessageResponse pruebaSoloAdmin() {

        return new MessageResponse("Prueba security solo-admin con éxito");

    }

}
