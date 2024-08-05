package com.tiendas_patito.api.repositories;

import com.tiendas_patito.api.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByEstatus(String estatus);

    List<Pedido> findByIdCliente(Long clienteId);

    List<Pedido> findByIdVendedor(Long vendedorId);
}