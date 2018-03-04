package facturacion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PeriodoFacturacion {
    private Date inicioPeriodo;
    private Date finPeriodo;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat(
            "EEEE d 'de' MMMM 'de' YYYY",
            new Locale("es", "ES")
    );

    public PeriodoFacturacion() {
        inicioPeriodo = new Date();
        finPeriodo = new Date();
    }

    public PeriodoFacturacion(Date inicio, Date fin) {
        inicioPeriodo = inicio;
        finPeriodo = fin;
    }

    public void calcularPeriodo(Date fecha){
        /*
        Método pensado para darle valores automáticamente al inicio y final
        del periodo dependiendo de la fecha que se le pase. Asumimos que se
        quiere facturar el periodo del mes anterior a la fecha que se le pasa.
         */
        Calendar mesAnterior = Calendar.getInstance();
        Calendar calInicio = Calendar.getInstance();
        Calendar calFin = Calendar.getInstance();
        mesAnterior.setTime(fecha);
        mesAnterior.add(Calendar.MONTH, -1);


        int diaInicio = mesAnterior.getActualMinimum(mesAnterior.DAY_OF_MONTH);
        int diaFin = mesAnterior.getActualMaximum(mesAnterior.DAY_OF_MONTH);

        calInicio.set(
                mesAnterior.get(mesAnterior.YEAR),
                mesAnterior.get(mesAnterior.MONTH),
                diaInicio
        );
        calFin.set(
                mesAnterior.get(mesAnterior.YEAR),
                mesAnterior.get(mesAnterior.MONTH),
                diaFin
        );
        inicioPeriodo = calInicio.getTime();
        finPeriodo = calFin.getTime();
    }

    public String getPeriodo() {
        return String.format("%s - %s", formatoFecha.format(inicioPeriodo), formatoFecha.format(finPeriodo));
    }
}