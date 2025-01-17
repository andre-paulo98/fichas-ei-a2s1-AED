package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.ComparacaoLimite;
import pt.ipleiria.estg.dei.aed.modelo.Data;

public enum ComparacaoLimiteGestoresContactosNumaDataPorDataAsc implements ComparacaoLimite<GestorContactosNumaData> {
    CRITERIO;

    private static final GestorContactosNumaData LIMITE = new GestorContactosNumaData(new Data(0, 0, Integer.MAX_VALUE));

    @Override
    public GestorContactosNumaData getLimite() {
        return LIMITE;
    }

    @Override
    public int comparar(GestorContactosNumaData o1, GestorContactosNumaData o2) {
        return o1.getData().compareTo(o2.getData());
    }
}
