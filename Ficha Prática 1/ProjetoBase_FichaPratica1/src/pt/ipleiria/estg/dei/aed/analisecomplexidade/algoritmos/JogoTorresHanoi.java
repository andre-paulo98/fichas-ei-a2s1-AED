package pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos;

import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadas;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadasEMovimentos;

public class JogoTorresHanoi {

    public void executar(EstatisticaDeChamadasEMovimentos estatistica, int n, Torre origem, Torre auxiliar, Torre destino) {

        estatistica.incrementarChamadas();

        if(n > 1) {
            executar(estatistica, n - 1, origem, destino, auxiliar);

            moverDisco(estatistica, origem, destino);

            executar(estatistica, n - 1, auxiliar, origem, destino);

        } else if (n == 1) {
            moverDisco(estatistica, origem, destino);

        } else {
            //throw new IllegalArgumentException("Deve haver pelo menos um disco.");
        }

    }

    private void moverDisco(EstatisticaDeChamadasEMovimentos estatistica, Torre origem, Torre destino) {
        estatistica.incrementarMovimentos();
        destino.colocarDisco(origem.tiraDisco());
    }

    public EstatisticaDeChamadasEMovimentos getEstatistica(int n) {

        EstatisticaDeChamadasEMovimentos est = new EstatisticaDeChamadasEMovimentos(n);

        Torre origem = new Torre(n, true);
        Torre destino = new Torre(n, false);
        Torre auxiliar = new Torre(n, false);

        executar(est, n, origem, auxiliar, destino);

        return est;
    }
}
