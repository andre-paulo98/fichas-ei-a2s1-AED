package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravelDuplo;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenada;
import pt.ipleiria.estg.dei.aed.modelo.Data;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc;

public class GestorContactosNumaData {
    private Data data;
    private ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactos;

    public GestorContactosNumaData(Data data) {
        this.data = data;
        contactos = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO);
    }

    public Data getData() {
        return data;
    }

    public void inserir(Contacto contacto) {
        contactos.inserir(contacto);
    }

    public IteradorIteravelDuplo<Contacto> iterador() {
        return contactos.iterador();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GestorContactosNumaData that = (GestorContactosNumaData) o;

        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    public Contacto remover(Contacto contacto) {
        return null;
    }

    public boolean isVazio() {
        return contactos.isVazia();
    }
}
