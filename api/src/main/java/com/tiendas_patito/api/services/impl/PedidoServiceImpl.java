package com.tiendas_patito.api.services.impl;

import com.tiendas_patito.api.entities.Auditoria;
import com.tiendas_patito.api.entities.DetallePedido;
import com.tiendas_patito.api.entities.Pedido;
import com.tiendas_patito.api.entities.Producto;
import com.tiendas_patito.api.repositories.AuditoriaRepository;
import com.tiendas_patito.api.repositories.PedidoRepository;
import com.tiendas_patito.api.repositories.ProductoRepository;
import com.tiendas_patito.api.services.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImpl implements IPedidoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Pedido> getPedidosByEstatus(String estatus) {
        return pedidoRepository.findByEstatus(estatus);
    }

    @Override
    public List<Pedido> getPedidosByClienteId(Long clienteId) {
        return pedidoRepository.findByIdCliente(clienteId);
    }

    @Override
    public List<Pedido> getPedidosByVendedorId(Long vendedorId) {
        return pedidoRepository.findByIdVendedor(vendedorId);
    }

    @Override
    public Pedido savePedido(Pedido pedido) {

        for (DetallePedido detalle : pedido.getDetallesPedidos()) {
            Producto productoExistente = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (detalle.getCantidad() > productoExistente.getExistencias()) {
                throw new RuntimeException("No hay suficientes existencias para el producto: " + productoExistente.getNombre());
            }
            detalle.setProducto(productoExistente);
        }

        double montoTotal = 0.0;
        for (DetallePedido detalle : pedido.getDetallesPedidos()) {
            Producto productoExistente = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            productoExistente.setExistencias(productoExistente.getExistencias() - detalle.getCantidad());
            productoRepository.save(productoExistente);

            double precioFinal = productoExistente.getPrecioConDescuento();
            detalle.setPrecioUnitario(precioFinal);
            detalle.setPedido(pedido);
            montoTotal += precioFinal * detalle.getCantidad();
        }

        pedido.setMonto(montoTotal);

        pedido.setFechaCreacion(LocalDateTime.now());

        pedido = pedidoRepository.save(pedido);


        Auditoria auditoria = new Auditoria();
        auditoria.setIdPedido(pedido.getId());
        auditoria.setFechaEvento(new Date());
        auditoria.setIdCliente(pedido.getIdCliente());
        auditoria.setIdVendedor(pedido.getIdVendedor());

        auditoriaRepository.save(auditoria);

        return pedido;
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido actualizarEstatusPedido(Long id, String nuevoEstatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        if(pedido.getEstatus().equals("cancelado") || pedido.getEstatus().equals("entregado")){
            throw new RuntimeException("Ya no es posible modificar un pedido cancelado o entregado");
        }
        if (Duration.between(pedido.getFechaCreacion(), LocalDateTime.now()).toMinutes() > 10) {
            throw new RuntimeException("No se puede actualizar el estatus del pedido después de 10 minutos");
        }
        if (!nuevoEstatus.equals("entregado") && !nuevoEstatus.equals("cancelado")) {
            throw new RuntimeException("Estatus inválido");
        }
        pedido.setEstatus(nuevoEstatus);
        if (nuevoEstatus.equals("cancelado")) {
            for (DetallePedido detalle : pedido.getDetallesPedidos()) {
                Producto producto = productoRepository.findById(detalle.getProducto().getId())
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                producto.setExistencias(producto.getExistencias() + detalle.getCantidad());
                productoRepository.save(producto);
            }
        }
        return pedidoRepository.save(pedido);
    }
}
