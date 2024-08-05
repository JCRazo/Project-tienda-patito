package com.tiendas_patito.api.repositories;

import com.tiendas_patito.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNombre(String nombre);

    Optional<Cliente> findByUsername(String nombre);
}