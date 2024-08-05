package com.tiendas_patito.api.services;

import com.tiendas_patito.api.entities.Cliente;

import java.util.List;

public interface IClienteService {

    public List<Cliente> getAllClientes();

    public Cliente getClienteById(Long id);

    public Cliente getClienteByNombre(String nombre);

    public void deleteCliente(Long id);
}
