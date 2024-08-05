import React, { useState } from 'react';
import axios from '../axiosConfig';
import './login.css';
import userImage from '../assets/seller.png';

const SellerLogin: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const response = await axios.post('/api/auth/login', { username, password });
      localStorage.setItem('token', response.data.token);
      window.location.href = '/seller-orders';
    } catch (error) {
      console.error('Login error', error);
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <img src={userImage} alt="User" className="user-image" />
        <h2>Inicio de sesión para vendedor</h2>
        <input type="text" placeholder="Username" value={username} onChange={e => setUsername(e.target.value)} />
        <input type="password" placeholder="Contraseña" value={password} onChange={e => setPassword(e.target.value)} />
        <button onClick={handleLogin}>Ingresar</button>
      </div>
    </div>
  );
};

export default SellerLogin;

