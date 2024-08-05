package com.tiendas_patito.api.repositories;

import com.tiendas_patito.api.entities.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {

    Optional<Vendedor> findByNombre(String nombre);

    Optional<Vendedor> findByUsername(String nombre);
}
