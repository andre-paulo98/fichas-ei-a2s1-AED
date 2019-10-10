package pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos;

import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeComparacoes;
import pt.ipleiria.estg.dei.aed.utils.Par;

import java.awt.geom.Point2D;

public class ParDePontosMaisProximos {

    public Par<Point2D, Point2D> executar(EstatisticaDeComparacoes est, Point2D... pontos) {

        if (pontos.length < 2) {
            return null;
        }

        if(pontos.length == 2) {
            return new Par<>(pontos[0], pontos[1]);
        }

        int indiceP1 = 0, indiceP2 = 0;
        double minimaDistancia = Double.MAX_VALUE;

        for (int i = 0; i < pontos.length - 1; i++) {
            for (int j = i + 1; j < pontos.length; j++) {

                double distancia = pontos[i].distanceSq(pontos[j]);

                est.incrementarComparacoes();
                if (distancia < minimaDistancia) {
                    indiceP1 = i;
                    indiceP2 = j;
                    minimaDistancia = distancia;
                }
            }
        }

        return new Par<>(pontos[indiceP1], pontos[indiceP2]);

    }

    public EstatisticaDeComparacoes getEstatistica(Point2D... pontos) {


        EstatisticaDeComparacoes est = new EstatisticaDeComparacoes(pontos.length);

        var ponto = executar(est, pontos);

        est.parar();

        System.out.println("O par de pontos mais próximo é: (" + ponto.getPrimeiro().getX() + ", " + ponto.getPrimeiro().getY() + ")" +
                " (" + ponto.getSegundo().getX() + ", " + ponto.getSegundo().getY() + "). Distância = " + ponto.getPrimeiro().distance(ponto.getSegundo()));

        est.apresentar();

        return est;

    }
}
