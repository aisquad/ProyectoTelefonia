package modelo.generadores;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/*
Al pedirnos el enunciado el código postal para la población, y al ver que el generador
suministrado en el Aula Virtual no lo proporcionaba nos propusimos crear nuestro
generador propio que también  extraería otro tipos de datos como el correo electrónico.

Para el generador de nombres usamos un documento de dominio público sobre los alumnos
admitidos en el año 2016. El enlace aparece en la primera línea del fichero.

Para el generador de modelo.poblaciones usamos otro documento de dominio público del cual también
detallamos su url en la primera línea del fichero. (Desconocemos la licencia exacta bajo la
cual se rige este documento.)

A diferencia del generador del Aula Virtual, obtenemos los datos de manera aleatoria
(o pseudoaleatoria) sin tener en cuenta la frecuencia, ya que no son datos obtenidos
mediante ninguna estadístia.
*/

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
}