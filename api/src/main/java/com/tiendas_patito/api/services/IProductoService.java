package com.tiendas_patito.api.services;

import com.tiendas_patito.api.entities.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getAllProductos();

    public Producto getProductoById(Long id);

    public Producto getProductoByHawa(String hawa);

    public List<Producto> getProductosByExistenciasGreaterThan(int existencias);

    public Producto saveProducto(Producto producto);

    public void deleteProducto(Long id);
}
