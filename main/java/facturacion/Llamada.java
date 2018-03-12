package facturacion;

import clientes.Cliente;
import tiempo.Fecha;
import tiempo.FormateadorFecha;
import tiempo.SegundosATexto;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by al361930 on 27/02/18.
 */

public class Llamada extends FormateadorFecha implements Fecha {
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
        fechaInicio = new Date();
        this.duracion = duracion;
        this.cliente = cliente;
        establecerFechaInicio();
    }

    private void establecerFechaInicio() {
        /*
        Este metodo está pensado para que se cree una fecha de llamada
        en el periodo de facturación del mes anterior.

        La otra solución es indicar qué fecha le queremos dar a la factura
        mediante el método insertarLlamada(fecha, ...) en el gestor.
        */
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaInicio);
        cal.add(Calendar.MONTH, -1);
        fechaInicio = cal.getTime();
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

    public void setFecha(Date fecha){
        fechaInicio = fecha;
    }

    public String toString() {
        return String.format(
            "%s %s, %s",
            numeroTelefono,
            formatoFecha.format(fechaInicio),
            new SegundosATexto().segundosATextoAbreviado(duracion)
        );
    }

}
