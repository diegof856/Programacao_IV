import React, { useState, useEffect } from "react";
import { usePost } from "../../hooks/usePost";
import { useNavigate } from "react-router-dom";
const CriarVotacao = ({id}) => {
    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [tempoVotacao, setTempoVotacao] = useState("");
    const { httpConfig, carregamento, erro, limparErro, resposta } = usePost();
    const url = `http://localhost:8080/v1/pautas/${id}`;
    const navegar = useNavigate();
    const subirFormulario = async (e) => {
        e.preventDefault();
        const pauta = {
            titulo,
            descricao,
            tempoVotacao
        }
       
        httpConfig(pauta, "POST", url);
        navegar(`/MainPage/${id}`);
    }
    return (
        <form onSubmit={subirFormulario}>
            <label>
                <input type="text" value={titulo} name="titulo" placeholder="Digite o titulo da votação" onChange={(e) => setTitulo(e.target.value)} />
            </label>
            <label>
                <input type="text" value={descricao} name="descricao" placeholder="Digite a descrição da votação" autoComplete="off" onChange={(e) => setDescricao(e.target.value)} />
            </label>
            <label>
                <input type="number" value={tempoVotacao} name="tempoVotacao" placeholder="Digite o tempo que a votação (só será até 60 minutos)" autoComplete="off" onChange={(e) => setTempoVotacao(e.target.value)} />
            </label>
            { carregamento && <button type="submit" >Aguarde</button>}
             { !carregamento && <button type="submit" >Criar</button>}
           
        </form>
    )
}
export default CriarVotacao;