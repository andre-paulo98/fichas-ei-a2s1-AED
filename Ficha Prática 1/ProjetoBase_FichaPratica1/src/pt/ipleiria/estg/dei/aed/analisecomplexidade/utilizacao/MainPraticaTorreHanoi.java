package pt.ipleiria.estg.dei.aed.analisecomplexidade.utilizacao;

import pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos.JogoTorresHanoi;
import pt.ipleiria.estg.dei.aed.recursividade.algoritmos.TorresHanoi;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadasEMovimentos;

public class MainPraticaTorreHanoi {

    public static void main(String[] args) {


        JogoTorresHanoi jogo = new JogoTorresHanoi();
        jogo.getEstatistica(5);

    }
}
