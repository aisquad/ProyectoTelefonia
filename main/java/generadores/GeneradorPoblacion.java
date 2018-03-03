package generadores;

import poblaciones.Poblacion;

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
        provincias = new HashMap<String, String>();
        llenarDatos(PROVINCIAS);
        poblaciones = new HashMap<String, Poblacion>();
        llenarDatos(LOCALIDADES);
    }

    @Override
    protected void llenarDatos(String nombreFichero) {
        ArrayList<String> lista = leerFichero(nombreFichero);
        String id = "", toponym = "";
        if (nombreFichero.equals(PROVINCIAS)) {
            for (String linea : lista) {
                String tokens[] = linea.split("\t");
                id = tokens[0];
                toponym = tokens[1];
                provincias.put(id, toponym);
            }
        } else {
            for (String linea : lista) {
                String tokens[] = linea.split("\t");

                id = tokens[0];
                toponym = tokens[1];
                Poblacion poblacion = new Poblacion();
                poblacion.setNombre(toponym);
                poblacion.setProvincia(provincias.get(id.substring(0, 2)));
                poblacion.setCodigoPostal(id);
                poblaciones.put(id, poblacion);
                listaCodPost.add(id);
            }
        }
    }

    public Poblacion getPoblacion(){
        return poblaciones.get(listaCodPost.get(random.nextInt(listaCodPost.size())));
    }
}
