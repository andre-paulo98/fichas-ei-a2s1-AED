package pt.ipleiria.estg.dei.aed.recursividade.algoritmos;

import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadasEMovimentos;

public class TorresHanoi {

    public void executar(EstatisticaDeChamadasEMovimentos estatistica, int n, char origem, char auxiliar, char destino) {

        estatistica.incrementarChamadas();

        if(n > 1) {
            executar(estatistica, n - 1, origem, destino, auxiliar);

            System.out.println("Mover de '" + origem + "' para '" + destino + "'");
            estatistica.incrementarMovimentos();

            executar(estatistica, n - 1, auxiliar, origem, destino);

        } else if (n == 1) {
            System.out.println("Mover de '" + origem + "' para '" + destino + "'");
            estatistica.incrementarMovimentos();

        } else {
            //throw new IllegalArgumentException("Deve haver pelo menos um disco.");
        }

    }
}
