import React from 'react';
import { Link } from 'react-router-dom';
import logo from './assets/patito.png';
import './home.css';

const Home: React.FC = () => {
  return (
    <div className="home-container">
      <img src={logo} alt="Logo" className="home-logo" />
      <h1>Tiendas Patito</h1>
      <h2>Bienvenido</h2>
      <Link to="/client-login">
        <button className="home-button">
          Soy Cliente
        </button>
      </Link>
      <Link to="/seller-login">
        <button className="home-button">
          Soy Vendedor
        </button>
      </Link>
    </div>
  );
};

export default Home;


