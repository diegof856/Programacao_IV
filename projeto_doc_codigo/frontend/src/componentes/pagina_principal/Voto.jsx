import { useGet } from "../../hooks/useGet";
import { usePost } from "../../hooks/usePost";
import { useDelete } from "../../hooks/useDelete";
import React, { useState, useEffect } from "react";
const Voto = ({ idAssociado, idPauta, setAbriModalVoto, setAbrirMoldalVotacao, setMostrarImg }) => {
    const [idVotacao, setIdVotacao] = useState("");
    const urlVotacao = `http://localhost:8080/v1/votacoes/pauta/${idPauta}`;
    const urlVotar = `http://localhost:8080/v1/votos/${idAssociado}/${idVotacao}`;
    const { httpConfig, carregamento, erro, limparErro } = usePost();
    const {httpConfigDeletar} = useDelete();
    const { dados } = useGet(urlVotacao);
    const [totalVotos, setTotalVotos] = useState("");
    const [pauta, setPauta] = useState("");
   
    useEffect(() => {
        limparErro();

    }, []);
    useEffect(() => {

        if (dados) {

            setIdVotacao(dados.id);
            setPauta(dados.pauta);
            setTotalVotos(dados.Quantidade_Votos)
        }
    }, [dados]);
    const editarVotacao = () =>{
        setAbriModalVoto(false);
        setAbrirMoldalVotacao(true);
    }
    const computarVotoSim = () => {
        const opcao = {
            voto: "sim"
        }
        httpConfig(opcao, "POST", urlVotar);
    }
    const computarVotoNao = () => {
        const opcao = {
            voto: "nao"
        }
        httpConfig(opcao, "POST", urlVotar);
    }
    const voltar = () => {
        if (setAbriModalVoto) {
            setAbriModalVoto(false);
            setMostrarImg(true);
        }
    }
    const deletarVotacao = () => {
        httpConfigDeletar(idPauta, "DELETE")
    }
     

    return (
        <article className="caixa-votacao-aberta">
            <h3>Opção de votação</h3>
            {carregamento ? (
                <p>Carregando...</p>
            ) : (
                <div >
                    <h3>{pauta.titulo}</h3>
                    <p>Criada por <strong>{pauta.autor}</strong></p>
                    <div className="botoes-opcoes">
                        <button className="botao-votar-um" onClick={computarVotoSim}>SIM</button>
                        <button className="botao-votar-dois" onClick={computarVotoNao}>NÃO</button>
                    </div>
                    <p>Total de pessoas que votaram: {totalVotos}</p>
                    <div className="botoes-acoes espacamento-superior">
                        <button onClick={voltar}>voltar</button>
                        {pauta.idAutor === idAssociado && (
                            <>
                                <button className="btn-editar" onClick={editarVotacao}>Editar</button>
                                 <button className="btn-deletar" onClick={() => {
                                    if (window.confirm("Tem certeza que deseja deletar esta votação?")) {
                                        deletarVotacao();
                                    }
                                }}>Deletar</button>
                            </>
                        )}
                    </div>

                </div>

            )}
            {erro && (
                <>
                    <p>{erro.status}</p>
                    <p>{erro.mensagem}</p>
                </>
            )}
        </article>

    )

}

export default Voto;