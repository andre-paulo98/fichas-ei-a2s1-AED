package pt.ipleiria.estg.dei.aed.modelo.contactos;

public class Data {
    private int ano;
    private int mes;
    private int dia;

    public Data(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int compareTo(Data data) {
        int comp = Integer.compare(this.ano, data.ano);
        if(comp != 0)
            return comp;

        comp = Integer.compare(this.mes, data.mes);
        if(comp != 0)
            return comp;

        return Integer.compare(this.dia, data.dia);
    }
}
