package facturacion;

import java.util.Calendar;
import java.util.Date;

public abstract class PeriodoFacturacion {
    private Date inicioPeriodo;
    private Date finPeriodo;

    public PeriodoFacturacion() {
        inicioPeriodo = new Date();
        finPeriodo = new Date();
    }

    public PeriodoFacturacion(Date inicio, Date fin) {
        inicioPeriodo = inicio;
        finPeriodo = fin;
    }
    public void calcularPeriodo(Date fecha){
        //TODO: asegurar que la fecha sea correcta para el mes de enero (debería ser dic año-1)
        Calendar calendario = Calendar.getInstance();
        Calendar calInicio = Calendar.getInstance();
        Calendar calFin = Calendar.getInstance();
        calendario.setTime(fecha);
        int diaInicio = calendario.getActualMinimum(calendario.DAY_OF_MONTH);
        int diaFin = calendario.getActualMaximum(calendario.DAY_OF_MONTH);
        calInicio.set(diaInicio, calendario.MONTH-1, calendario.YEAR);
        calFin.set(diaFin, calendario.MONTH-1, calendario.YEAR);
        inicioPeriodo = calInicio.getTime();
        finPeriodo = calFin.getTime();
    }
}
