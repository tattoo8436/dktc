import React, { useRef, useState } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "../pages/home/Home";
import Login from "../pages/login/Login";
import NotFound from "../pages/not-found/NotFound";

const Layout = () => {
  const isLogin = Boolean(localStorage.getItem("account"));

  return (
    <BrowserRouter>
      {isLogin ? (
        <Routes>
          <Route path="/" element={<Navigate to="/home" />} />
          <Route path="/login" element={<Navigate to="/home" />}></Route>
          <Route path="/home" element={<Home />} />
          <Route path="*" element={<NotFound />}></Route>
        </Routes>
      ) : (
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/home" element={<Navigate to="/login" />} />
          <Route path="/login" element={<Login />}></Route>
          <Route path="*" element={<NotFound />}></Route>
        </Routes>
      )}
    </BrowserRouter>
  );
};

export default Layout;
