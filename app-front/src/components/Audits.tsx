import React, { useEffect, useState } from 'react';
import axios from '../axiosConfig';
import './Audits.css';

interface Audit {
  id: number;
  fechaEvento: string;
  idCliente: number;
  idPedido: number;
  idVendedor: number;
}

const Audits: React.FC = () => {
  const [audits, setAudits] = useState<Audit[]>([]);

  useEffect(() => {
    const fetchAudits = async () => {
      try {
        const response = await axios.get('/api/auditorias', {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
          }
        });
        setAudits(response.data);
      } catch (error) {
        console.error('Error fetching audits', error);
      }
    };

    fetchAudits();
  }, []);

  const handleBackToOrders = () => {
    window.location.href = '/seller-orders';
  };

  return (
    <div className="audits-container">
      <button onClick={handleBackToOrders} className="back-button">
        Volver
      </button>
      <h2>Lista de auditorias</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Fecha del evento</th>
            <th>ID del cliente</th>
            <th>ID de la orden</th>
            <th>ID del vendedor</th>
          </tr>
        </thead>
        <tbody>
          {audits.map(audit => (
            <tr key={audit.id}>
              <td>{audit.id}</td>
              <td>{new Date(audit.fechaEvento).toLocaleString()}</td>
              <td>{audit.idCliente}</td>
              <td>{audit.idPedido}</td>
              <td>{audit.idVendedor}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Audits;

