package generadores;

import java.util.ArrayList;

public class GeneradorParticulares extends Generador {
    private ArrayList<String> nombres;
    private ArrayList<String> apellidos;

    public GeneradorParticulares() {
        nombres = leerFichero(NOMBRES);
        apellidos = leerFichero(APELLIDOS);
    }

    protected void llenarDatos(String nombreFichero) {

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

    private String eliminaDiacriticos(String string){
        string = string.replaceAll("[áàâä]", "a");
        string = string.replaceAll("ç", "c");
        string = string.replaceAll("[éèêë]", "e");
        string = string.replaceAll("[íìîï]", "i");
        string = string.replaceAll("ñ", "n");
        string = string.replaceAll("[óòôö]", "o");
        string = string.replaceAll("úùûü", "u");;
        return string;
    }

    public String getEmail(String nombre, String apellido){
        nombre = eliminaDiacriticos(nombre.toLowerCase());
        apellido = eliminaDiacriticos(apellido.toLowerCase());

        String servidores [] = {
                "gmail.com", "hotmail.com", "hotmail.es", "uji.es", "yahoo.es", "yahoo.ma", "outlook.com",
                "lycos.com", "telefonica.net", "movistar.es", "wannadoo.com"
        };
        int alea = random.nextInt(servidores.length);
        return String.format("%s.%s@%s", apellido, nombre, servidores[alea]);
    }
}
