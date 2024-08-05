package com.tiendas_patito.api.services;

import com.tiendas_patito.api.entities.Vendedor;

import java.util.List;


public interface IVendedorService {

    public List<Vendedor> getAllVendedores();

    public Vendedor getVendedorById(Long id);

    public Vendedor getVendedorByNombre(String nombre);

    public void deleteVendedor(Long id);
}
