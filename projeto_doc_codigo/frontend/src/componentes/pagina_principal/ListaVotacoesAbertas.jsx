import "../../styles/MenuPrincipal.css";
import React, { useState, useEffect } from "react";
import CriarVotacao from "./CriarVotacao";
import Votar from "./Voto";
import { useGet } from "../../hooks/useGet";
const ListaVotacoesAbertas = ({id}) => {
    const urlPautas = "http://localhost:8080/v1/pautas";
   
    const { dados } = useGet(urlPautas);
    const [votacoesAbertas, setVotacaoAbertas] = useState([]);
    const [abrirMoldalVotacao, setAbriModalVotacao] = useState(false);
    const [abrirModalVoto, setAbriModalVoto] = useState(false);
    const [idPauta, setIdPauta] = useState("");
    const abrirVotacao = () => {
        if (!abrirMoldalVotacao) {
            setAbriModalVoto(false)
            setAbriModalVotacao(true);
        } else {
            setAbriModalVotacao(false);
        }
    }
    const votar = (id) =>{

        if(!abrirModalVoto ){
            setIdPauta("")
            setAbriModalVoto(true);
          
            setIdPauta(id)
        }else{
            setAbriModalVoto(false);

        }
    }

    useEffect(() => {
        setVotacaoAbertas([])
        if (dados) {
            setVotacaoAbertas(dados.content);
        }
    }, [dados]);

    return (
        <section className="conteudo-principal">
            <article className="texto-votacoes">
                <h3>Votações em Aberto</h3>
                <p>Selecione o seu interesse e faça sua votação</p>
            </article>
            <article className="caixa-votacoes">
                <ul className="lista-votacoes">
                    {votacoesAbertas.map((votacao) => (
                        <li key={votacao.id} className="item-votacao" onClick={() => votar(votacao.id)}>
                            <strong>{votacao.titulo}</strong>
                            <div className="subinfo">
                                {votacao.descricao}
                            </div>
                        </li>
                    ))}
                    <li className="botao-nova-votacao" onClick={abrirVotacao}>Criar Nova Votação</li>
                </ul>
            </article>

            {abrirMoldalVotacao && (<CriarVotacao id={id}/>)}
            {abrirModalVoto && (<Votar idAssociado={id} idPauta = {idPauta}/>)}

        </section>
    )
}

export default ListaVotacoesAbertas;