package pt.ipleiria.estg.dei.aed.modelo;

import pt.ipleiria.estg.dei.aed.ComparacaoLimite;

public enum ComparacaoLimiteDataAsc implements ComparacaoLimite<Data> {
    CRITERIO;

    private static final Data LIMITE = new Data(0, 0, Integer.MAX_VALUE);

    @Override
    public Data getLimite() {
        return LIMITE;
    }

    @Override
    public int comparar(Data o1, Data o2) {
        return o1.compareTo(o2);
    }
}
