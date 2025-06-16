package org.iesvdm.proyecto_servidor.controller;

import org.iesvdm.proyecto_servidor.model.domain.Producto;
import org.iesvdm.proyecto_servidor.model.enums.EstadoPedido;
import org.iesvdm.proyecto_servidor.repository.PedidoRepository;
import org.iesvdm.proyecto_servidor.service.PedidoService;
import org.iesvdm.proyecto_servidor.model.domain.Pedido;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/pedidos")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://kristyco-studio.vercel.app")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoRepository pedidoRepository;

    @GetMapping({"", "/"})
    public Page<Pedido> all(@RequestParam(name="direccion", required = false) Optional<String> optDireccion,
                            @RequestParam(name="estado", required = false) Optional<EstadoPedido> optEstado,
                            @PageableDefault Pageable pageable) { return this.pedidoService.all(optDireccion, optEstado, pageable); }

    @GetMapping( "/list")
    public List<Pedido> all() { return this.pedidoService.all(); }

    @PostMapping({"", "/"})
    public Pedido newPedido(@RequestBody Pedido pedido) { return this.pedidoService.saveOrGetIfExists(pedido); }

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

    @GetMapping("/{id}/pedidosPorUsuario")
    public List<Pedido> pedidosGroupByUser(@PathVariable("id") Long id) {
        return this.pedidoRepository.findAllByUsuario_Id(id);
    }

    @GetMapping("/{id}/pedidosByCategoria")
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
