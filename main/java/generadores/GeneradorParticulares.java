package generadores;

import java.util.ArrayList;

public class GeneradorParticulares extends GeneradorCliente {
    private ArrayList<String> nombres;
    private ArrayList<String> apellidos;

    public GeneradorParticulares() {
        nombres = leerFichero(NOMBRES);
        apellidos = leerFichero(APELLIDOS);
    }

    public String getNombre(){
        return nombres.get(random.nextInt(nombres.size()));
    }

    public String getApellido(){
        return apellidos.get(random.nextInt(apellidos.size()));
    }

    public int getEdad(){
        return  random.nextInt((60 - 13) + 1) + 13;
    }

    public String getDNI(){
        //fte: https://es.wikipedia.org/wiki/N%C3%BAmero_de_identificaci%C3%B3n_fiscal
        String tabla = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero =  random.nextInt(99999999);
        return String.format("%08d%c", numero, tabla.charAt(numero % tabla.length()));
    }
}
