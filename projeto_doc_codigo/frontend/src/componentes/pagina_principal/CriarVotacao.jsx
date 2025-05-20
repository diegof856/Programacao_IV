import React, { useState } from "react";
import { usePost } from "../../hooks/usePost";
import { useNavigate } from "react-router-dom";
const CriarVotacao = ({ id, setAbriModalVotacao, setMostrarImg }) => {
    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [tempoVotacao, setTempoVotacao] = useState("");
    const { httpConfig, carregamento, erro } = usePost();
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
    const cancelar = ()=>{
        setAbriModalVotacao(false);
        setMostrarImg(true);
        navegar(`/MainPage/${id}`);
    }
    return (
        <form onSubmit={subirFormulario} className="caixa-votacao-aberta">
            <h3>Nova Votação</h3>
            <label>

                <input type="text" value={titulo} name="titulo" placeholder="Digite o titulo da votação" onChange={(e) => setTitulo(e.target.value)} />
            </label>
            <label>
                <input type="text" value={descricao} name="descricao" placeholder="Descrição da votação" autoComplete="off" onChange={(e) => setDescricao(e.target.value)} />
            </label>
            <label>
                <input type="number" value={tempoVotacao} name="tempoVotacao" placeholder="Tempo que a votação (60 min max)" autoComplete="off" onChange={(e) => setTempoVotacao(e.target.value)} />
            </label>
            <div className="botoes-acoes">
                {carregamento && <button className="btn-editar" type="submit" >Aguarde</button>}
                {!carregamento && <button className="btn-editar" type="submit" >Criar</button>}
                {!carregamento && <button className="btn-voltar" type="submit" onClick={cancelar}>Cancelar</button>}

            </div>

        </form>
    )
}
export default CriarVotacao;