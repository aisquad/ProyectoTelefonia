package modelo.facturacion;

import java.time.DayOfWeek;

public class TarifaReducida extends Tarifa {
    private double valor = .05d / 60;

    public TarifaReducida(Double nuevoValor) {
        super(nuevoValor);
    }

    @Override
    public String getDescripcion() {
        return "Tardes reducidas (17:00 a 21:59): 0,05 €/min";
    }

    @Override
    public Double getCosteLlamada(Llamada llamada) {
        Double precioAnt = getCosteLlamada(llamada);
        Double precioAhora = 0.0;
        if (llamada.getFecha().getHour() >= 16 && llamada.getFecha().getHour() < 20) {
            precioAhora = (double) llamada.getDuracion() * valor;
        }
        if(llamada.getFecha().getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            precioAhora = 0.0;
        }
        if (precioAnt < precioAhora){
            return precioAnt;
        }
        return precioAhora;

    }
}
