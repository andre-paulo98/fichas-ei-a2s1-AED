package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravelDuplo;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenada;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenadaDistinta;
import pt.ipleiria.estg.dei.aed.modelo.Data;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc;

public enum GestorContactos {

    INSTANCIA;

    public static final IteradorIteravelDuplo<Contacto> ITERADOR_VAZIO = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO).iterador();
    private ListaDuplaCircularBaseLimiteOrdenadaDistinta<GestorContactosNumaData> contactosPorData;

    GestorContactos() {
        contactosPorData = new ListaDuplaCircularBaseLimiteOrdenadaDistinta<>(ComparacaoLimiteGestoresContactosNumaDataPorDataAsc.CRITERIO);
    }

    public void inserir(Contacto contacto) {

        // consultar o gestor interno dessa data de nascimento
        // se não existir criar e adicionar ao gestor de contactos
        // adicionar o contacto a esse gestor interno


        GestorContactosNumaData gestorNovo = new GestorContactosNumaData(contacto.getDataNascimento());
        GestorContactosNumaData gestorContactosNumaData = contactosPorData.consultarDistinto(gestorNovo);

        if(gestorContactosNumaData == null) { // criar
            contactosPorData.inserir(gestorNovo);
            gestorContactosNumaData = gestorNovo;
        }

        gestorContactosNumaData.inserir(contacto);
    }

    public Contacto remover(Contacto contacto) {
        GestorContactosNumaData gestorContactosNumaData = contactosPorData.consultarDistinto(
                new GestorContactosNumaData(contacto.getDataNascimento())
        );

        if(gestorContactosNumaData != null) {

            Contacto contactoRemovido = gestorContactosNumaData.remover(contacto);
            if(gestorContactosNumaData.isVazio()) {
                contactosPorData.remover(gestorContactosNumaData);
            }
            return contactoRemovido;
        }

        return null;
    }

    public IteradorIteravelDuplo<Contacto> remover(Data data) {
        GestorContactosNumaData remover = contactosPorData.remover(new GestorContactosNumaData(data));
        return remover != null ? remover.iterador() : ITERADOR_VAZIO;
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data data) {
        GestorContactosNumaData gestorContactosNumaData = contactosPorData.consultarDistinto(new GestorContactosNumaData(data));
        return gestorContactosNumaData != null ? gestorContactosNumaData.iterador() : ITERADOR_VAZIO;
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data dataInicial, Data dataFinal) {
        return new Iterador(dataInicial, dataFinal);
    }

    private class Iterador implements IteradorIteravelDuplo<Contacto> {

        private IteradorIteravelDuplo<GestorContactosNumaData> iteradorGestoresContactosNumaData;
        private IteradorIteravelDuplo<Contacto> iteradorContactos;

        public Iterador(Data dataInicial, Data dataFinal) {
            iteradorGestoresContactosNumaData = contactosPorData.consultar(
                                                            new GestorContactosNumaData(dataInicial),
                                                            new GestorContactosNumaData(dataFinal));

            reiniciar();

        }

        @Override
        public void reiniciar() {
            iteradorGestoresContactosNumaData.reiniciar();
            iteradorContactos = ITERADOR_VAZIO;
        }

        @Override
        public Contacto corrente() {
            return iteradorContactos.corrente();
        }

        @Override
        public boolean podeAvancar() {
            return iteradorContactos.podeAvancar() || iteradorGestoresContactosNumaData.podeAvancar();
        }

        @Override
        public Contacto avancar() {
            if(!iteradorContactos.podeAvancar())
                iteradorContactos = iteradorGestoresContactosNumaData.avancar().iterador();

            return iteradorContactos.avancar();
        }

        @Override
        public boolean podeRecuar() {
            return iteradorContactos.podeRecuar() || iteradorGestoresContactosNumaData.podeRecuar();
        }

        @Override
        public Contacto recuar() {
            if(!iteradorContactos.podeRecuar())
                iteradorContactos = iteradorGestoresContactosNumaData.recuar().iterador();

            return iteradorContactos.recuar();
        }
    }
}
