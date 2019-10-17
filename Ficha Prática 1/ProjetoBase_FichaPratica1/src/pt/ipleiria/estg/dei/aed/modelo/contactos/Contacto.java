package pt.ipleiria.estg.dei.aed.modelo.contactos;

public class Contacto {

    private String primeiroNome;
    private String ultimoNome;
    private long numTelefone;
    private String morada;
    private Data dataNascimento;

    public Contacto(String primeiroNome, String ultimoNome, long numTelefone, String morada, Data dataNascimento) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.numTelefone = numTelefone;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }


    public long getNumTelefone() {
        return numTelefone;
    }

    public String getMorada() {
        return morada;
    }

    public Data getDataNascimento() {
        return dataNascimento;
    }
}
