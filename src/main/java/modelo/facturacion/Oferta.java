package modelo.facturacion;

public abstract class Oferta extends Tarifa {
    private Tarifa tarifa;

    public Oferta(Tarifa trf, double nuevoValor) {
        super(nuevoValor);
        tarifa = trf;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

 }
