package entities;

public class Town {
    //Atributes
    private String zipCode;
    private String name;
    private String province;

    //Constructors
    public Town() {
        zipCode = "";
        name = "";
        province = "";
    }

    //Methods
    public String getZipCode(){
        return zipCode;
    }

    public String getName() {
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