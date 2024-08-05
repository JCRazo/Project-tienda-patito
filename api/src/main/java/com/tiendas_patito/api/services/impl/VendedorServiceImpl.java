package com.tiendas_patito.api.services.impl;

import com.tiendas_patito.api.entities.Vendedor;
import com.tiendas_patito.api.repositories.VendedorRepository;
import com.tiendas_patito.api.services.IVendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorServiceImpl implements IVendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    @Override
    public Vendedor getVendedorById(Long id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    @Override
    public Vendedor getVendedorByNombre(String nombre) {
        return vendedorRepository.findByNombre(nombre).orElse(null);
    }

    @Override
    public void deleteVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
}
