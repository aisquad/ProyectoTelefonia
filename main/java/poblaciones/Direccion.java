package poblaciones;

/**
 * Created by al361930 on 27/02/18.
 */
public class Direccion {
    //Atributos
    private String codigoPostal;
    private String poblacion;
    private String provincia;

    //Constructor
    public Direccion () {
        codigoPostal = "";
        poblacion = "";
        provincia = "";
    }

    public Direccion (String codigoPostal, String poblacion, String provincia) {
        this.codigoPostal = codigoPostal;
        this.poblacion = poblacion;
        this.provincia = provincia;
    }

}
