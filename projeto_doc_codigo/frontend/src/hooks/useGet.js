import { useEffect, useState } from "react";

export const useGet = (url) => {
    const [dados, setDados] = useState(null);

useEffect(() =>{
    const buscarDados = async () =>{
        const requisicao = await fetch(url);
        const json = await requisicao.json();
        setDados(json);
    }
    buscarDados();
},[url])
return {dados}
};