package facturacion;

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
        if (llamada.getFecha().getTime() > 16)
        return null;
    }
}
