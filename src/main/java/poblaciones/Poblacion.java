package poblaciones;

import java.io.Serializable;

public class Poblacion implements Serializable {
    //Atributes
    private String codigoPostal;
    private String nombre;
    private String provincia;

    //Constructors
    public Poblacion() {
        codigoPostal = "";
        nombre = "";
        provincia = "";
    }

    //Methods
    public String getCodigoPostal(){
        return codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProvincia(){
        return provincia;
    }

    public void setCodigoPostal(String id) {
        if (id.length() < 1 || id.length() > 5)
            throw new IllegalArgumentException();
        codigoPostal = id;
    }

    public void setNombre(String nombre) {
        /*
        Asignamos al nombre del municipio un aspecto más 'natural'.
        Eliminamos la dualidad entre nombre castellanizado y denominación regional y
        colocamos el artículo delante.
         */
        if (nombre.contains("/")) {
            //Eliminamos binomio castellanización/regionalismo.
            String tokens[] = nombre.split("/");
            nombre = tokens[1];
        }
        if (nombre.contains(", ")) {
            //Recolocamos las partículas al inicio.
            String tokens[] = nombre.split(", ");
            String noSpace = tokens[1].endsWith("'") ? "" : " ";
            nombre = String.format("%s%s%s", tokens[1], noSpace, tokens[0]);
        }
        this.nombre = nombre;
    }

    public void setProvincia(String name) {
        provincia = name;
    }
}