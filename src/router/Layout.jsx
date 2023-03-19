import React, { useRef, useState } from "react";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "../pages/home/Home";
import Login from "../pages/login/Login";
import NotFound from "../pages/not-found/NotFound";

const Layout = () => {
  // localStorage.clear();
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            !!localStorage.getItem("account")?.status ? (
              <Navigate to="/home" />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route path="/login" element={<Login />}></Route>
        <Route path="/home" element={<Home />} />
        <Route path="/*" element={<NotFound />}></Route>
      </Routes>
    </BrowserRouter>
  );
};

export default Layout;
