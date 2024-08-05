package com.tiendas_patito.api.repositories;

import com.tiendas_patito.api.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByHawa(String hawa);

    List<Producto> findByExistenciasGreaterThan(int existencias);
}
