package com.tiendas_patito.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String hawa;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer existencias;

    @Column(nullable = true)
    private Double descuento;

    @Column(nullable = false)
    private Boolean activoDescuento  = false;

    @OneToMany(mappedBy = "producto")
    @JsonBackReference
    private List<DetallePedido> detallesPedidos;

    public Double getPrecioConDescuento() {
        if (activoDescuento && descuento != null) {
            return precio - descuento;
        }
        return precio;
    }
}

