package pt.ipleiria.estg.dei.aed.modelo;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagem;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;

public class HashingIncrementoLong implements TabelaHashPorSondagemComIncrementoPorHash.HashingIncremento<Long> {
    protected int primo, tamanhoTabelaAnterior;

    public HashingIncrementoLong(int tamanhoTabela) {
        calcularPrimo(tamanhoTabela);
    }

    private void calcularPrimo(int tamanhoTabela) {
        tamanhoTabelaAnterior = tamanhoTabela;
        do {
            primo = TabelaHashPorSondagem.proximoPrimo(tamanhoTabela /= 2);
        } while (primo >= tamanhoTabelaAnterior);
    }

    @Override
    public int getIncremento(Long chave, int tamanhoTabela) {
        if (tamanhoTabelaAnterior != tamanhoTabela) {
            calcularPrimo(tamanhoTabela);
        }
        return primo - (Math.abs(chave.hashCode() % primo));
    }
}
