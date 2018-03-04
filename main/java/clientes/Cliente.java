package clientes;

import interfaces.Fecha;
import facturacion.Tarifa;
import poblaciones.Poblacion;

import java.util.Date;

/**
 * Created by al361930 on 27/02/18.
 */
public abstract class Cliente implements Fecha{
    //Atributos
    private Tarifa tarifa;
    private String nombre;
    private String nif;
    private Poblacion poblacion;
    private String correoElectronico;
    private Date fechaAlta;

    //Contructor
    public Cliente (){
        tarifa = null;
        nombre = "";
        nif = "";
        poblacion = null;
        correoElectronico = "";
        fechaAlta = null;
    }

    public Cliente(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico){
        this.tarifa = tarifa;
        this.nombre = nombre;
        this.nif = nif;
        this.poblacion = poblacion;
        this.correoElectronico = correoElectronico;
        fechaAlta = new Date();
    }

    //Metodos
    public Date getFecha(){
        return fechaAlta;
    }

    public void setPoblacion(Poblacion poblacion) {
        this.poblacion = poblacion;
    }
}
