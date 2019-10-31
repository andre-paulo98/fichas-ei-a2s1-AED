package pt.ipleiria.estg.dei.aed.modelo.contactos.io;

import pt.ipleiria.estg.dei.aed.modelo.contactos.Contacto;
import pt.ipleiria.estg.dei.aed.modelo.contactos.Data;

import java.io.*;
import java.util.InvalidPropertiesFormatException;

public class ContactosIO {
    public static Contacto[] lerContactos(File ficheiro) {

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))){
            String primeiraLinha = br.readLine().trim();
            int numContactos = Integer.parseInt(primeiraLinha);
            Contacto[] contactos = new Contacto[numContactos];
            String linha; int i = 0;
            while((linha = br.readLine()) != null) {
                contactos[i++] = parseContacto(linha);
            }

            if(numContactos != i) {
                throw new InvalidPropertiesFormatException("Número de contactos incorreto");
            }

            return contactos;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Contacto[]{};
    }

    private static Contacto parseContacto(String linha) throws InvalidPropertiesFormatException {

        String[] dados = linha.split(",");
        if(dados.length != 5) {
            throw new InvalidPropertiesFormatException("Contacto inválido");
        }

        return new Contacto(dados[0].trim(), dados[1].trim(),
                Long.parseLong(dados[2].trim()), dados[3].trim(), Data.parseData(dados[4].trim()));
    }


}
