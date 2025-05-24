import { useGet } from "../../hooks/useGet";
import { usePost } from "../../hooks/usePost";
import Btns from "../btns/BtnVoto";
import { useState, useEffect } from "react";
import AtualizarParcial from "./AtualizarParcial";
import Erro from "../errocorreto/Erro";
const Voto = ({ idAssociado, idPauta, setAbriModalVoto, setAbrirMoldalVotacao, setMostrarImg, setEditar }) => {
    const [idVotacao, setIdVotacao] = useState("");

    const urlVotacao = `http://localhost:8080/v1/votacoes/pauta/${idPauta}`;
    const urlVotar = `http://localhost:8080/v1/votos/${idAssociado}/${idVotacao}`;
    
    const { httpConfig, carregamento, erro, limparErro } = usePost();

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
            setTotalVotos(dados.quantidade_votos)
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
        <article className="caixa-votacao-aberta">
            <h3>{pauta.titulo}</h3>
            <p>Criada por <strong>{pauta.autor}</strong></p>
            {carregamento ? (
                <p>Carregando...</p>
            ) : (
                <>
                    <AtualizarParcial idVotacao={idVotacao}/>
                    <div className="botoes-opcoes">
                        <button className="botao-votar-um" onClick={computarVotoSim}>SIM</button>
                        <button className="botao-votar-dois" onClick={computarVotoNao}>NÃO</button>
                    </div>
                    <p>Total de pessoas que votaram: {totalVotos}</p>
                    {erro && erro.status && erro && erro.mensagem  && <Erro status={erro.status} mensagem={erro.mensagem}/>}
                    <Btns setAbriModalVoto={setAbriModalVoto} setAbrirMoldalVotacao={setAbrirMoldalVotacao} setMostrarImg={setMostrarImg} pauta={pauta} idAssociado={idAssociado} setEditar={setEditar} />

                </>

            )}

        </article>

    )

}

export default Voto;