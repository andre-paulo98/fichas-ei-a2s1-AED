package pt.ipleiria.estg.dei.aed.exercicios.ficha1;

public class Fatorial {

    public static void main(String[] args) {
        System.out.println("Fatorial de 1:" + fatorial(1));
        System.out.println("Fatorial de 2:" + fatorial(2));
        System.out.println("Fatorial de 3:" + fatorial(3));
        System.out.println("Fatorial de 4:" + fatorial(4));
        System.out.println("Fatorial de 21:" + fatorial(21));
    }


    public static long fatorial(int i) {
        if(i <= 0)
            return 1;
        else
            return i * fatorial(i - 1);
    }

}
