package facturacion;

import clientes.Cliente;
import interfaces.Fecha;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

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
        establecerFechaInicio();
    }

    private void establecerFechaInicio() {
        /*
        Este metodo está pensado para que se cree una fecha de llamada
        en el periodo de facturación del mes anterior.

        La otra solución es indicar qué fecha le queremos dar a la factura
        mediante el método introducirFechaInicio(String fecha).
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

}
