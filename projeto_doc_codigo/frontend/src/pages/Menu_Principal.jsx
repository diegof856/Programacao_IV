import "../styles/MenuPrincipal.css";
import { useParams } from "react-router-dom";
import NomeUsuario from "../componentes/pagina_principal/NomeUsuario";
import ImagemLateral from "../componentes/pagina_principal/ImagemLateral";
import ListaVotacoesAbertas from "../componentes/pagina_principal/ListaVotacoesAbertas";
import Votacao from "../componentes/pagina_principal/Votacao";
export const MenuPrincipal = () => {
    const { id } = useParams();


    return (
        <div className="menu">
            <header>
                <NomeUsuario id={id} />
            </header>
            <main>
                <ImagemLateral />
                <ListaVotacoesAbertas id={id} />
                <Votacao/>
            </main>
            <footer>

            </footer>
        </div>

    );
};