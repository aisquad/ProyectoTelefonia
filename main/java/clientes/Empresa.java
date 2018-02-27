package clientes;

import places.Direccion;
import facturacion.Tarifa;

/**
 * Created by al361930 on 27/02/18.
 */
public class Empresa extends Cliente{
    //Atributos

    //Constructores
    public Empresa(){}

    public Empresa(Tarifa tarifa, String nombre, String nif, Direccion direccion, String correoElectronico){
        super(tarifa, nombre, nif, direccion, correoElectronico);
    }
}
