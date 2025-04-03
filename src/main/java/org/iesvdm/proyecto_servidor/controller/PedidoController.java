package org.iesvdm.proyecto_servidor.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iesvdm.proyecto_servidor.domain.Pedido;
import org.iesvdm.proyecto_servidor.domain.Producto;
import org.iesvdm.proyecto_servidor.enums.EstadoPedido;
import org.iesvdm.proyecto_servidor.service.PedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping({"", "/"})
    public Page<Pedido> all(Pageable pageable) { return this.pedidoService.all(pageable); }

    @PostMapping({"", "/"})
    public Pedido newPedido(@RequestBody Pedido pedido) { return this.pedidoService.save(pedido); }

    @GetMapping("/{id}")
    public Pedido one(@PathVariable("id") Long id) {
        return this.pedidoService.one(id);
    }

    @PutMapping("/{id}")
    public Pedido replacePedido(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
        System.out.println(pedido);
        return this.pedidoService.replace(id, pedido);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable("id") Long id) {
        this.pedidoService.delete(id);
    }

    @GetMapping("{id}/pedidosByCategoria")
    public Set<Pedido> pedidosByCategoriaId(@PathVariable("id") Long id) {
        return pedidoService.getPedidosByCategoriaId(id);
    }

    @GetMapping("/pedidosAfterFecha")
    public List<Pedido> pedidosAfterDate(@RequestParam("date") String date) {
        LocalDateTime fechaRequest = LocalDateTime.parse(date);
        return pedidoService.getPedidosAfterDate(fechaRequest);
    }

    @GetMapping("/getPedidosHigherThanCoste")
    public Set<Pedido> pedidosHigherThanCoste(@RequestParam("coste") String coste) {
        BigDecimal value = BigDecimal.valueOf(Double.parseDouble(coste));
        return pedidoService.getAllPedidoByCosteEnvioHigherThan(value);
    }

    @GetMapping("/getPedidosByEstado")
    public Set<Pedido> pedidosByEstado(@RequestParam("estado") String estado) {
        if (estado.equalsIgnoreCase("pendiente")
         || estado.equalsIgnoreCase("en_camino")
         || estado.equalsIgnoreCase("entregado")) {
            estado = estado.toUpperCase();
            return pedidoService.getAllByEstado(EstadoPedido.valueOf(estado));
        } else {
            throw new IllegalArgumentException("El estado del Pedido introducido no es válido.");
        }
    }

    @GetMapping("/findByIdOrDireccionOrEstadoOrFechaOrCosteEnvio")
    public List<Pedido> filtrado(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "estado", required = false) EstadoPedido estado,
            @RequestParam(value = "fecha", required = false) LocalDateTime fecha,
            @RequestParam(value = "coste", required = false) BigDecimal coste) {
        return pedidoService.filter(id, direccion, estado, fecha, coste);
    }

}
