package pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.naoordenadas.estruturas;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravel;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.naoordenadas.ColecaoIteravelLinearNaoOrdenada;

import java.io.Serializable;

public class ListaDuplaNaoOrdenada<T> implements ColecaoIteravelLinearNaoOrdenada<T> {

    protected No noInicial;
    protected No noFinal;
    protected int numElementos;

    public ListaDuplaNaoOrdenada() {
        noFinal = noInicial = null;
        numElementos = 0;
    }

    @Override
    public void inserirNoInicio(T elem) {
        if(++numElementos == 1) {
            noFinal = noInicial = new No(elem);
        } else {
            noInicial = new No(elem, noInicial);
        }
    }

    @Override
    public void inserirNoFim(T elem) {
        No no = new No(elem);
        noFinal = no;
        if(++numElementos == 1) {
            noInicial = no;
        }
    }

    @Override
    public void inserir(int indice, T elem) {

    }

    @Override
    public T removerDoInicio() {
        return null;
    }

    @Override
    public T removerDoFim() {
        return null;
    }

    @Override
    public T remover(T elem) {
        return null;
    }

    @Override
    public T remover(int indice) {
        return null;
    }

    @Override
    public T removerPorReferencia(T elem) {
        return null;
    }

    @Override
    public T consultar(int indice) {
        return null;
    }

    @Override
    public boolean contem(T elem) {
        return false;
    }

    @Override
    public boolean contemReferencia(T elem) {
        return false;
    }

    @Override
    public IteradorIteravel<T> iterador() {
        return null;
    }

    @Override
    public int getNumeroElementos() {
        return 0;
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

            if(noFinal != null)
                noFinal.seguinte = this;
        }

        // cria um nó com elem antes do nó seguinte
        public No(T elem, No seg) {
            elemento = elem;
            seguinte = seg;
            anterior = seguinte.anterior;

            if(anterior != null)
                anterior.seguinte = this;

            seguinte.anterior = this;
        }
    }
}
