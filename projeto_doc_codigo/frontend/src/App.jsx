// App.jsx
import React, { useState } from "react";
import { LoginCadastro } from "./pages/Login_Cadastro";
import { MenuPrincipal } from "./pages/Menu_Principal";
import { BrowserRouter, Routes, Route } from "react-router-dom";
// CADASTRO E LOGIN
//
const MainPage = () => {


    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<LoginCadastro />} />
                    <Route path="/MainPage/:id" element={<MenuPrincipal />} />
                </Routes>
            </BrowserRouter>
        </>
    );
};



export default MainPage;
