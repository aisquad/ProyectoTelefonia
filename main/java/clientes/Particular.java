package clientes;

import poblaciones.Direccion;
import facturacion.Tarifa;
import poblaciones.Poblacion;

/**
 * Created by al361930 on 27/02/18.
 */
public class Particular extends Cliente {
    //Atributos
    private String apellido;

    //Constr.
    public Particular () {
        super();
        apellido = "";
    }

    public Particular(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico, String apellido){
        super(tarifa, nombre, nif, poblacion, correoElectronico);
        this.apellido = apellido;
    }

    @Override
    public String getNombreCompleto() {
        return super.getNombreCompleto() + " " + apellido;
    }
}
