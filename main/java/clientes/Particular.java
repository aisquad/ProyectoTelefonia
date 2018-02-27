package clientes;

import places.Direccion;
import facturacion.Tarifa;

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

    public Particular(Tarifa tarifa, String nombre, String nif, Direccion direccion, String correoElectronico, String apellido){
        super(tarifa, nombre, nif, direccion, correoElectronico);
        this.apellido = apellido;
    }

}
