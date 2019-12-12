package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravel;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.IteradorIteravelDuplo;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoPorHash;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.associativas.estruturas.TabelaHashPorSondagemComIncrementoQuadratico;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.naoordenadas.estruturas.ListaSimplesCircularBaseNaoOrdenada;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenada;
import pt.ipleiria.estg.dei.aed.colecoes.iteraveis.lineares.ordenadas.estruturas.ListaDuplaCircularBaseLimiteOrdenadaDistinta;
import pt.ipleiria.estg.dei.aed.modelo.ComparacaoLimiteDataAsc;
import pt.ipleiria.estg.dei.aed.modelo.Data;
import pt.ipleiria.estg.dei.aed.modelo.HashingIncrementoData;
import pt.ipleiria.estg.dei.aed.modelo.HashingIncrementoLong;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc;

public enum GestorContactosOtimizadoFicha7 {
    INSTANCIA;

    public static final IteradorIteravelDuplo<Contacto> ITERADOR_CONTACTOS_VAZIO = new ListaDuplaCircularBaseLimiteOrdenada<>(ComparacaoLimiteContactosPrimNomeAscUltiNomeAsc.CRITERIO).iterador();
    public static final IteradorIteravel<String> ITERADOR_DATAS_VAZIO = new ListaSimplesCircularBaseNaoOrdenada<String>().iterador();

    private TabelaHashPorSondagemComIncrementoPorHash<Data, GestorContactosNumaDataFicha7> contactosPorData;
    private ListaDuplaCircularBaseLimiteOrdenadaDistinta<Data> datas;
    private TabelaHashPorSondagemComIncrementoPorHash<Long, Contacto> contactosPorNumeroTelefone;

    GestorContactosOtimizadoFicha7() {
        contactosPorData = new TabelaHashPorSondagemComIncrementoPorHash<>(100, new HashingIncrementoData(100));
        datas = new ListaDuplaCircularBaseLimiteOrdenadaDistinta<>(ComparacaoLimiteDataAsc.CRITERIO);
        contactosPorNumeroTelefone = new TabelaHashPorSondagemComIncrementoPorHash<>(100, new HashingIncrementoLong(100));

    }

    public void inserir(Contacto contacto) {
        if(contacto == null) {
            throw new IllegalArgumentException("Não pode inserir contactos nulos");
        }
        // inserir o contacto na TABELA HASH por numero telefone
        contactosPorNumeroTelefone.inserir(contacto.getNumTelefone(), contacto);

        Data dataNascimento = contacto.getDataNascimento();
        // obter o gestor interno que gere esta data
        GestorContactosNumaDataFicha7 contactosNumaData = contactosPorData.consultar(dataNascimento);
        // se não existir nenhum contacto com a mesma data do contacto que se pretende inserir
        if(contactosNumaData == null) {
            // criar um gestor interno
            contactosNumaData = new GestorContactosNumaDataFicha7();
            // inserir na TH
            contactosPorData.inserir(dataNascimento, contactosNumaData);
            // inserir a data na lista das datas
            datas.inserir(dataNascimento);
        }
        // inserir o contacto no gestor interno
        contactosNumaData.inserir(contacto);

    }

    public Contacto remover(Contacto contacto) {
        if(contacto == null) {
            return null;
        }
        // remover o contacto da th por numero de telefone
        Contacto contactoRemovido = contactosPorNumeroTelefone.remover(contacto.getNumTelefone());
        if(contactoRemovido == null) {
            return null;
        }

        Data dataNascimento = contacto.getDataNascimento();
        // consultar o gestor interno que gere esta data (!= null)
        GestorContactosNumaDataFicha7 contactosNumaData = contactosPorData.consultar(dataNascimento);
        // remover o contacto do gestor interno
        contactosNumaData.remover(contacto);
        // se o gestor ficar vazio
        if(contactosNumaData.isVazio()){
            // remover da TH contactosPorData
            contactosPorData.remover(dataNascimento);
            // remover a data da lista das datas
            datas.remover(dataNascimento);
        }
        // devolver o contacto removido
        return contactoRemovido;
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
