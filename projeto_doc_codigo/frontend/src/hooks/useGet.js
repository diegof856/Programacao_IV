import { useEffect, useState } from "react";

export const useGet = (url) => {
    const [dados, setDados] = useState(null);
    const [carregamento,setCarregamento] = useState(false);
useEffect(() =>{

    const buscarDados = async () =>{
        setCarregamento(true);
        const requisicao = await fetch(url);
        const json = await requisicao.json();
        setDados(json);
        setCarregamento(false)
    }
    buscarDados();
},[url])
return {dados, carregamento}
};