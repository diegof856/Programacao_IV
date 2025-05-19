import React, { useState, useEffect } from "react";
import "../styles/style.css";
import login from "../assets/login.png";
import icon from "../assets/icon.png";
import { usePost } from "../hooks/usePost";
import { useNavigate } from "react-router-dom";
import "../styles/nav.css"
export const LoginCadastro = () => {
    const [modoCadastro, setModoCadastro] = useState(false);
    const [nome, setNome] = useState("");
    const [cpf, setCpf] = useState("");
    const [senha, setSenha] = useState("");
    const [mensagemErro, setMensagemErro] = useState("");

    const { httpConfig, carregamento, erro, limparErro, resposta } = usePost();
    const url = modoCadastro
        ? "http://localhost:8080/v1/associados"
        : "http://localhost:8080/v1/associados/login";
    const navegar = useNavigate();

    const alternarModo = () => {
        setModoCadastro(!modoCadastro);
        setMensagemErro("");
        limparErro()
        setNome("");
        setCpf("");
        setSenha("");

    };

    const realizarEnvio = () => {

        if (modoCadastro && !nome) {
            setMensagemErro("Nome obrigatório.");
            return;
        }
        if (!cpf) {
            setMensagemErro("CPF obrigatório.");
            return;
        }
        if (!senha) {
            setMensagemErro("Senha obrigatória.");
            return;
        }
        const requisicao = {
            nome,
            cpf,
            senha
        }

        httpConfig(requisicao, "POST", url);
        if (!modoCadastro) {
            realizarLogin(url);
        }


    };
    const realizarLogin = (url) => {

        const requisicao = {
            cpf,
            senha
        }

        httpConfig(requisicao, "POST", url)

    };
    useEffect(() => {
        if (resposta && !modoCadastro) {
            navegar(`/MainPage/${resposta.id}`);

        }
    }, [resposta]);



    return (
        <div className="login">
            <div className="conteudo">

                {/*  LADO ESQUERDO - Imagem */}
                <div className="lado-esquerdo">
                    <img src={login} alt="LoginCadastro" style={{ width: "100vh" }} />
                </div>

                {/*  LADO DIREITO - Formulário */}
                <div className="lado-direito">
                    <img src={icon} alt="LoginCadastro" style={{ width: "65px" }} />
                    <div className="formulario">

                        {/*  Campo Nome */}
                        {modoCadastro && (
                            <div className="campo">
                                <div className="rotulo">Nome</div>
                                <input
                                    className={`entrada ${mensagemErro && !nome ? "erro" : ""}`}
                                    type="text"
                                    placeholder="Nome completo"
                                    autoComplete="off"
                                    value={nome}
                                    onChange={(e) => setNome(e.target.value)}
                                />
                                <div className="mensagem-erro-campo">
                                    {mensagemErro && !nome ? "Nome obrigatório." : ""}
                                </div>
                            </div>
                        )}

                        {/*  Campo CPF */}
                        <div className="campo">
                            <div className="rotulo">CPF</div>
                            <input
                                className={`entrada ${mensagemErro && (!cpf) ? "erro" : ""}`}
                                type="text"
                                placeholder="Digite seu CPF"
                                autoComplete="off"
                                value={cpf}
                                onChange={(e) => setCpf(e.target.value)}
                            />
                            <div className="mensagem-erro-campo">
                                {mensagemErro && (!cpf) ? "CPF inválido." : ""}
                            </div>
                        </div>

                        {/* Campo Senha */}
                        <div className="campo">
                            <div className="rotulo">Senha</div>
                            <input
                                className={`entrada ${mensagemErro && (!senha) ? "erro" : ""}`}
                                type="password"
                                placeholder="Digite sua senha"
                                value={senha}
                                onChange={(e) => setSenha(e.target.value)}
                            />
                            <div className="mensagem-erro-campo">
                                {mensagemErro && (!senha) ? "Senha incorreta." : ""}
                            </div>
                        </div>

                        {/* Link "Esqueceu a senha" */}
                        {!modoCadastro && (
                            <div className="link-cadastro">
                                <div className="descricao">Esqueceu a senha ?</div>
                            </div>
                        )}

                        {/*  Botão */}
                        <div className="grupo-botao">
                            {carregamento ? (
                                <button className="botao" disabled>
                                    <span className="texto-botao">
                                        Aguarde {modoCadastro ? "Cadastrar" : "Acessar"}
                                    </span>
                                </button>
                            ) : (
                                <button className="botao" onClick={realizarEnvio}>
                                    <span className="texto-botao">
                                        {modoCadastro ? "Cadastrar" : "Acessar"}
                                    </span>
                                </button>
                            )}


                        </div>
                    </div>

                    {/*  Alternar modo */}
                    <div className="texto-recuperar" onClick={alternarModo} >
                        {modoCadastro ? "Já tenho uma conta" : "Cadastre-se"}
                    </div>
                    {erro && (
                        <div className="mensagem-erro-geral">
                            <p>{erro.status}</p>
                            <p> {erro.mensagem}</p>

                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};
