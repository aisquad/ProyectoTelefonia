package clientes;

import interfaces.Fecha;
import places.Direccion;
import facturacion.Tarifa;

import java.util.Date;

/**
 * Created by al361930 on 27/02/18.
 */
public abstract class Cliente implements Fecha{
    //Atributos
    private Tarifa tarifa;
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    private Date fechaAlta;

    //Contructor
    public Cliente (){
        tarifa = null;
        nombre = "";
        nif = "";
        direccion = null;
        correoElectronico = "";
        fechaAlta = null;
    }

    public Cliente(Tarifa tarifa, String nombre, String nif, Direccion direccion, String correoElectronico){
        this.tarifa = tarifa;
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        fechaAlta = new Date();
    }

    //Metodos
    public Date getFecha(){
        return fechaAlta;
    }
}
