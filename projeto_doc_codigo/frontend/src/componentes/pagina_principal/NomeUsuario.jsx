import { useGet } from "../../hooks/useGet";
import React, { useState, useEffect } from "react";
import "../../styles/MenuPrincipal.css";
import { useNavigate } from "react-router-dom";
const NomeUsuario = ({id}) => {
    const urlUsuario = `http://localhost:8080/v1/associados/${id}`;
    const { dados } = useGet(urlUsuario);
    const [nome, setNome] = useState(null);
    const navigate = useNavigate();
    useEffect(() => {
        if (dados) {
            setNome(dados.nome);
        }
    }, [dados]);

    const sair = () =>{
         navigate(`/`);
    }
    return (
        <nav>
            <p className="nomeUsuario">{nome}</p>
           <button className = "botao-deslogar "onClick={sair}>Sair</button>
        </nav>
    )
} 

export default NomeUsuario;