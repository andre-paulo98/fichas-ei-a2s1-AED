package pt.ipleiria.estg.dei.aed.modelo;

import java.util.InvalidPropertiesFormatException;

public class Data {
    private int ano;
    private int mes;
    private int dia;

    public Data(int dia, int mes, int ano){
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

    @Override
    public String toString() {
        return dia + "/" + mes + "/" + ano;
    }

    public static Data parseData(String data) throws InvalidPropertiesFormatException {
        String[] partes = data.split("/");
        if(partes.length != 3) {
            throw new InvalidPropertiesFormatException("Data inválido");
        }

        return new Data(Integer.parseInt(partes[0].trim()), Integer.parseInt(partes[1].trim()), Integer.parseInt(partes[2].trim()));


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (ano != data.ano) return false;
        if (mes != data.mes) return false;
        return dia == data.dia;
    }

    @Override
    public int hashCode() {
        int result = ano;
        result = 31 * result + mes;
        result = 31 * result + dia;
        return result;
    }
}
