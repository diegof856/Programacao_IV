import "../styles/MenuPrincipal.css";
import { useParams } from "react-router-dom";
import NomeUsuario from "../componentes/pagina_principal/NomeUsuario";
import ImagemLateral from "../componentes/pagina_principal/ImagemLateral";
import ListaVotacoesAbertas from "../componentes/pagina_principal/ListaVotacoesAbertas";
import Votacao from "../componentes/pagina_principal/Votacao";
import Footer from "../componentes/footer/Footer"
import { useState } from "react";
export const MenuPrincipal = () => {
    const { id } = useParams();
    
    const idNumero = Number(id)
    const [mostrarImg, setMostrarImg] = useState(true);

    return (
        <div className="menu">
            <header>
                <NomeUsuario id={id} />
            </header>
            <main>
                {mostrarImg && <ImagemLateral />}

                <ListaVotacoesAbertas id={idNumero} setMostrarImg={setMostrarImg} />
                {
                    /*<Votacao />*/
                }
            </main>

            <footer className="rodape">
                <Footer />
            </footer>
        </div>

    );
};