package modelo.factorias;

import modelo.facturacion.Tarifa;
import modelo.facturacion.TarifaBasica;
import modelo.facturacion.TarifaFree;
import modelo.facturacion.TarifaReducida;

public class FactoriaTarifas implements IntTarifas {
    @Override
    public TarifaBasica getTarifaBasica(double valor) {
        return new TarifaBasica(valor);
    }

    @Override
    public TarifaFree getTarifaFree(Tarifa trf) {
        return new TarifaFree(trf);
    }

    @Override
    public TarifaReducida getTarifaReducida(Tarifa trf) {
        return new TarifaReducida(trf);
    }
}
