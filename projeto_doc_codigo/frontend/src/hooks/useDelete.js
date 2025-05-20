import { useState,  useEffect } from "react";

export const useDelete = () => {
    const [config, setConfig] = useState(null);
    const [metodo, setMetodo] = useState(null);
    const [url, setUrl] = useState(null);
    const urlBase = "http://localhost:8080/v1/pautas/"
    const httpConfigDeletar = (idPauta, metodo) => {
        let deletarUrl = `${urlBase}` / `${idPauta}`
        setConfig({
            method: metodo,
            headers: {
                "Content-type": "application/json"
            },

        })
        setUrl(deletarUrl);
        setMetodo(metodo);
    }
    useEffect(() => {
        const httpRequest = async () => {
            if (!config) return;
            await fetch(url, config);

        };
        httpRequest();
    }, [config, metodo, url])
    return { httpConfigDeletar }
}
export default useDelete;