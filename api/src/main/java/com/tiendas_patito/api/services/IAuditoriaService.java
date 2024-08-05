package com.tiendas_patito.api.services;

import com.tiendas_patito.api.entities.Auditoria;

import java.util.Date;
import java.util.List;

public interface IAuditoriaService {

    public List<Auditoria> getAllAuditorias();

    public Auditoria getAuditoriaById(Long id);

    public List<Auditoria> getAuditoriasByFechaEventoBetween(String fechaInicio, String fechaFin) ;

    public List<Auditoria> getAuditoriasByIdUsuario(Long idUsuario);

    public Auditoria saveAuditoria(Auditoria auditoria);

    public void deleteAuditoria(Long id);
}
