package com.tiendas_patito.api.services.impl;

import com.tiendas_patito.api.entities.Cliente;
import com.tiendas_patito.api.repositories.ClienteRepository;
import com.tiendas_patito.api.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente getClienteByNombre(String nombre) {
        return clienteRepository.findByNombre(nombre).orElse(null);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
