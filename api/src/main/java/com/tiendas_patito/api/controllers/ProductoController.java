package com.tiendas_patito.api.controllers;

import com.tiendas_patito.api.entities.Producto;
import com.tiendas_patito.api.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private IProductoService iProductoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return iProductoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return iProductoService.getProductoById(id);
    }

    @GetMapping("/hawa/{hawa}")
    public Producto getProductoByHawa(@PathVariable String hawa) {
        return iProductoService.getProductoByHawa(hawa);
    }

    @PostMapping
    public Producto saveProducto(@RequestBody Producto producto) {
        return iProductoService.saveProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return iProductoService.saveProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        iProductoService.deleteProducto(id);
    }
}
