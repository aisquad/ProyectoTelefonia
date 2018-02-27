package main;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class Llamada implements Fecha{
    Date fechaInicio;
    long duracion;

    Llamada(){
        fechaInicio = new Date();
        fechaInicio.getTime();

    }

    public Date sumarSegundos(long segundos){

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicio);
            calendar.add(Calendar.SECOND, (int) segundos);
            return calendar.getTime();
    }

    public String getFecha() {
        Locale locale = new Locale("es", "ES");
        DateFormat formatter = new SimpleDateFormat("E dd-MM-YYYY HH:mm:ss", locale);
        return formatter.format(fechaInicio);
    }
}
