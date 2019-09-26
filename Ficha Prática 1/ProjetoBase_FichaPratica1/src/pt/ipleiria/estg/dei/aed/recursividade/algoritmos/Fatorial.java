package pt.ipleiria.estg.dei.aed.recursividade.algoritmos;

import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeChamadas;

public class Fatorial {


    public long executar(EstatisticaDeChamadas estatistica, int n) {

        estatistica.incrementarChamadas();

        if (n < 0) {
            throw new IllegalArgumentException("fatorial(" + n + ") = argumento inválido: deve ser >= 0");
        }

        if (n < 2)
            return 1;

        return n * executar(estatistica, n - 1);
    }

    public long executarPerformance(EstatisticaDeChamadas estatistica, int n) {

        estatistica.incrementarChamadas();

        if (n >= 2)
            return n * executarPerformance(estatistica, n -1);
        else if (n <= 0)
            return 1;
        else
            throw new IllegalArgumentException("fatorialPerformace(" + n + ") = argumento inválido: deve ser >= 0");
    }

    // d)
    public EstatisticaDeChamadas getEstatistica(int n){
        try {
            // criar estatistica
            EstatisticaDeChamadas estatistica = new EstatisticaDeChamadas(n);

            // executar algoritmo para medir tempo
            long resultado = executar(estatistica, n);

            // parar estatistica
            estatistica.parar();

            // apresentar resultados do algoritmo
            System.out.println("Fatorial de " + n + ": " + resultado);

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
