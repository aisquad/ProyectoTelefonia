package places;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Poblacion {
    //Atributes
    private String zipCode;
    private String name;
    private String province;
    private final String pattern = "^(0[1-9]|[1-4]\\d|5[012])\\d{3}$";

    //Constructors
    public Poblacion() {
        zipCode = "";
        name = "";
        province = "";
    }

    //Methods
    public String getZipCode(){
        return zipCode;
    }

    public String getNombre() {
        return name;
    }

    public String getProvince(){
        return province;
    }

    public void setZipCode(String id) {
        if (id.length() < 1 || id.length() > 5)
            throw new IllegalArgumentException();
        zipCode = id;
    }

    public void setName(String name) {
        /*
        Asignamos al nombre del municipio un aspecto más 'natural'.
        Eliminamos la dualidad entre nombre castellanizado y denominación regional y
        colocamos el artículo delante.
         */
        if (name.contains("/")) {
            //Eliminamos binomio castellanización/regionalismo.
            String tokens[] = name.split("/");
            name = tokens[1];
        }
        if (name.contains(", ")) {
            //Recolocamos las partículas al inicio.
            String tokens[] = name.split(", ");
            String noSpace = tokens[1].endsWith("'") ? "" : " ";
            name = String.format("%s%s%s", tokens[1], noSpace, tokens[0]);
        }
        this.name = name;
    }

    public void setProvince(String name) {
        province = name;
    }
}