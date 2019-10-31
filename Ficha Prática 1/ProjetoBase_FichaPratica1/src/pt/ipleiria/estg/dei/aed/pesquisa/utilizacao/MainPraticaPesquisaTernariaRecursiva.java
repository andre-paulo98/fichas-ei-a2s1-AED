package pt.ipleiria.estg.dei.aed.pesquisa.utilizacao;

import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.ComparacaoInteiros;
import pt.ipleiria.estg.dei.aed.ordenacao.algoritmos.QuickSort;
import pt.ipleiria.estg.dei.aed.pesquisa.algoritmos.PesquisaBinaria;
import pt.ipleiria.estg.dei.aed.pesquisa.algoritmos.PesquisaTernariaIterativa;
import pt.ipleiria.estg.dei.aed.pesquisa.algoritmos.PesquisaTernariaRecursiva;
import pt.ipleiria.estg.dei.aed.utils.Vetor;
import pt.ipleiria.estg.dei.aed.utils.VetorDeInteiros;

import java.util.Random;

public class MainPraticaPesquisaTernariaRecursiva {

    public MainPraticaPesquisaTernariaRecursiva() {/*
        PesquisaTernariaRecursiva<Integer> pesquisaTernariaRecursiva = new PesquisaTernariaRecursiva<>(ComparacaoInteiros.CRITERIO);
        pesquisaTernariaRecursiva.getEstatistica(7, 7);
        pesquisaTernariaRecursiva.getEstatistica(7, 1, 2, 4, 5, 6, 7, 8, 9);
        pesquisaTernariaRecursiva.getEstatistica(3, 1, 2, 4, 5, 6, 7, 8, 9);*/


        Random r = new Random();
        QuickSort<Integer> quickSort = new QuickSort<>(ComparacaoInteiros.CRITERIO);
        PesquisaTernariaIterativa<Integer> pesquisaTernaria = new PesquisaTernariaIterativa<>(ComparacaoInteiros.CRITERIO);

        Integer[] elementos = VetorDeInteiros.criarAleatorioInteger(10, -10, 10, true);
        quickSort.getEstatistica(elementos);
        pesquisaTernaria.getEstatistica(r.nextInt(21) - 10, elementos);

        elementos = VetorDeInteiros.criarAleatorioInteger(10, -10, 10, true);
        quickSort.getEstatistica(elementos);
        pesquisaTernaria.getEstatistica(r.nextInt(21) - 10, elementos);
    }

    public static void main(String[] args) {
        new MainPraticaPesquisaTernariaRecursiva();
    }
}
