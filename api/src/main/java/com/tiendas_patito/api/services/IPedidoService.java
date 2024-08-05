package com.tiendas_patito.api.services;

import com.tiendas_patito.api.entities.Pedido;

import java.util.List;

public interface IPedidoService {

    public List<Pedido> getAllPedidos();

    public Pedido getPedidoById(Long id);

    public List<Pedido> getPedidosByEstatus(String estatus);

    public List<Pedido> getPedidosByClienteId(Long clienteId);

    public List<Pedido> getPedidosByVendedorId(Long vendedorId);

    public Pedido savePedido(Pedido pedido) throws Exception ;

    public void deletePedido(Long id);

    public Pedido actualizarEstatusPedido(Long id, String nuevoEstatus);
}
