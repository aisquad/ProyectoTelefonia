package facturacion;

public class TarifaBasica extends Tarifa {

    //private double valor = .15d / 60;

    public TarifaBasica(Double valor) {
        super(valor);
    }

    @Override
    public String getDescripcion() {
        return "Tarifa Básica: 0.15 € / min.";
    }

    @Override
    public Double getCosteLlamada(Llamada llamada) {
        return llamada.getDuracion() * getValor();
    }
}
