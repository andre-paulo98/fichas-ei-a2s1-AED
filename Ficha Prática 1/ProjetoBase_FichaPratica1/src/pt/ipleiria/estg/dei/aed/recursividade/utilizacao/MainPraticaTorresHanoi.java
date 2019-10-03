package pt.ipleiria.estg.dei.aed.recursividade.utilizacao;

import pt.ipleiria.estg.dei.aed.recursividade.algoritmos.TorresHanoi;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadasEMovimentos;

public class MainPraticaTorresHanoi {

    public static void main(String[] args) {


        int[] ns = {3, 0, 5, 10, 15, 30, 31, 32};

        for (int i : ns) {


            int nDiscos = i;

            EstatisticaDeChamadasEMovimentos estatistica = new EstatisticaDeChamadasEMovimentos(nDiscos);


            TorresHanoi torresHanoi = new TorresHanoi();
            torresHanoi.executar(estatistica, nDiscos, 'A', 'B', 'C');

            estatistica.parar();
            estatistica.apresentar();

        }
    }

}
