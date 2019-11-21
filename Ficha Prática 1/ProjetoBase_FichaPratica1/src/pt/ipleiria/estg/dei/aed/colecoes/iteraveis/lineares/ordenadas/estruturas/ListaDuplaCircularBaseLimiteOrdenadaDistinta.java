package pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas;


import pt.ipleiria.estg.dei.aed.ComparacaoLimite;

public class ListaDuplaCircularBaseLimiteOrdenadaDistinta<T> extends ListaDuplaCircularBaseLimiteOrdenada<T> {

    public ListaDuplaCircularBaseLimiteOrdenadaDistinta(ComparacaoLimite<T> cpl) {
        super(cpl);
    }

    // a
    @Override
    public void inserir(T elem) {
        No no = getNo(elem);
        if(criterio.comparar(no.elemento, elem) == 0) {
            throw new IllegalArgumentException("Já existe um elemento com a mesma ordem.");
        }

        new No(elem, no);
        numeroElementos++;
    }

    public T consultarDistinto(T elem) {
        // TODO continuar
        return null;
    }
}
