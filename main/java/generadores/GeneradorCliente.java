package generadores;

public class GeneradorCliente extends Generador {
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
