package modelo.facturacion;

public class TarifaReducida extends Oferta {
    private double valor = .05d / 60;

    public TarifaReducida(Tarifa trf) {
        super(trf, .05 / 60);
    }

    @Override
    public String getDescripcion() {
        return "Tardes reducidas (16:00 a 20:00): 0,05 €/min";
    }

    @Override
    public Double getCosteLlamada(Llamada llamada) {
        Double precioTarifaAnterior = getCosteLlamada(llamada);
        if (llamada.getFecha().getHour() >= 16 && llamada.getFecha().getHour() < 20) {
            Double precioTarifaActual = (double) llamada.getDuracion() * valor;
            if (precioTarifaActual < precioTarifaAnterior) {
                return precioTarifaActual;
            }
        }
        return precioTarifaAnterior;

    }
}
