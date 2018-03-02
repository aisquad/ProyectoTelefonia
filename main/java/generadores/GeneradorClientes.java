package generadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GeneradorClientes extends Generador {
    private ArrayList<String> nombres;
    private ArrayList<String> apellidos;

    public GeneradorClientes () {
        nombres = leeFichero(NOMBRES);
        apellidos = leeFichero(APELLIDOS);
    }

    protected ArrayList<String> leeFichero(String fichero) {
        ArrayList<String> lista = new ArrayList<String>();
        FileReader file = null;
        try {
            file = new FileReader(RUTA + fichero + ".dat");
            BufferedReader buffer = new BufferedReader(file);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                lista.add(line);
            }
            buffer.close();
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
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
