import { usePut } from "../../hooks/usePut";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import Erro from "../errocorreto/Erro";
const EditarVotacao = ({ idPauta, idAssociado, setAbrirMoldalVotacao, setEditar, setMostrarImg }) => {
    const { httpPutConfig, carregamento, erro, limparErro } = usePut(`http://localhost:8080/v1/pautas/${idPauta}`);
    const [titulo, setTitulo] = useState("");
    const [descricao, setDescricao] = useState("");
    const [tempoVotacao, setTempoVotacao] = useState("");
    const navegar = useNavigate();
    const enviarEdicaoFormulario = async (e) => {
        e.preventDefault();
        limparErro();   
        setDescricao("");
        setTitulo("");
        setTempoVotacao("");
        const pauta = {
            titulo,
            descricao,
            tempoVotacao
        }


        const sucesso = await httpPutConfig(pauta);

        if (sucesso) {
            setEditar(false);
            setMostrarImg(true);
            navegar(`/MainPage/${idAssociado}`);
        }



    }
    const cancelar = () => {
        setAbrirMoldalVotacao(false);
        setMostrarImg(true);
        setEditar(false);
        navegar(`/MainPage/${idAssociado}`);
    }
    return (

        <form onSubmit={enviarEdicaoFormulario} className="caixa-votacao-aberta">
            <h3>Editar Votação</h3>
            <label>

                <input type="text" value={titulo} name="titulo" placeholder="Digite o titulo da votação" onChange={(e) => setTitulo(e.target.value)} />
            </label>
            <label>
                <input type="text" value={descricao} name="descricao" placeholder="Descrição da votação" autoComplete="off" onChange={(e) => setDescricao(e.target.value)} />
            </label>
            <label>
                <input type="number" value={tempoVotacao} name="tempoVotacao" placeholder="Tempo que a votação (60 min max)" autoComplete="off" onChange={(e) => setTempoVotacao(e.target.value)} />
            </label>
               {erro && erro.status && erro && erro.mensagem && <Erro status={erro.status} mensagem={erro.mensagem} />}
            <div className="botoes-acoes">
                {carregamento && <button className="btn-editar" type="submit" disabled>Aguarde</button>}
                {!carregamento && <button className="btn-editar" type="submit" >Editar</button>}
                {!carregamento && <button className="btn-voltar" type="button" onClick={cancelar}>Cancelar</button>}
             

            </div>

        </form>
    )
}
export default EditarVotacao;