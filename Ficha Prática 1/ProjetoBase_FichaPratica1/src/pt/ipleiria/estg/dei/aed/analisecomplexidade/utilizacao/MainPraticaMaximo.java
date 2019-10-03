package pt.ipleiria.estg.dei.aed.analisecomplexidade.utilizacao;

import pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos.Maximo;
import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.VetorDeInteiros;
import pt.ipleiria.estg.dei.aed.utils.visualizacao.VisualizadorEstatisticas;

import java.util.LinkedList;

public class MainPraticaMaximo {

    public MainPraticaMaximo() {
        Maximo maximo = new Maximo();

        maximo.getEstatistica(VetorDeInteiros.criarAleatorioInt(5, -50, 50, true));
        maximo.getEstatistica(VetorDeInteiros.criarAleatorioInt(10, -50, 50, true));


        LinkedList<Estatistica> estatisticas = new LinkedList<>();

        for (int i = 10; i < 501; i+= 10) {
            estatisticas.add(maximo.getEstatistica(VetorDeInteiros.criarAleatorioInt(i, true)));
        }

        VisualizadorEstatisticas vis = new VisualizadorEstatisticas();
        vis.adicionarEstatisticas("Máximo", estatisticas);
        vis.visualizar();
    }

    public static void main(String[] args) {
        new MainPraticaMaximo();
    }

}
