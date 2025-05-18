package org.iesvdm.proyecto_servidor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BasicControllerInterface<T> {

    @GetMapping({"", "/"})
    List<T> all();

    @PostMapping({"", "/"})
    T create(@RequestBody T t);

    @GetMapping("/{id}")
    T one(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    T update(@PathVariable("id") Long id, @RequestBody T t);

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

}
