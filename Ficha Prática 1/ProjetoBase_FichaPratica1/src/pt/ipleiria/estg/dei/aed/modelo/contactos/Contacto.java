package pt.ipleiria.estg.dei.aed.modelo.contactos;

import pt.ipleiria.estg.dei.aed.modelo.Data;

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

    @Override
    public String toString() {
        return primeiroNome + " " + ultimoNome + " - Nº: " + numTelefone + " - " + morada + " - " + dataNascimento + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;

        if (numTelefone != contacto.numTelefone) return false;
        if (primeiroNome != null ? !primeiroNome.equals(contacto.primeiroNome) : contacto.primeiroNome != null)
            return false;
        if (ultimoNome != null ? !ultimoNome.equals(contacto.ultimoNome) : contacto.ultimoNome != null) return false;
        if (morada != null ? !morada.equals(contacto.morada) : contacto.morada != null) return false;
        return dataNascimento != null ? dataNascimento.equals(contacto.dataNascimento) : contacto.dataNascimento == null;
    }

    @Override
    public int hashCode() {
        int result = primeiroNome != null ? primeiroNome.hashCode() : 0;
        result = 31 * result + (ultimoNome != null ? ultimoNome.hashCode() : 0);
        result = 31 * result + (int) (numTelefone ^ (numTelefone >>> 32));
        result = 31 * result + (morada != null ? morada.hashCode() : 0);
        result = 31 * result + (dataNascimento != null ? dataNascimento.hashCode() : 0);
        return result;
    }
}
