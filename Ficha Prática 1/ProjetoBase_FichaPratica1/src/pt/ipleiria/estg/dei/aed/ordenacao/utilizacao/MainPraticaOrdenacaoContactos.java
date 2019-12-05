package pt.ipleiria.estg.dei.aed.ordenacao.utilizacao;

import pt.ipleiria.estg.dei.aed.modelo.contactos.Contacto;
import pt.ipleiria.estg.dei.aed.modelo.Data;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoContactosPrimNomeAscMoradaDesc;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoContactosUltimoNomeAscDataNascDesc;
import pt.ipleiria.estg.dei.aed.modelo.contactos.comparadores.ComparacaoContactosTelefoneDescendente;
import pt.ipleiria.estg.dei.aed.ordenacao.algoritmos.InsertionSort;

public class MainPraticaOrdenacaoContactos {

    public MainPraticaOrdenacaoContactos() {
        Contacto[] contactos = new Contacto[] {
                new Contacto("Ana", "Silva",
                        950000000, "Rua de Leiria", new Data(1, 10, 1990)),

                new Contacto("Ana", "Rita",
                        990000000, "Travessa 25 de Abril", new Data(15, 6, 2000)),

                new Contacto("Hugo", "Santos",
                        971234567, "Avenida 1º de Maio ", new Data(18, 3, 1994)),

                new Contacto("Teresa", "Silva",
                        950000001, "Rua de Leiria", new Data(2, 10, 1990)),

                new Contacto("Eça", "Queiroz",
                        100000000, "Praça do Almada", new Data(25, 11, 1845))

        };

        Contacto[] copia1 = contactos.clone();
        Contacto[] copia2 = contactos.clone();
        Contacto[] copia3 = contactos.clone();

        InsertionSort<Contacto> algoritmo1 = new InsertionSort<>(ComparacaoContactosTelefoneDescendente.CRITERIO);
        InsertionSort<Contacto> algoritmo2 = new InsertionSort<>(ComparacaoContactosUltimoNomeAscDataNascDesc.CRITERIO);
        InsertionSort<Contacto> algoritmo3 = new InsertionSort<>(ComparacaoContactosPrimNomeAscMoradaDesc.CRITERIO);


        algoritmo1.getEstatistica(copia1);
        algoritmo2.getEstatistica(copia2);
        algoritmo3.getEstatistica(copia3);

    }

    public static void main(String[] args) {
        new MainPraticaOrdenacaoContactos();
    }
}
