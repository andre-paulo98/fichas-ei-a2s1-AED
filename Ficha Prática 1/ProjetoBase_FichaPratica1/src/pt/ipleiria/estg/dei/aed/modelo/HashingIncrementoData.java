package pt.ipleiria.estg.dei.aed.modelo;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagem;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;

public class HashingIncrementoData implements TabelaHashPorSondagemComIncrementoPorHash.HashingIncremento<Data> {
    protected int primo, tamanhoTabelaAnterior;

    public HashingIncrementoData(int tamanhoTabela) {
        calcularPrimo(tamanhoTabela);
    }

    private void calcularPrimo(int tamanhoTabela) {
        tamanhoTabelaAnterior = tamanhoTabela;
        do {
            primo = TabelaHashPorSondagem.proximoPrimo(tamanhoTabela /= 2);
        } while (primo >= tamanhoTabelaAnterior);
    }

    @Override
    public int getIncremento(Data chave, int tamanhoTabela) {
        if (tamanhoTabelaAnterior != tamanhoTabela) {
            calcularPrimo(tamanhoTabela);
        }
        return primo - (Math.abs(chave.hashCode() % primo));
    }
}
