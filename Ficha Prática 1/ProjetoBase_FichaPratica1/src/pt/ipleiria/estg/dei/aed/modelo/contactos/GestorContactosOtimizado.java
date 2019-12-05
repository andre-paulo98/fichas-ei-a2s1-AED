package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravelDuplo;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoQuadratico;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenada;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenadaDistinta;
import pt.ipleiria.estg.dei.aed.modelo.ComparacaoLimiteDataAsc;
import pt.ipleiria.estg.dei.aed.modelo.Data;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc;

public enum GestorContactosOtimizado {
    INSTANCIA;

    public static final IteradorIteravelDuplo<Contacto> ITERADOR_VAZIO = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO).iterador();

    private TabelaHashPorSondagemComIncrementoQuadratico<Data, ListaDuplaCircularBaseLimiteOrdenada<Contacto>> contactosPorData;
    private ListaDuplaCircularBaseLimiteOrdenadaDistinta<Data> datas;

    GestorContactosOtimizado () {
        contactosPorData = new TabelaHashPorSondagemComIncrementoQuadratico<>(100);
        datas = new ListaDuplaCircularBaseLimiteOrdenadaDistinta<>(ComparacaoLimiteDataAsc.CRITERIO);
    }

    public void inserir(Contacto contacto) {
        if(contacto == null) {
            throw new IllegalArgumentException("Não pode inserir contactos nulos");
        }
        Data dataNascimento = contacto.getDataNascimento();
        ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactosNumaData = contactosPorData.consultar(dataNascimento);

        if(contactosNumaData == null) { // criar
            contactosNumaData = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO);
            contactosPorData.inserir(dataNascimento, contactosNumaData);
            datas.inserir(dataNascimento);
        }
        contactosNumaData.inserir(contacto);

    }

    public Contacto remover(Contacto contacto) {
        if(contacto == null) {
            return null;
        }

        Data dataNascimento = contacto.getDataNascimento();
        ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactosNumaData = contactosPorData.consultar(dataNascimento);
        if(contactosNumaData != null){
            Contacto contactoRemovido = contactosNumaData.remover(contacto);
            if(contactosNumaData.isVazia()){
                contactosPorData.remover(dataNascimento);
                datas.remover(dataNascimento);
            }
            return contactoRemovido;
        }
        return null;


    }

    public IteradorIteravelDuplo<Contacto> remover(Data data) {
       ListaDuplaCircularBaseLimiteOrdenada<Contacto> contactosNumaData = contactosPorData.remover(data);
       if(contactosNumaData == null) {
           return ITERADOR_VAZIO;
       }

       datas.remover(data);
       return contactosNumaData.iterador();
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data data) {
        ListaDuplaCircularBaseLimiteOrdenada contactosNumaData =
                contactosPorData.consultar(data);
        return contactosNumaData != null ? contactosNumaData.iterador() : ITERADOR_VAZIO;
    }

    public IteradorIteravelDuplo<Contacto> consultar(Data dataInicial, Data dataFinal) {
        return new Iterador(dataInicial, dataFinal);
    }

    private class Iterador implements IteradorIteravelDuplo<Contacto> {

        private IteradorIteravelDuplo<Data> iteradorDatas;
        private IteradorIteravelDuplo<Contacto> iteradorContactos;

        public Iterador(Data dataInicial, Data dataFinal) {
            iteradorDatas = datas.consultar(dataInicial, dataFinal);
            reiniciar();

        }

        @Override
        public void reiniciar() {
            iteradorDatas.reiniciar();
            iteradorContactos = ITERADOR_VAZIO;
        }

        @Override
        public Contacto corrente() {
            return iteradorContactos.corrente();
        }

        @Override
        public boolean podeAvancar() {
            return iteradorContactos.podeAvancar() || iteradorDatas.podeAvancar();
        }

        @Override
        public Contacto avancar() {
            if(!iteradorContactos.podeAvancar())
                iteradorContactos = contactosPorData.consultar(iteradorDatas.avancar()).iterador();

            return iteradorContactos.avancar();
        }

        @Override
        public boolean podeRecuar() {
            return iteradorContactos.podeRecuar() || iteradorDatas.podeRecuar();
        }

        @Override
        public Contacto recuar() {
            if(!iteradorContactos.podeRecuar())
                iteradorContactos = contactosPorData.consultar(iteradorDatas.recuar()).iterador();

            return iteradorContactos.recuar();
        }
    }
}
