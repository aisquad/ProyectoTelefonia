package poblaciones;

/**
 * Created by al361930 on 27/02/18.
 */
public class Direccion {
    /*
    TODO: Puede que sobre, la dejamos por si más adelante
     hemos de especificar calle, número, etc.
     */

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
