import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import ClientLogin from './components/ClientLogin';
import SellerLogin from './components/SellerLogin';
import ClientProducts from './components/ClientProducts';
import SellerOrders from './components/SellerOrders';
import OrderConfirmation from './components/OrderConfirmation';
import Home from './Home';
import Audits from './components/Audits';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/client-login" element={<ClientLogin />} />
        <Route path="/seller-login" element={<SellerLogin />} />
        <Route path="/client-products" element={<ClientProducts />} />
        <Route path="/seller-orders" element={<SellerOrders />} />
        <Route path="/order-confirmation" element={<OrderConfirmation />} />
        <Route path="/audits" element={<Audits />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
