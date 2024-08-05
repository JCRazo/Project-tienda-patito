import React, { useEffect, useState } from 'react';
import axios from '../axiosConfig';
import './ClientProducts.css';

interface Product {
  id: number;
  hawa: string;
  nombre: string;
  descripcion: string;
  precio: number;
  descuento: number;
  precioConDescuento: number;
  existencias: number;
}

interface SelectedProduct {
  id: number;
  cantidad: number;
  precioUnitario: number;
}

const ClientProducts: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [selectedProducts, setSelectedProducts] = useState<SelectedProduct[]>([]);
  const [showErrorModal, setShowErrorModal] = useState(false);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await axios.get('/api/productos', {
          headers: {
            Authorization: `Bearer ${localStorage.getItem('token')}`
          }
        });
        setProducts(response.data);
      } catch (error) {
        console.error('Error fetching products', error);
      }
    };

    fetchProducts();
  }, []);

  const handleSelectProduct = (product: Product) => {
    setSelectedProducts(prev => {
      const existingProduct = prev.find(p => p.id === product.id);
      if (existingProduct) {
        return prev.filter(p => p.id !== product.id);
      } else {
        return [...prev, { id: product.id, cantidad: 1, precioUnitario: product.precio }];
      }
    });
  };

  const handleQuantityChange = (productId: number, cantidad: number) => {
    setSelectedProducts(prev =>
      prev.map(p => (p.id === productId ? { ...p, cantidad } : p))
    );
  };

  const handleCreateOrder = async () => {
    const orderData = {
      idCliente: `${localStorage.getItem('idCliente')}`,
      idVendedor: 1,
      estatus: 'pendiente',
      detallesPedidos: selectedProducts.map(product => ({
        producto: { id: product.id },
        cantidad: product.cantidad,
        precioUnitario: product.precioUnitario,
      })),
    };

    try {
      await axios.post('/api/pedidos', orderData, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      });
      window.location.href = '/order-confirmation';
    } catch (error) {
      setShowErrorModal(true);
    }
  };

  const handleCloseModal = () => {
    setShowErrorModal(false);
  };

  return (
    <div className="card">
      <h2>Lista de productos</h2>
      <table className="product-table">
        <thead>
          <tr>
            <th>Seleccionar</th>
            <th>HAWA</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Descuento</th>
            <th>Precio con descuento</th>
            <th>Cantidad</th>
            <th>Existencias</th>
          </tr>
        </thead>
        <tbody>
          {products.map(product => (
            <tr key={product.id}>
              <td>
                <input
                  type="checkbox"
                  className="product-checkbox"
                  checked={selectedProducts.some(p => p.id === product.id)}
                  onChange={() => handleSelectProduct(product)}
                />
              </td>
              <td>{product.hawa}</td>
              <td>{product.nombre}</td>
              <td>{product.descripcion}</td>
              <td>${product.precio}</td>
              <td>${product.descuento}</td>
              <td>${product.precioConDescuento}</td>
              <td>
                {selectedProducts.some(p => p.id === product.id) && (
                  <input
                    type="number"
                    className="product-quantity-input"
                    min="1"
                    value={selectedProducts.find(p => p.id === product.id)?.cantidad || 1}
                    onChange={(e) => handleQuantityChange(product.id, parseInt(e.target.value))}
                  />
                )}
              </td>
              <td>{product.existencias}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button className="create-order-button" onClick={handleCreateOrder}>Crear orden</button>
      {showErrorModal && (
        <div className="modal">
          <div className="modal-content">
            <h3>Error</h3>
            <p>Error creando orden. Por favor, verifique que no sobrepase el stock actual.</p>
            <button onClick={handleCloseModal} className="order-button">Cerrar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ClientProducts;
