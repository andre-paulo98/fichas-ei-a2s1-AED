package pt.ipleiria.estg.dei.aed.recursividade.algoritmos;

import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadas;

public class Fibonacci {

    public long executar(EstatisticaDeChamadas estatistica, int n) {

        estatistica.incrementarChamadas();

        if (n >= 2)
            return executar(estatistica, n - 2) + executar(estatistica, n - 1);
        else if (n >= 0)
            return n;
        else
            throw new IllegalArgumentException("Fibonacci(" + n + ") = argumento inválido: deve ser >= 0");
    }

    // d)
    public EstatisticaDeChamadas getEstatistica(int n) {
        try {
            // criar estatistica
            EstatisticaDeChamadas estatistica = new EstatisticaDeChamadas(n);

            // executar algoritmo para medir tempo
            long resultado = executar(estatistica, n);

            // parar estatistica
            estatistica.parar();

            // apresentar resultados do algoritmo
            System.out.println("Fibonacci de " + n + ": " + resultado);

            // apresentar estatistica
            estatistica.apresentar();

            // devolver estatistica
            return estatistica;

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage() + "\n");
            return null;
        }
    }


}
