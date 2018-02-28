package generadores;

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

}