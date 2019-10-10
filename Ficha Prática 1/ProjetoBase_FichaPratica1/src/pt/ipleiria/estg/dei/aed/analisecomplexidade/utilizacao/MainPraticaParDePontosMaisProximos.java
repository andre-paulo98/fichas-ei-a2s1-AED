package pt.ipleiria.estg.dei.aed.analisecomplexidade.utilizacao;

import pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos.ParDePontosMaisProximos;
import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeComparacoes;
import pt.ipleiria.estg.dei.aed.utils.VetorDePoint2D;
import pt.ipleiria.estg.dei.aed.utils.visualizacao.VisualizadorEstatisticas;

import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainPraticaParDePontosMaisProximos {

    public static void main(String[] args) {

        int ns[] = {5, 10, 15};

        List<Estatistica> listEst = new ArrayList<>();
        for (int n : ns) {
            Point2D[] resultado = VetorDePoint2D.criarAleatorio(n);

            ParDePontosMaisProximos parDePontosMaisProximos = new ParDePontosMaisProximos();
            listEst.add(parDePontosMaisProximos.getEstatistica(resultado));
        }


        VisualizadorEstatisticas vis = new VisualizadorEstatisticas();
        vis.adicionarEstatisticas("Máximo", listEst);
        vis.visualizar();
    }
}
