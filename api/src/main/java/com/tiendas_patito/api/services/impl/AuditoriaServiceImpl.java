package com.tiendas_patito.api.services.impl;

import com.tiendas_patito.api.entities.Auditoria;
import com.tiendas_patito.api.repositories.AuditoriaRepository;
import com.tiendas_patito.api.services.IAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements IAuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    public List<Auditoria> getAllAuditorias() {
        return auditoriaRepository.findAll();
    }

    @Override
    public Auditoria getAuditoriaById(Long id) {
        return auditoriaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Auditoria> getAuditoriasByFechaEventoBetween(String fechaInicio, String fechaFin) {

        Date fechaInicial = new Date();
        Date fechaFinal = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try{
            fechaInicial = formato.parse(fechaInicio);
            fechaFinal = formato.parse(fechaFin);
        }catch(ParseException e){
            return null;
        }

        return auditoriaRepository.findByFechaEventoBetween(fechaInicial, fechaFinal);
    }

    @Override
    public List<Auditoria> getAuditoriasByIdUsuario(Long idUsuario) {
        return auditoriaRepository.findByIdCliente(idUsuario);
    }

    @Override
    public Auditoria saveAuditoria(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    @Override
    public void deleteAuditoria(Long id) {
        auditoriaRepository.deleteById(id);
    }
}
