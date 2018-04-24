package facturacion;

public class TarifaFree extends Tarifa {
    //Duo gratis con un número concreto.
    private double valor = .00d;

    public TarifaFree(Double valor) {
        super(valor);
    }

    @Override
    public String getDescripcion() {
        return "Tarifa Free: 0.00 € / min.";
    }

    @Override
    public Double getCosteLlamada(Llamada llamada) {
        return null;
    }
}
