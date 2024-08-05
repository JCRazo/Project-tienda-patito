package com.tiendas_patito.api.controllers;

import com.tiendas_patito.api.entities.Auditoria;
import com.tiendas_patito.api.services.IAuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/auditorias")
public class AuditoriaController {

    @Autowired
    private IAuditoriaService iAuditoriaService;

    @GetMapping
    public List<Auditoria> getAllAuditorias() {
        return iAuditoriaService.getAllAuditorias();
    }

    @GetMapping("/{id}")
    public Auditoria getAuditoriaById(@PathVariable Long id) {
        return iAuditoriaService.getAuditoriaById(id);
    }

    @GetMapping("/entre-fechas/{fechaInicio}/{fechaFin}")
    public List<Auditoria> getAuditoriasByFechaEventoBetween(@PathVariable String fechaInicio, @PathVariable String fechaFin) {
        return iAuditoriaService.getAuditoriasByFechaEventoBetween(fechaInicio, fechaFin);
    }

    @DeleteMapping("/{id}")
    public void deleteAuditoria(@PathVariable Long id) {
        iAuditoriaService.deleteAuditoria(id);
    }
}
