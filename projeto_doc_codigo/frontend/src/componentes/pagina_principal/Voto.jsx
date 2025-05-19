import { useGet } from "../../hooks/useGet";
import { usePost } from "../../hooks/usePost";
import React, { useState, useEffect } from "react";
const Voto = ({ idAssociado, idPauta }) => {
    const [idVotacao, setIdVotacao] = useState("");
    const urlVotacao = `http://localhost:8080/v1/votacoes/pauta/${idPauta}`;
    const urlVotar = `http://localhost:8080/v1/votos/${idAssociado}/${idVotacao}`;
    
    const { httpConfig, carregamento, erro, limparErro } = usePost();
    const { dados } = useGet(urlVotacao);
    useEffect(() => {
        limparErro();
    }, []);
    useEffect(() => {

        if (dados) {
         
            setIdVotacao(dados.id);
        }
    }, [dados]);

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


    return (
        <article>
            <h3>Opção de votação</h3>
            {carregamento ? (
                <p>Carregando...</p>
            ) : (
                <>
                    <button onClick={computarVotoSim}>SIM</button>
                    <button onClick={computarVotoNao}>NÃO</button>
                </>
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