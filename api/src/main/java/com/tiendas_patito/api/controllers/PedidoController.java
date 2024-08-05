package com.tiendas_patito.api.controllers;

import com.tiendas_patito.api.entities.Pedido;
import com.tiendas_patito.api.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private IPedidoService iPedidoService;

    @GetMapping
    public List<Pedido> getAllPedidos() {
        return iPedidoService.getAllPedidos();
    }

    @GetMapping("/{id}")
    public Pedido getPedidoById(@PathVariable Long id) {
        return iPedidoService.getPedidoById(id);
    }

    @GetMapping("/estatus/{estatus}")
    public List<Pedido> getPedidosByEstatus(@PathVariable String estatus) {
        return iPedidoService.getPedidosByEstatus(estatus);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> getPedidosByClienteId(@PathVariable Long clienteId) {
        return iPedidoService.getPedidosByClienteId(clienteId);
    }

    @GetMapping("/vendedor/{vendedorId}")
    public List<Pedido> getPedidosByVendedorId(@PathVariable Long vendedorId) {
        return iPedidoService.getPedidosByVendedorId(vendedorId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = iPedidoService.savePedido(pedido);
            return ResponseEntity.ok(nuevoPedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error -> "+e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        iPedidoService.deletePedido(id);
    }

    @PutMapping("/{id}/actualizar-estatus")
    public ResponseEntity<?> actualizarEstatus(@PathVariable Long id, @RequestParam String estatus) {
        try{
            Pedido pedidoActualizado = iPedidoService.actualizarEstatusPedido(id, estatus);
            return ResponseEntity.ok(pedidoActualizado);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}