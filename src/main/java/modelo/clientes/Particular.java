package modelo.clientes;

import modelo.facturacion.Tarifa;
import modelo.poblaciones.Poblacion;

/**
 * Created by al361930 on 27/02/18.
 */
public class Particular extends Cliente {
    //Atributos
    private String apellido;

    //Constr.
    public Particular () {
        super();
        activo = true;
        apellido = "";
    }

    public Particular(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico, String apellido){
        super(tarifa, nombre, nif, poblacion, correoElectronico);
        activo = true;
        this.apellido = apellido;
    }

    @Override
    public String getNombreCompleto() {
        return activo ? super.getNombreCompleto() + " " + apellido : "";
    }

    @Override
    public String toString() {
        String rtn = "";
        if (activo) {
            rtn = String.format(
                "%s %s %s, %s",
                getNIF(),
                this.getNombreCompleto(),
                getEmail(),
                getPoblacion()
            );
        }
        return rtn;
    }
}
