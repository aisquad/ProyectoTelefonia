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
    //DONE: hay que cambiar el método eliminar para que conserve el cliente y cambie el atributo de activo a inactivo
    //No se puede perder un cliente si no queremos perder la facturación
    private boolean activo; //Si se le da de baja es false

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
        fechaAlta = new Date();
        activo = true;
    }

    //Metodos
    public Date getFecha(){
        return fechaAlta;
    }

    public String getNIF() {
        return nif;
    }

    public String getNombreCompleto() {
        return nombre;
    }

    public String getPoblacion() {
        return String.format("%s %s (%s)", poblacion.getCodigoPostal(), poblacion.getNombre(), poblacion.getProvincia());
    }

    public String getEmail() {
        return correoElectronico;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public void setPoblacion(Poblacion poblacion) {
        this.poblacion = poblacion;
    }

    public void setActivo(boolean bool) {
        activo = bool;
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
}
