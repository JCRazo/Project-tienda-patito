package com.tiendas_patito.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "auditorias")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fechaEvento;

    @Column(nullable = false)
    private Long idCliente;

    @Column(nullable = false)
    private Long idPedido;

    @Column(nullable = false)
    private Long idVendedor;
}
