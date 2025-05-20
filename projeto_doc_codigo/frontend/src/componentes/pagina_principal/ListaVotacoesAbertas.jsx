import "../../styles/MenuPrincipal.css";
import React, { useState, useEffect } from "react";
import CriarVotacao from "./CriarVotacao";
import Votar from "./Voto";
import { useGet } from "../../hooks/useGet";
const ListaVotacoesAbertas = ({ id, setMostrarImg }) => {
    const urlPautas = "http://localhost:8080/v1/pautas";

    const { dados } = useGet(urlPautas);
    const [votacoesAbertas, setVotacaoAbertas] = useState([]);
    const [abrirMoldalVotacao, setAbriModalVotacao] = useState(false);
    const [abrirModalVoto, setAbriModalVoto] = useState(false);
    const [idPauta, setIdPauta] = useState("");
    useEffect(() => {
        setVotacaoAbertas([])
        if (dados) {
            setVotacaoAbertas(dados.content);
        }
    }, [dados]);
    const abrirVotacao = () => {
        if (!abrirMoldalVotacao) {
            setAbriModalVoto(false)
            setAbriModalVotacao(true);
            setMostrarImg(false);
        } else {
            setAbriModalVotacao(false);

        }
    }



    const votar = (idPauta) => {

        if (!abrirModalVoto) {
            setIdPauta("")
            setAbriModalVoto(true);
            setMostrarImg(false)
           
            setIdPauta(id)
        } else {
            setAbriModalVoto(false);

        }
    }

    return (

        <section className="conteudo-principal">
            {!abrirMoldalVotacao & !abrirModalVoto && <article className="texto-votacoes">
                <h3>Votações em Aberto</h3>
                <p>Selecione o seu interesse e faça sua votação</p>
            </article>}
            {!abrirMoldalVotacao && !abrirModalVoto && <article className="caixa-votacoes">
                <ul className="lista-votacoes">
                    {votacoesAbertas.map((votacao) => (
                        <li key={votacao.id} className="item-votacao" onClick={() => votar(votacao.id)}>
                            <strong>{votacao.titulo}</strong>
                            <div className="subinfo">
                                <p>Por: {votacao.autor} • Descrição Da Votação:{votacao.descricao}</p>
                            </div>
                        </li>
                    ))}
                    <li className="botao-nova-votacao" onClick={abrirVotacao}>Criar Nova Votação</li>
                </ul>
            </article>}



            {abrirMoldalVotacao && (<CriarVotacao id={id} setAbriModalVotacao={setAbriModalVotacao} setMostrarImg={setMostrarImg} />)}
            {abrirModalVoto && (<Votar idAssociado={id} idPauta={idPauta} setAbriModalVoto={setAbriModalVoto} setMostrarImg={setMostrarImg} setAbrirMoldalVotacao={setAbriModalVotacao}/>)}

        </section>
    )
}

export default ListaVotacoesAbertas;