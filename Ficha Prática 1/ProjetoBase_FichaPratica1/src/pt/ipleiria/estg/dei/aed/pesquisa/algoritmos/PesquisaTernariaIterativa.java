package pt.ipleiria.estg.dei.aed.pesquisa.algoritmos;

import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeComparacoes;

public class PesquisaTernariaIterativa<T> extends AlgoritmoPesquisa<T> {

    public PesquisaTernariaIterativa(Comparacao<T> criterio) {
        super(criterio);
    }

    public int pesquisar(EstatisticaDeComparacoes estatistica, T elemento, T... elementos) {
        int indiceUltimoElemento = elementos.length - 1;
        if (elementos.length == 0 ||
                criterio.comparar(elemento, elementos[0]) < 0 ||
                criterio.comparar(elemento, elementos[indiceUltimoElemento]) > 0) {
            return NAO_ENCONTRADO;
        }
        int esq = 0;
        int dir = indiceUltimoElemento;

        do {

            // 1
            int aux = (dir - esq) / 3;
            int terco1 = esq + aux;

            // 2
            int cp = criterio.comparar(elemento, elementos[terco1]);
            // 2.i)
            estatistica.incrementarComparacoes();
            if (cp == 0) {
                return terco1;
            }

            // 2.ii)
            estatistica.incrementarComparacoes();
            if (cp < 0) {
                dir = terco1 - 1;
                continue;
            }


            int terco2 = dir - aux;
            cp = criterio.comparar(elemento, elementos[terco2]);

            // 2.iii.a)
            estatistica.incrementarComparacoes();
            if (cp == 0) {
                return terco2;
            }

            // 2.iii.b)
            estatistica.incrementarComparacoes();
            if (cp < 0) {
                esq = terco1 + 1;
                dir = terco2 - 1;
                continue;
            }

            // 2.iii.c)
            esq = terco2 + 1;

        } while (esq <= dir);

        return NAO_ENCONTRADO;
    }

}
