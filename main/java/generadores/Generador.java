package generadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public abstract class Generador {
    //Atributos
    protected final String NOMBRES = "givennames";
    protected final String APELLIDOS = "familynames";
    protected final String LOCALIDADES = "zipcodes";
    protected final String PROVINCIAS = "provinces";
    protected final String RUTA = "src/main/resources/data/";
    protected final Random random;

    //Constructores
    public Generador() {
        random = new Random();
    }

    protected ArrayList leerFichero(String nombreFichero){
        ArrayList<String> lista = new ArrayList<String>();
        FileReader file = null;
        try {
            file = new FileReader(RUTA + nombreFichero + ".dat");
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

    protected abstract void llenarDatos(String nombreFichero);
}