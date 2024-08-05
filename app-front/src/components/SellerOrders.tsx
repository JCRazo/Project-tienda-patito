import React, { useEffect, useState } from 'react';
import axios from '../axiosConfig';
import { Link } from 'react-router-dom';
import './SellerOrders.css';

interface Product {
  id: number;
  hawa: string;
  nombre: string;
  descripcion: string;
  precio: number;
  existencias: number;
  descuento: number;
  activoDescuento: boolean;
  precioConDescuento: number;
}

interface OrderDetail {
  id: number;
  producto: Product;
  cantidad: number;
  precioUnitario: number;
}

interface Order {
  id: number;
  monto: number;
  idCliente: number;
  idVendedor: number;
  estatus: string;
  fechaCreacion: string;
  detallesPedidos: OrderDetail[];
}

const SellerOrders: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [selectedOrder, setSelectedOrder] = useState<Order | null>(null);
  const [showErrorModal, setShowErrorModal] = useState(false);

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get('/api/pedidos', {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
          }
        });
        setOrders(response.data);
      } catch (error) {
        console.error('Error fetching orders', error);
      }
    };

    fetchOrders();
  }, []);

  const handleSelectOrder = (order: Order) => {
    setSelectedOrder(order);
  };

  const handleUpdateStatus = async (status: string) => {
    if (!selectedOrder) return;
    try {
      await axios.put(`/api/pedidos/${selectedOrder.id}/actualizar-estatus?estatus=${status}`, null, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      });
      const response = await axios.get('/api/pedidos', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      });
      setOrders(response.data);
      setSelectedOrder(null);
    } catch (error) {
      setShowErrorModal(true);
    }
  };

  const handleCloseModal = () => {
    setShowErrorModal(false);
  };

  return (
    <div className="orders-container">
      <Link to="/audits">
        <button className="audits-button">Ver Auditorías</button>
      </Link>
      <h2>Lista de ordenes</h2>
      <table className="orders-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Estatus</th>
            <th>Monto</th>
            <th>Fecha de creación</th>
          </tr>
        </thead>
        <tbody>
          {orders.map(order => (
            <tr key={order.id} onClick={() => handleSelectOrder(order)} style={{ cursor: 'pointer' }}>
              <td>{order.id}</td>
              <td>{order.estatus}</td>
              <td>${order.monto}</td>
              <td>{new Date(order.fechaCreacion).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
      {selectedOrder && (
        <div className="order-card">
          <h3>Detalles de la orden</h3>
          <p>ID de la orden: {selectedOrder.id}</p>
          <p>Estatus: {selectedOrder.estatus}</p>
          <p>Monto total: ${selectedOrder.monto}</p>
          <p>Fecha de creación: {new Date(selectedOrder.fechaCreacion).toLocaleString()}</p>
          <h4>Productos:</h4>
          <table className="details-table">
            <thead>
              <tr>
                <th>Nombre del producto</th>
                <th>Cantidad</th>
                <th>Precio unitario</th>
              </tr>
            </thead>
            <tbody>
              {selectedOrder.detallesPedidos.map(detail => (
                <tr key={detail.id}>
                  <td>{detail.producto.nombre}</td>
                  <td>{detail.cantidad}</td>
                  <td>${detail.precioUnitario}</td>
                </tr>
              ))}
            </tbody>
          </table>
          <button onClick={() => handleUpdateStatus('cancelado')} className="order-button">Cancelar Orden</button>
          <button onClick={() => handleUpdateStatus('entregado')} className="order-button">Marcar como entregado</button>
        </div>
      )}
      {showErrorModal && (
        <div className="modal">
          <div className="modal-content">
            <h3>Error</h3>
            <p>No es posible cancelar un pedido ya entregado o cancelarlo después de 10 minutos.</p>
            <button onClick={handleCloseModal} className="order-button">Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default SellerOrders;

