package generadores;

import places.Poblacion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GeneradorPoblacion extends  Generador{
    //Atributos
    private ArrayList<String> listaCodPost;
    private HashMap<String, Poblacion> poblaciones;
    private HashMap<String, String> provincias;

    //Constructores
    public GeneradorPoblacion(){
        listaCodPost = new ArrayList<String>();
        provincias = leeFicheroProvincias();
        poblaciones = leeFicheroLocalidades();
    }

    protected HashMap<String , String> leeFicheroProvincias() {
        HashMap<String, String> entidades =  new HashMap<String, String>();
        try {
            FileReader file = new FileReader(RUTA + PROVINCIAS + ".dat");
            BufferedReader buffer = new BufferedReader(file);
            String id = "";
            String toponym = "";
            String line = "";
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("#"))
                    continue;

                String tokens[] = line.split("\t");

                id = tokens[0];
                toponym = tokens[1];
                entidades.put(id, toponym);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entidades;
    }

    protected HashMap<String , Poblacion> leeFicheroLocalidades() {
        HashMap<String, Poblacion> entidades =  new HashMap<String, Poblacion>();
        try {
            FileReader file = new FileReader(RUTA + LOCALIDADES + ".dat");
            BufferedReader buffer = new BufferedReader(file);
            String id = "";
            String toponym = "";
            String line = "";
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }

                String tokens[] = line.split("\t");

                id = tokens[0];
                toponym = tokens[1];
                Poblacion poblacion = new Poblacion();
                poblacion.setName(toponym);
                poblacion.setProvince(provincias.get(id.substring(0,2)));
                poblacion.setZipCode(id);
                entidades.put(id, poblacion);
                listaCodPost.add(id);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entidades;
    }

    public Poblacion getPoblacion(){
        return poblaciones.get(listaCodPost.get(random.nextInt(listaCodPost.size())));
    }
}
