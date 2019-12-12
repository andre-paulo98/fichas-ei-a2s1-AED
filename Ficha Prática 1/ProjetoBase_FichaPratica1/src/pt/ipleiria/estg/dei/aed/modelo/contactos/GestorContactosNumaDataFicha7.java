package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenada;
import pt.ipleiria.estg.dei.aed.modelo.HashingIncrementoString;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc;

public class GestorContactosNumaDataFicha7 {
    private TabelaHashPorSondagemComIncrementoPorHash<String,
            ListaDuplaCircularBaseLimiteOrdenada<Contacto>> contactosPorMorada;
    private ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactos;

    public GestorContactosNumaDataFicha7() {
        contactosPorMorada = new TabelaHashPorSondagemComIncrementoPorHash<>(100, new HashingIncrementoString(100));
        contactos = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO);
    }

    public void inserir(Contacto contacto) {
        // consultar a lista dos contactos que moram na mesma morada
        String morada = contacto.getMorada();
        ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactosNumaMorada = contactosPorMorada.consultar(morada);
        // se a lista não existir (se for o primeiro contacto com esta morada)
        if(contactosNumaMorada == null) {
            // criar a lista
            contactosNumaMorada = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO);
            // inserir na TH
            contactosPorMorada.inserir(morada, contactosNumaMorada);
        }
        // inserir o contacto na lista dos contactos com a mesma morada
        contactosNumaMorada.inserir(contacto);
        // inserir o contacto na lista
        contactos.inserir(contacto);

    }

    public boolean isVazio() {
        return contactosPorMorada.isVazia();
    }

    public Contacto remover(Contacto contacto) {
        // consultar a lista de contactos com a mesma morada (!= null)
        String morada = contacto.getMorada();
        ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactosNumaMorada = contactosPorMorada.consultar(morada);
        // remover o contacto dessa lista
        contactosNumaMorada.remover(contacto);
        // se a lista ficar vazia
        if(contactosNumaMorada.isVazia()) {
            // remover a lista da TH contactosPorMorada
            contactosPorMorada.remover(morada);
        }
        // remover o contacto da lista de contactos (outra estrutura)
        return contactos.remover(contacto);
    }
}
