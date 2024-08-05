package com.tiendas_patito.api.controllers;

import com.tiendas_patito.api.entities.Cliente;
import com.tiendas_patito.api.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return iClienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return iClienteService.getClienteById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Cliente getClienteByNombre(@PathVariable String nombre) {
        return iClienteService.getClienteByNombre(nombre);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        iClienteService.deleteCliente(id);
    }
}