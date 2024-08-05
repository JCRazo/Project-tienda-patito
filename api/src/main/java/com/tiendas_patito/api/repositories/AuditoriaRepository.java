package com.tiendas_patito.api.repositories;

import com.tiendas_patito.api.entities.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {

    List<Auditoria> findByFechaEventoBetween(Date fechaInicio, Date fechaFin);

    List<Auditoria> findByIdCliente(Long idUsuario);
}