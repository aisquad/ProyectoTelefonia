package modelo.generadores;

import modelo.poblaciones.Poblacion;

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

    private ArrayList<String []> seccionaLista(ArrayList<String> lista){
        ArrayList<String []> listaDevolver = new ArrayList<String[]>();
        for (String linea : lista) {
            String tokens[] = linea.split("\t");
            listaDevolver.add(tokens);
        }
        return listaDevolver;
    }

    protected void llenarDatos(String nombreFichero) {
        ArrayList<String []> lista = seccionaLista(leerFichero(nombreFichero));
        String id = "", toponimo = "";
        if (nombreFichero.equals(PROVINCIAS)) {
            for (String tokens[] : lista) {
                id = tokens[0];
                toponimo = tokens[1];

                provincias.put(id, toponimo);
            }
        } else {
            for (String tokens[] : lista) {
                id = tokens[0];
                toponimo = tokens[1];

                Poblacion poblacion = new Poblacion();
                poblacion.setNombre(toponimo);
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
