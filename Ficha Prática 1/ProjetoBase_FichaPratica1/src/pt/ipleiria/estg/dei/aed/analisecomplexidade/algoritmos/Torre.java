package pt.ipleiria.estg.dei.aed.analisecomplexidade.algoritmos;

import java.util.NoSuchElementException;

public class Torre {
    private int[] discos;
    private int indiceTopo;

    public Torre(int nDiscos, boolean preenchida) {

        discos = new int[nDiscos + 1];
        discos[0] = nDiscos * 3;

        if(preenchida) {
            for (int i = 1; i <= nDiscos; i++) {
                discos[i] = nDiscos - i + 1;
            }
            indiceTopo = nDiscos;
        }

    }

    public int tiraDisco(){
        if(indiceTopo == 0)
            throw new NoSuchElementException("Torre Vazia");

        int disco = discos[indiceTopo];
        discos[indiceTopo--] = 0;
        return disco;
    }

    public void colocarDisco(int disco) {
        if(indiceTopo == discos.length -1)
            throw new NoSuchElementException("Torre Cheia");

        discos[++indiceTopo] = disco;
    }
}
