import "../../styles/MenuPrincipal.css";
import { useState, useEffect } from "react";
import CriarVotacao from "./CriarVotacao";
import EditarVotacao from "./EditarVotacao";
import Votar from "./Voto";
import { useGet } from "../../hooks/useGet";
const ListaVotacoesAbertas = ({ id, setMostrarImg }) => {
    const urlPautas = "http://localhost:8080/v1/pautas";

    const { dados } = useGet(urlPautas);
    const [votacoesAbertas, setVotacaoAbertas] = useState([]);
    const [abrirMoldalVotacao, setAbriModalVotacao] = useState(false);
    const [abrirModalVoto, setAbriModalVoto] = useState(false);
    const [idPauta, setIdPauta] = useState("");
    const [editar,setEditar] = useState(false);
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
           
            setAbriModalVoto(true);
            setMostrarImg(false)

            setIdPauta(idPauta)
        } else {
            setAbriModalVoto(false);

        }
    }
  
    return (

        <section className="conteudo-principal">
            {!abrirMoldalVotacao & !abrirModalVoto & !editar && <article className="texto-votacoes">
                <h3>Votações em Aberto</h3>
                <p>Selecione o seu interesse e faça sua votação</p>
            </article>}
            {!abrirMoldalVotacao && !abrirModalVoto & !editar && <article className="caixa-votacoes">
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

                    
            {editar && <EditarVotacao idAssociado = {id}idPauta={idPauta} setAbrirMoldalVotacao = {setAbriModalVotacao} setEditar={setEditar} setMostrarImg={setMostrarImg} />}
            {abrirMoldalVotacao && (<CriarVotacao id={id} setAbriModalVotacao={setAbriModalVotacao} setMostrarImg={setMostrarImg} />)}
            {abrirModalVoto && (<Votar idAssociado={id} idPauta={idPauta} setAbriModalVoto={setAbriModalVoto} setMostrarImg={setMostrarImg} setAbrirMoldalVotacao={setAbriModalVotacao} setEditar={setEditar}/>)}

        </section>
    )
}

export default ListaVotacoesAbertas;