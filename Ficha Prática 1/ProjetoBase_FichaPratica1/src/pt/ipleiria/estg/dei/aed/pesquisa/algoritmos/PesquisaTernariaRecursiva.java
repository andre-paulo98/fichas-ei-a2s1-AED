package pt.ipleiria.estg.dei.aed.pesquisa.algoritmos;

import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.utils.EstatisticaDeComparacoes;

public class PesquisaTernariaRecursiva<T> extends AlgoritmoPesquisa<T> {

    public PesquisaTernariaRecursiva(Comparacao<T> criterio) {
        super(criterio);
    }

    public int pesquisar(EstatisticaDeComparacoes estatistica, T elemento, T... elementos) {
        int indiceUltimoElemento = elementos.length - 1;
        if (elementos.length == 0 ||
                criterio.comparar(elemento, elementos[0]) < 0 ||
                criterio.comparar(elemento, elementos[indiceUltimoElemento]) > 0) {
            return NAO_ENCONTRADO;
        }
        return pesquisarRecursivo(estatistica, elemento, 0, indiceUltimoElemento, elementos);
    }

    public int pesquisarRecursivo(EstatisticaDeComparacoes estatistica, T elemento, int esq, int dir, T... elementos) {
        if (esq > dir) {
            return NAO_ENCONTRADO;
        }

        // 1
        int aux = (dir-esq)/3;
        int terco1 = esq + aux;

        // 2
        int cp = criterio.comparar(elemento, elementos[terco1]);
        // 2.i)
        estatistica.incrementarComparacoes();
        if(cp == 0) {
            return terco1;
        }

        // 2.ii)
        estatistica.incrementarComparacoes();
        if (cp < 0) {
            return pesquisarRecursivo(estatistica, elemento, esq, terco1 - 1, elementos);
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
            return pesquisarRecursivo(estatistica, elemento, terco1 + 1, terco2 - 1, elementos);
        }

        // 2.iii.c)
        return pesquisarRecursivo(estatistica, elemento, terco2 + 1, dir, elementos);
    }

}
