package pt.ipleiria.estg.dei.aed.ordenacao.utilizacao;


import pt.ipleiria.estg.dei.aed.Comparacao;
import pt.ipleiria.estg.dei.aed.modelo.contactos.Contacto;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoContactosUltimoNomeAscDataNascDesc;
import pt.ipleiria.estg.dei.aed.modelo.contactos.io.ContactosIO;
import pt.ipleiria.estg.dei.aed.ordenacao.algoritmos.*;
import pt.ipleiria.estg.dei.aed.utils.Estatistica;
import pt.ipleiria.estg.dei.aed.utils.IOUtils;
import pt.ipleiria.estg.dei.aed.utils.visualizacao.VisualizadorEstatisticas;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

public class MainPraticaComparacaoAlgoritmosOrdenacao {

    public MainPraticaComparacaoAlgoritmosOrdenacao() {
        File file = IOUtils.escolherFicheiroAbrir();
        if (file == null) {
            System.exit(0);
        }

        Contacto[] contactos = ContactosIO.lerContactos(file);
        System.out.println(contactos.length);

        Comparacao<Contacto> criterio = ComparacaoContactosUltimoNomeAscDataNascDesc.CRITERIO;

        AlgoritmoOrdenacao<Contacto>[] algoritmos = new AlgoritmoOrdenacao[]{
                new SelectionSort(criterio),
                new BubbleSort(criterio),
                new InsertionSort(criterio),
                new QuickSort(criterio)
        };

        VisualizadorEstatisticas visualizador = new VisualizadorEstatisticas();

        for (AlgoritmoOrdenacao<Contacto> algoritmo : algoritmos) {

            LinkedList<Estatistica> estatisticas = new LinkedList<>();

            for (int i = 10; i < 51; i += 10) {
                estatisticas.add(algoritmo.getEstatistica(Arrays.copyOf(contactos, i)));
            }

            visualizador.adicionarEstatisticas(algoritmo.getClass().getSimpleName(), estatisticas);
        }


        visualizador.visualizar();


    }

    public static void main(String[] args) {
        new MainPraticaComparacaoAlgoritmosOrdenacao();
    }


}
