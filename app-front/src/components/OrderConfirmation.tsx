import React from 'react';
import checkImage from '../assets/check.png';
import './OrderConfirmation.css';

const OrderConfirmation: React.FC = () => {
  const handleBackToProducts = () => {
    window.location.href = '/client-products';
  };

  return (
    <div className="confirmation-card">
      <img src={checkImage} alt="Check" className="confirmation-image" />
      <h2>Confirmación de orden</h2>
      <p>Tu orden ha sido enviada!</p>
      <button onClick={handleBackToProducts} className="confirmation-button">
        Volver a lista de productos
      </button>
    </div>
  );
};

export default OrderConfirmation;


