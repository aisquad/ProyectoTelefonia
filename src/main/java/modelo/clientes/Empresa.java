package modelo.clientes;

import modelo.facturacion.Tarifa;
import modelo.poblaciones.Poblacion;

/**
 * Created by al361930 on 27/02/18.
 */
public class Empresa extends Cliente{
    //Atributos

    //Constructores
    public Empresa(){
        super();
    }

    public Empresa(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico){
        super(tarifa, nombre, nif, poblacion, correoElectronico);
        activo = true;
    }
}
