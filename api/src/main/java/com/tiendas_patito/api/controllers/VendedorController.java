package com.tiendas_patito.api.controllers;

import com.tiendas_patito.api.entities.Vendedor;
import com.tiendas_patito.api.services.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private IVendedorService iVendedorService;

    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return iVendedorService.getAllVendedores();
    }

    @GetMapping("/{id}")
    public Vendedor getVendedorById(@PathVariable Long id) {
        return iVendedorService.getVendedorById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public Vendedor getVendedorByNombre(@PathVariable String nombre) {
        return iVendedorService.getVendedorByNombre(nombre);
    }

    @DeleteMapping("/{id}")
    public void deleteVendedor(@PathVariable Long id) {
        iVendedorService.deleteVendedor(id);
    }
}