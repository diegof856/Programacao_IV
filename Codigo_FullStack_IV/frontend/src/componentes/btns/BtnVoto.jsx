import { useDelete } from "../../hooks/useDelete";

const BtnVoto = ({ setEditar, setAbrirMoldalVotacao, setAbriModalVoto, setMostrarImg, pauta, idAssociado }) => {
    const { httpConfigDeletar } = useDelete();

    const editarVotacao = () => {
        setAbrirMoldalVotacao(false)
        setAbriModalVoto(false)
        setEditar(true)
    }
    const voltar = () => {
        if (setAbriModalVoto) {
            setAbrirMoldalVotacao(false)
            setAbriModalVoto(false);
            setMostrarImg(true);
        }
    }
    const deletarVotacao = () => {
        httpConfigDeletar(pauta.id, "DELETE")
    }


    return (

        <div className="botoes-acoes espacamento-superior">

            <button onClick={voltar}>voltar</button>

            {pauta.idAutor === idAssociado && (
                <>
                    <button className="btn-editar" type = "button" onClick={editarVotacao}>Editar</button>
                    <button className="btn-deletar" onClick={() => {
                        if (window.confirm("Tem certeza que deseja deletar esta votação?")) {
                            deletarVotacao();
                        }
                    }}>Deletar</button>
                </>
            )}
        </div>
    )

}
export default BtnVoto;