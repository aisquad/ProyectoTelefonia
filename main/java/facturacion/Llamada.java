package facturacion;

import clientes.Cliente;
import interfaces.Fecha;

import java.util.Date;

/**
 * Created by al361930 on 27/02/18.
 */

public class Llamada implements Fecha{
    //Atributos
    private String numeroTelefono;
    private Date fechaInicio;
    private int duracion;
    private Cliente cliente;

    //Contructores
    public Llamada () {
        numeroTelefono = "";
        fechaInicio = new Date();
        duracion = 0;
        cliente = null;
    }

    public Llamada(String numeroTelefono, int duracion, Cliente cliente) {
        this.numeroTelefono = numeroTelefono;
        this.duracion = duracion;
        this.cliente = cliente;
        fechaInicio = new Date();
    }

    public Date getFecha() {
        return fechaInicio;
    }

    public String getTelefono() {
        return numeroTelefono;
    }

    public int getDuracion() {
        return duracion;
    }


}
