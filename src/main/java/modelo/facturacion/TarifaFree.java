package modelo.facturacion;

import java.time.DayOfWeek;

public class TarifaFree extends Oferta {
    public TarifaFree(Tarifa trf) {
        super(trf, 0d);
    }

    @Override
    public String getDescripcion() {
        return getTarifa().getDescripcion() + "Tarifa Free: 0,00 € / min.";
    }

    @Override
    public Double getCosteLlamada(Llamada llamada) {
        double precioAnt = getTarifa().getCosteLlamada(llamada);
        if (llamada.getFecha().equals(DayOfWeek.SUNDAY)) {
            double precioActual = llamada.getDuracion() * getValor();
            if (precioActual < precioAnt) {
                return precioActual;
            }
        }
        return precioAnt;
    }
}
