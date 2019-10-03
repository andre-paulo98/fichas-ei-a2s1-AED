package pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos;

import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeComparacoes;

import java.util.Arrays;

public class Maximo {

    public int executar(EstatisticaDeComparacoes est, int... valores) {
        if (valores.length == 0) {
            throw new IllegalArgumentException("Deve ter pelo menos 1 valor");
        }

        int maximo = valores[0];
        for (int i = 1; i < valores.length; i++) {

            est.incrementarComparacoes();
            if (valores[i] > maximo) {
                maximo = valores[i];
            }

        }

        return maximo;
    }


    public EstatisticaDeComparacoes getEstatistica(int... valores) {
        try {
            EstatisticaDeComparacoes est = new EstatisticaDeComparacoes(valores.length);

            int resultado = executar(est, valores);

            est.parar();

            System.out.println("O máximo (" + Arrays.toString(valores) + ") = " + resultado);

            est.apresentar();

            return est;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + "\n");
            return null;
        }
    }
}
