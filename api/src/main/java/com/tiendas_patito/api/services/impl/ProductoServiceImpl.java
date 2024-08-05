package com.tiendas_patito.api.services.impl;

import com.tiendas_patito.api.entities.Producto;
import com.tiendas_patito.api.repositories.ProductoRepository;
import com.tiendas_patito.api.services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto getProductoByHawa(String hawa) {
        return productoRepository.findByHawa(hawa).orElse(null);
    }

    @Override
    public List<Producto> getProductosByExistenciasGreaterThan(int existencias) {
        return productoRepository.findByExistenciasGreaterThan(existencias);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
