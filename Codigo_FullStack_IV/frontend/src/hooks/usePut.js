import { useEffect, useState } from "react";

export const usePut = (url) => {
    const [config, setConfig] = useState(null);
    
    const [carregamento, setCarregamento] = useState(false);
    const [erro, setErro] = useState(null);
   
    const httpPutConfig = async (dados) => {
        setErro(null);
        
        setConfig({
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify(dados)
        })
       
        

    }
    
    const limparErro = () => setErro("");
    useEffect(() => {

        const httpRequest = async () => {
          
            setCarregamento(true);
   
   
           
            try {
                const requisicao = await fetch(url, config);
                if (!requisicao.ok) {
                    let erroRequisicao = await requisicao.json();
                    throw { mensagem: erroRequisicao.mensagem, status: erroRequisicao.status }
                }
                return true

            } catch (error) {
                setErro({
                    mensagem: error.mensagem,
                    status: error.status
                });
                return false
            }
            finally {
                setCarregamento(false);
            }

        };
        httpRequest();
    }, [config, url])
    console.log(erro)
    return { httpPutConfig, carregamento, erro, limparErro }

}

