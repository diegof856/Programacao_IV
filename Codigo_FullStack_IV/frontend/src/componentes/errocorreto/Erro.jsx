const Erro = ({status, mensagem}) =>{

return(
    <div className="mensagem-voto erro">
          <p>Erro:{mensagem}, Status:{status} </p>
    </div>
)
}
export default Erro;