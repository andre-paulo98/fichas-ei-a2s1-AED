package pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores;

import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.modelo.contactos.Contacto;

public enum ComparacaoContactosPrimNomeAscMoradaDesc implements Comparacao<Contacto> {
    CRITERIO;

    @Override
    public int comparar(Contacto o1, Contacto o2) {
        int comparacao = o1.getPrimeiroNome().compareTo(o2.getPrimeiroNome());
        if(comparacao != 0){
            return comparacao;
        }
        return -o1.getMorada().compareTo(o2.getMorada());
    }
}
