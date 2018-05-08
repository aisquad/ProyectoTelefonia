package modelo.clientes;

import modelo.tiempo.Fecha;
import modelo.facturacion.Tarifa;
import modelo.poblaciones.Poblacion;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by al361930 on 27/02/18.
 */
public abstract class Cliente implements Serializable, Fecha {
    //Atributos
    private Tarifa tarifa;
    private String nombre;
    private String nif;
    private Poblacion poblacion;
    private String correoElectronico;
    private LocalDateTime fechaAlta;

    protected boolean activo; //Si se le da de baja es false

    //Contructor
    public Cliente (){
        tarifa = null;
        nombre = "";
        nif = "";
        poblacion = null;
        correoElectronico = "";
        fechaAlta = null;
        activo = true;
    }

    public Cliente(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico){
        this.tarifa = tarifa;
        this.nombre = nombre;
        this.nif = nif;
        this.poblacion = poblacion;
        this.correoElectronico = correoElectronico;
        fechaAlta = LocalDateTime.now();
        activo = true;
    }

    //Metodos
    public LocalDateTime getFecha(){
        return activo ? fechaAlta : null;
    }

    public String getNIF() {
        return activo ? nif : "";
    }

    public String getNombreCompleto() {
        return activo ? nombre : "";
    }

    public String getPoblacion() {
        String rtn = "";
        if (activo)
            rtn = String.format(
                "%s %s (%s)",
                poblacion.getCodigoPostal(), poblacion.getNombre(), poblacion.getProvincia()
            );
        return rtn;
    }

    public String getEmail() {
        return activo ? correoElectronico : "";
    }

    public Tarifa getTarifa() {
        return activo ? tarifa : null;
    }

    public void setTarifa(Tarifa tarifa) {
        if (activo)
            this.tarifa = tarifa;
    }

    public void setPoblacion(Poblacion poblacion) {
        if (activo)
            this.poblacion = poblacion;
    }

    public void setActivo(boolean bool) {
        activo = bool;
    }

    public boolean estadoActivo() {
        return activo;
    }

    @Override
    public String toString() {
        String rtn = "";
        if (activo)
            rtn = String.format(
                "%s %s %s, %s",
                getNIF(),
                getNombreCompleto(),
                getEmail(),
                getPoblacion()
            );
        else
            rtn = "<cliente eliminado>";
        return rtn;
    }

    public String mostrarDatosAnterioresABaja() {
        /*
        Forzamos el muestreo de datos para ello modificamos
        temporalmente el atributo activo para facilitar la
        extracción de datos que están ocultos si el usuario
        está dado de baja (activo = false)
         */
        boolean temp = activo;
        activo = true;
        String rtn =  String.format(
            "%s %s %s, %s",
            getNIF(),
            getNombreCompleto(),
            getEmail(),
            getPoblacion()
        );
        activo = temp;
        return rtn;
    }
}
