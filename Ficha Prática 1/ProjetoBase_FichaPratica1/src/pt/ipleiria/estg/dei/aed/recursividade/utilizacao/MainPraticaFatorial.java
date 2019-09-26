package pt.ipleiria.estg.dei.aed.recursividade.utilizacao;

import pt.ipleiria.estg.dei.aed.recursividade.algoritmos.Fatorial;
import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadas;
import pt.ipleiria.estg.dei.aed.utils.visualizacao.VisualizadorEstatisticas;

import java.util.LinkedList;

public class MainPraticaFatorial {
    // -Djava.compiler=NONE
    public MainPraticaFatorial() {
        Fatorial fatorial = new Fatorial();

//        int[] ns = {20, 20, 5, 0, -5, 21};
//
//        for (int n : ns) {
//            fatorial.getEstatistica(n);
//        }

        LinkedList<Estatistica> estatisticas = new LinkedList<>();
        for (int i = 1; i < 101; i++) {
            estatisticas.add(fatorial.getEstatistica(i));
        }

        VisualizadorEstatisticas visualizador = new VisualizadorEstatisticas();
        visualizador.adicionarEstatisticas("Fatorial", estatisticas);
        visualizador.visualizar();



    }

    public static void main(String[] args) {

        new MainPraticaFatorial();

    }


}
