package modelo.facturacion;

import modelo.tiempo.FormateadorFecha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Period;

public class PeriodoFacturacion extends FormateadorFecha implements Serializable {
    private LocalDateTime inicioPeriodo;
    private LocalDateTime finPeriodo;

    public PeriodoFacturacion() {
        inicioPeriodo = LocalDateTime.now();
        finPeriodo = LocalDateTime.now();
    }

    public PeriodoFacturacion(LocalDateTime inicio, LocalDateTime fin) {
        inicioPeriodo = inicio;
        finPeriodo = fin;
    }

    public void calcularPeriodo(LocalDateTime fecha){
        /*
        Método pensado para darle valores automáticamente al inicio y final
        del periodo dependiendo de la fecha que se le pase. Asumimos que se
        quiere facturar el periodo del mes anterior a la fecha que se le pasa.
         */
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        int anyo = fecha.getYear();
        Period period;
        inicioPeriodo = inicioPeriodo.withYear(anyo).withMonth(mes).withDayOfMonth(1);
        finPeriodo = inicioPeriodo.withDayOfMonth(28);
    }

    public String getPeriodo() {
        return String.format("%s - %s", formatoFecha.format(inicioPeriodo), formatoFecha.format(finPeriodo));
    }

    public LocalDateTime getInicioPeriodo() {
        return inicioPeriodo;
    }

    public LocalDateTime getFinPeriodo() {
        return finPeriodo;
    }
}
