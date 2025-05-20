/* <<<<<PODERA SER USADO >>>>>>>
import { useGet } from "../../hooks/useGet";
import React, { useState, useEffect } from "react";
const Votacao = () =>{
    const url = "http://localhost:8080/v1/votacoes";
    const [votacoes, setVotacoes] = useState([]);
   
    const { dados } = useGet(url);
     useEffect(() => {
            
            if (dados) {
                setVotacoes(dados.content);
               
            }
        }, [dados]);
       

        return(
            <section className="conteudo-principal">
                  <article className="texto-votacoes">
                <h3>Votações Computadas até o Momento</h3>
                <p>Selecione a de seu interesse</p>
            </article>
                <article className="caixa-votacoes">
                    <ul className="lista-votacoes">
                  {votacoes.map((votacao) =>(
                        
                    <li key={votacao.id} className="item-votacao">
                        <p>{votacao.pauta.titulo}</p>
                        <div className="subinfo" >
                            <p>Quantidades de votos:{votacao.Quantidade_Votos}</p>
                            <p>{votacao.staus_Votacao}</p>
                            <div>
                               <ul className="lista-votos">
                                {votacao.votos?.map((voto) =>(
                                    <li key = {voto.idVoto}>
                                        <p><strong>Associado:</strong> {voto.associado.nome}</p>
                                         <p><strong>Voto:</strong> {voto.voto}</p>
                                    </li>
                                ))}
                               </ul>
                            </div>
                        </div>
                    </li>
                  ))}  

                    </ul>
                </article>
            </section>
        )
}
export default Votacao;*/