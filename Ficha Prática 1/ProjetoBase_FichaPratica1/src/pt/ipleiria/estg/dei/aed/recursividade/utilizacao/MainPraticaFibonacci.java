package pt.ipleiria.estg.dei.aed.recursividade.utilizacao;

import pt.ipleiria.estg.dei.aed.recursividade.algoritmos.Fibonacci;
import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.visualizacao.VisualizadorEstatisticas;

import java.util.LinkedList;

public class MainPraticaFibonacci {

    // -Djava.compiler=NONE
    public MainPraticaFibonacci() {
        Fibonacci fibonacci = new Fibonacci();

        int[] ns = {5};

        for (int n : ns) {
            fibonacci.getEstatistica(n);
        }

        LinkedList<Estatistica> estatisticas = new LinkedList<>();
        for (int i = 5; i <= 20; i+=3) {
            estatisticas.add(fibonacci.getEstatistica(i));
        }

        VisualizadorEstatisticas visualizador = new VisualizadorEstatisticas();
        visualizador.adicionarEstatisticas("Fibonacci", estatisticas);
        visualizador.visualizar();



    }

    public static void main(String[] args) {

        new MainPraticaFibonacci();

    }
}
