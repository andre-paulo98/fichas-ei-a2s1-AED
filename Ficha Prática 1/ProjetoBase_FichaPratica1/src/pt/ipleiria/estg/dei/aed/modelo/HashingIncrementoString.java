package pt.ipleiria.estg.dei.aed.modelo;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagem;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;

public class HashingIncrementoString implements TabelaHashPorSondagemComIncrementoPorHash.HashingIncremento<String> {
    protected int primo, tamanhoTabelaAnterior;

    public HashingIncrementoString(int tamanhoTabela) {
        calcularPrimo(tamanhoTabela);
    }

    private void calcularPrimo(int tamanhoTabela) {
        tamanhoTabelaAnterior = tamanhoTabela;
        do {
            primo = TabelaHashPorSondagem.proximoPrimo(tamanhoTabela /= 2);
        } while (primo >= tamanhoTabelaAnterior);
    }

    @Override
    public int getIncremento(String chave, int tamanhoTabela) {
        if (tamanhoTabelaAnterior != tamanhoTabela) {
            calcularPrimo(tamanhoTabela);
        }
        return primo - (Math.abs(chave.hashCode() % primo));
    }
}
