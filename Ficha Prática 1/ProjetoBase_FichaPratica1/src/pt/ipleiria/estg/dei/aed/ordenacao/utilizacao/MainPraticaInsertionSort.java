package pt.ipleiria.estg.dei.aed.ordenacao.utilizacao;

import pt.ipleiria.estg.dei.aed.ComparacaoInteiros;
import pt.ipleiria.estg.dei.aed.ordenacao.algoritmos.InsertionSort;
import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.VetorDeInteiros;
import pt.ipleiria.estg.dei.aed.utils.visualizacao.VisualizadorEstatisticas;

import java.util.LinkedList;

public class MainPraticaInsertionSort {

    public MainPraticaInsertionSort() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>(ComparacaoInteiros.CRITERIO);
        insertionSort.getEstatistica(5, 2, 4, 6, 1, 3);

        // b)

        insertionSort.getEstatistica(VetorDeInteiros.criarAleatorioInteger(100, -100, 100, true));
        insertionSort.getEstatistica(VetorDeInteiros.criarAleatorioInteger(1000, -1000, 1000, true));
        insertionSort.getEstatistica(VetorDeInteiros.criarAleatorioInteger(10000, -10000, 10000, true));

        LinkedList<Estatistica> ests = new LinkedList<>();
        for (int i = 5; i <= 40; i += 5) {
            ests.add(insertionSort.getEstatistica(VetorDeInteiros.criarAleatorioInteger(i, -i, i, true)));
        }

        VisualizadorEstatisticas vis = new VisualizadorEstatisticas();
        vis.adicionarEstatisticas("InsertionSort", ests);
        vis.visualizar();

    }

    public static void main(String[] args) {
        new MainPraticaInsertionSort();
    }
}
