package pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores;

import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.modelo.contactos.Contacto;

public enum ComparacaoContactosTelefoneDescendente implements Comparacao<Contacto> {
    CRITERIO;

    @Override
    public int comparar(Contacto o1, Contacto o2) {
        //     - para devolver inverso (pois é descendente)
        return -Long.compare(o1.getNumTelefone(), o2.getNumTelefone());
    }
}
