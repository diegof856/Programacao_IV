import React, { useState, useEffect } from "react";
import "../styles/MenuPrincipal.css";
import icon from "../assets/icon.png";
import lateralImage from "../assets/menu.png";
import { useParams } from "react-router-dom";
import { useGet } from "../hooks/useGet";
import { usePost } from "../hooks/usePost"; 

export const MenuPrincipal = () => {
    const { id } = useParams();
    const urlUsuario = `http://localhost:8080/v1/associados/${id}`;
    const urlPautas = "http://localhost:8080/v1/pautas/todasPautas";
    const urlCriarPauta = "http://localhost:8080/v1/pautas"; // URL para criar pautas
    const { dadosUsuario, carregamento: carregandoUsuario, erro: erroUsuario } = useGet(urlUsuario);
    const { dados: dadosPautas, carregamento: carregandoPautas, erro: erroPautas } = useGet(urlPautas);
    const { httpConfig, carregamento: carregandoCriacao, erro: erroCriacao, resposta: respostaCriacao, limparErro } = usePost();

    const [nomeUsuario, setNomeUsuario] = useState(null);
    const [votacaoAberta, setVotacaoAberta] = useState(null);
    const [votacoes, setVotacoes] = useState([]);

   

    useEffect(() => {
        if (dadosUsuario) {
            setNomeUsuario(dadosUsuario.nome);
        }
    }, [dadosUsuario]);

    useEffect(() => {
        if (dadosPautas && dadosPautas.content) {
            const pautasFormatadas = dadosPautas.content.map((pauta) => ({
                id: pauta.id,
                titulo: pauta.titulo,
                descricao: pauta.descricao,
                data_criacao: pauta.data_criacao,
                status_Pauta: pauta.status_Pauta,
                Tempo_Votacao: pauta.Tempo_Votacao,
                autor: "Sistema",
                votosOpcao1: 0,
                votosOpcao2: 0,
                opcao1: "Sim",
                opcao2: "Não",
                usuariosVotaram: [],
            }));
            setVotacoes(pautasFormatadas);
        }
    }, [dadosPautas]);

    const [criandoVotacao, setCriandoVotacao] = useState(false);
    const [editandoVotacaoIndex, setEditandoVotacaoIndex] = useState(null);
    const [novaVotacao, setNovaVotacao] = useState({ titulo: "", descricao: "", tempoVotacao: "" }); 
    const [mensagemVoto, setMensagemVoto] = useState("");
    const [tipoMensagem, setTipoMensagem] = useState("");
    const [mensagemCriacao, setMensagemCriacao] = useState(""); 
    const [tipoMensagemCriacao, setTipoMensagemCriacao] = useState(""); 

    

    const iniciarCriacao = () => {
        setNovaVotacao({ titulo: "", descricao: "", tempoVotacao: "" }); 
        setCriandoVotacao(true);
        setEditandoVotacaoIndex(null);
        setMensagemVoto("");
        setTipoMensagem("");
        setMensagemCriacao("");
        setTipoMensagemCriacao("");
        limparErro(); 
    };
       const abrirVotacao = (index) => {
        setMensagemVoto("");
        setTipoMensagem("");
        setVotacaoAberta(index);
    };


    const salvarNovaVotacao = () => {
        if (!novaVotacao.titulo || !novaVotacao.descricao || !novaVotacao.tempoVotacao) {
            setMensagemCriacao("Preencha todos os campos.");
            setTipoMensagemCriacao("erro");
            return;
        }

        const novaPautaParaEnviar = {
            titulo: novaVotacao.titulo,
            descricao: novaVotacao.descricao,
            tempoVotacao: parseInt(novaVotacao.tempoVotacao, 10), 
        };

        httpConfig(novaPautaParaEnviar, "POST", urlCriarPauta);
    };

    useEffect(() => {
        if (respostaCriacao) {
            setMensagemCriacao("Pauta criada com sucesso!");
            setTipoMensagemCriacao("sucesso");
          
            setTimeout(() => {
                setCriandoVotacao(false);
                
            }, 2000);
        }

        if (erroCriacao) {
            setMensagemCriacao(`Erro ao criar pauta: ${erroCriacao.mensagem}`);
            setTipoMensagemCriacao("erro");
        }
    }, [respostaCriacao, erroCriacao]);

    const cancelarCriacao = () => {
        setCriandoVotacao(false);
        setMensagemCriacao("");
        setTipoMensagemCriacao("");
        limparErro();
    };



    return (
        <div className="menu">
            {}

            {!criandoVotacao && votacaoAberta === null && (
                <div className="imagem-lateral">
                    <img src={lateralImage} alt="Descrição visual" />
                </div>
            )}

            {criandoVotacao ? (
                <div className="caixa-votacao-aberta">
                    <h3>Nova Votação</h3> {}
                    <input
                        type="text"
                        placeholder="Título da Votação"
                        value={novaVotacao.titulo}
                        onChange={(e) => setNovaVotacao({ ...novaVotacao, titulo: e.target.value })}
                    />
                    <textarea 
                        placeholder="Descrição da Votação"
                        value={novaVotacao.descricao}
                        onChange={(e) => setNovaVotacao({ ...novaVotacao, descricao: e.target.value })}
                    />
                    <input
                        type="number" 
                        placeholder="Tempo de Votação (minutos)"
                        value={novaVotacao.tempoVotacao}
                        onChange={(e) => setNovaVotacao({ ...novaVotacao, tempoVotacao: e.target.value })}
                    />
                    {mensagemCriacao && <div className={`mensagem-voto ${tipoMensagemCriacao}`}>{mensagemCriacao}</div>}

                    <div className="botoes-acoes">
                        <button className="btn-editar" onClick={salvarNovaVotacao} disabled={carregandoCriacao}>
                            {carregandoCriacao ? "Criando..." : "Salvar"}
                        </button>
                        <button className="btn-voltar" onClick={cancelarCriacao} disabled={carregandoCriacao}>
                            Cancelar
                        </button>
                    </div>
                </div>
            ) : votacaoAberta !== null ? (
                <div className="caixa-votacao-aberta">
                    {}
                </div>
            ) : (
                <div className="conteudo-principal">
                    <div className="texto-votacoes">
                        <h3>Votações em Aberto</h3>
                        <p>Selecione o seu interesse e faça sua votação</p>
                    </div>
                    <div className="caixa-votacoes">
                        <ul className="lista-votacoes">
                            {votacoes.map((votacao, index) => (
                                <li key={votacao.id || index} onClick={() => abrirVotacao(index)} className="item-votacao">
                                    <strong>{votacao.titulo}</strong>
                                    <div className="subinfo">
                                        Por: {votacao.autor} • {votacao.usuariosVotaram.length} votos
                                    </div>
                                </li>
                            ))}
                            <li className="botao-nova-votacao" onClick={iniciarCriacao} disabled={carregandoCriacao}>
                                Criar Nova Votação
                            </li>
                        </ul>
                    </div>
                </div>
            )}
        </div>
    );
};