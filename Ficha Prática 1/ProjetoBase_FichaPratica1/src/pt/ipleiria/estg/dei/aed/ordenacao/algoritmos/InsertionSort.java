package pt.ipleiria.estg.dei.aed.ordenacao.algoritmos;

import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeComparacoesETrocas;

public class InsertionSort<T> extends AlgoritmoOrdenacao<T> {

    public InsertionSort(Comparacao<T> criterio) {
        super(criterio);
    }

    public void ordenar(EstatisticaDeComparacoesETrocas estatistica, T... elementos) {
        for (int i = 1; i < elementos.length; i++) {
            T saved = elementos[i];
            int j;
            estatistica.incrementarComparacoes();
            for (j = i - 1; j >= 0; j--) {
                if (criterio.comparar(elementos[j], saved) <= 0) {
                    break;
                }
                elementos[j + 1] = elementos[j];
                estatistica.incrementarTrocas();
            }
            elementos[j + 1] = saved;

        }

    }

}
