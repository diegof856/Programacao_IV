import { useGet } from "../../hooks/useGet";
import { useState, useEffect } from "react";
import "../../styles/AtualizarParcial.css"
const AtualizarParcial = ({ idVotacao }) => {
    const [porcentagem, setPorcentagem] = useState([]);

    const { dados } = useGet(`http://localhost:8080/v1/votacoes/quantidadeVotos/${idVotacao}`)

    useEffect(() => {

        if (dados) {
            setPorcentagem(dados)
        }
    }, [dados]);


    return (
        <div className="barra-porcentagem">

          
                <div
                    className="barra-sim"
                    style={{ width: `${porcentagem[0]}%` }}
                >
                    {porcentagem[0]}%
                </div>
                <div
                    className="barra-nao"
                    style={{ width: `${porcentagem[1]}%` }}
                >
                    {porcentagem[1]}%
                </div>
           

        </div>
    )

}
export default AtualizarParcial;