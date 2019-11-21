package pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.naoordenadas.estruturas;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravel;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravelDuplo;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.naoordenadas.ColecaoIteravelLinearNaoOrdenada;

import java.io.Serializable;
import java.util.NoSuchElementException;

public class ListaDuplaNaoOrdenada<T> implements ColecaoIteravelLinearNaoOrdenada<T> {

    protected No noInicial;
    protected No noFinal;
    protected int numElementos;

    public ListaDuplaNaoOrdenada() {
        noFinal = noInicial = null;
        numElementos = 0;
    }

    protected No getNo(T elem) {
        No atual = noInicial;
        while (atual != null && !atual.elemento.equals(elem))
            atual = atual.seguinte;

        return atual;
    }

    protected No getNoReferencia(T elem) {
        No atual = noInicial;
        while (atual != null && atual.elemento != elem) {
            atual = atual.seguinte;
        }

        return atual;
    }

    public No getNo(int indice) {
        if (indice < 0 || indice >= numElementos) {
            throw new IndexOutOfBoundsException();
        }

        No atual;
        if (indice < numElementos / 2) {
            atual = noInicial;
            while (indice-- > 0) {
                atual = atual.seguinte;
            }
        } else {
            atual = noFinal;
            indice = numElementos - indice - 1;
            while (indice-- > 0) {
                atual = atual.anterior;
            }
        }

        return atual;
    }

    @Override
    public void inserirNoInicio(T elem) {
        if (++numElementos == 1) {
            noFinal = noInicial = new No(elem);
        } else {
            noInicial = new No(elem, noInicial);
        }
    }

    @Override
    public void inserirNoFim(T elem) {
        No no = new No(elem);
        noFinal = no;
        if (++numElementos == 1) {
            noInicial = no;
        }
    }

    @Override
    public void inserir(int indice, T elem) {
        if (indice < 0 || indice > numElementos) {
            throw new IndexOutOfBoundsException();
        }

        if (indice == 0) {
            inserirNoInicio(elem);
        } else if (indice == numElementos) {
            inserirNoFim(elem);
        } else {
            new No(elem, getNo(indice));
            numElementos++;
        }
    }

    /**
     * Apenas remove nós do meio
     *
     * @param noARemover No a remover
     * @return No removido
     */
    public No removerNo(No noARemover) {
        if (noARemover == noInicial) {
            removerDoInicio();
        } else if (noARemover == noFinal) {
            removerDoFim();
        } else {
            noARemover.seguinte.anterior = noARemover.anterior;
            noARemover.anterior.seguinte = noARemover.seguinte;
        }
        numElementos--;
        return noARemover;
    }

    @Override
    public T removerDoInicio() {
        if (numElementos == 0)
            return null;

        No no = noInicial;
        noInicial = noInicial.seguinte;

        if (--numElementos == 0) {
            noFinal = null;
        } else {
            noInicial.anterior = null;
        }

        return no.elemento;
    }

    @Override
    public T removerDoFim() {
        return null;
    }

    @Override
    public T remover(T elem) {

        No no = getNo(elem);

        return no != null ? removerNo(no).elemento : null;
    }

    @Override
    public T remover(int indice) {
        return removerNo(getNo(indice)).elemento;
    }

    @Override
    public T removerPorReferencia(T elem) {
        if (numElementos == 0)
            return null;

        No no = getNoReferencia(elem);
        return no != null ? removerNo(no).elemento : null;
    }

    @Override
    public T consultar(int indice) {
        return getNo(indice).elemento;
    }

    @Override
    public boolean contem(T elem) {
        return getNo(elem) != null;
    }

    @Override
    public boolean contemReferencia(T elem) {
        return getNoReferencia(elem) != null;
    }

    @Override
    public IteradorIteravel<T> iterador() {
        return new Iterador();
    }

    @Override
    public int getNumeroElementos() {
        return 0;
    }

    protected class Iterador implements IteradorIteravelDuplo<T> {

        protected No anterior;
        protected No proximo;
        protected No corrente;

        public Iterador() {
            reiniciar();
        }

        @Override
        public void reiniciar() {
            corrente = null;
            proximo = noInicial;
            anterior = noFinal;
        }

        @Override
        public T corrente() {
            if(corrente == null)
                throw new NoSuchElementException();

            return corrente.elemento;
        }

        @Override
        public boolean podeAvancar() {
            return proximo != null;
        }

        @Override
        public T avancar() {
            if(!podeAvancar())
                throw new NoSuchElementException();

            anterior = corrente;
            corrente = proximo;
            proximo = corrente.seguinte;
            return corrente.elemento;
        }

        @Override
        public boolean podeRecuar() {
            return anterior != null;
        }

        @Override
        public T recuar() {
            if(!podeRecuar())
                throw new NoSuchElementException();

            proximo = corrente;
            corrente = anterior;
            anterior = corrente.anterior;
            return corrente.elemento;
        }
    }

    protected class No implements Serializable {

        protected T elemento;
        protected No seguinte;
        protected No anterior;

        // cria um nó com elem no final da lista
        public No(T elem) {
            elemento = elem;
            seguinte = null;
            anterior = noFinal;

            if (noFinal != null)
                noFinal.seguinte = this;
        }

        // cria um nó com elem antes do nó seguinte
        public No(T elem, No seg) {
            elemento = elem;
            seguinte = seg;
            anterior = seguinte.anterior;

            if (anterior != null)
                anterior.seguinte = this;

            seguinte.anterior = this;
        }
    }
}
