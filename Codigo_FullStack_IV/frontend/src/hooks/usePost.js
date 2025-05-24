import { useEffect, useState } from "react";

export const usePost = () => {
    const [config, setConfig] = useState(null);
    const [metodo, setMetodo] = useState(null);
    const [url, setUrl] = useState(null);
    const [carregamento, setCarregamento] = useState(false);
    const [erro, setErro] = useState(null);
    const [requisicaoCorreta,setRequisicaoCorreta] = useState(false);
    const [resposta, setResposta] = useState(null)

    const httpConfig = (dados, metodo, novaUrl) => {

        setConfig({
            method: metodo,
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(dados)
        })
        setMetodo(metodo);
        setUrl(novaUrl);
    };
     const limparErro = () => {
        setErro(null);
      
    };
    useEffect(() => {
        const httpRequest = async () => {
            if (!config) return;
         
           
            try {
                const requisicao = await fetch(url, config);
                 
                if (!requisicao.ok) {
                  
                    const erroTexto = await requisicao.json();

                    throw { mensagem: erroTexto.mensagem, status: erroTexto.status };
                }
                setRequisicaoCorreta(true)
            
                setResposta(await requisicao.json());
               
            } catch (error) {
                setErro({
                    mensagem: error.mensagem,
                    status: error.status
                });
                setRequisicaoCorreta(false)
                return null;
            } finally {
                setCarregamento(false);
            }

        };
        httpRequest();
    }, [config, metodo, url])
    return { httpConfig, carregamento, erro, limparErro, resposta,requisicaoCorreta }
};