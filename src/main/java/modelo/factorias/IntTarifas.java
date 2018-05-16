package modelo.factorias;

import modelo.facturacion.Tarifa;
import modelo.facturacion.TarifaBasica;
import modelo.facturacion.TarifaFree;
import modelo.facturacion.TarifaReducida;

public interface IntTarifas {
    TarifaBasica getTarifaBasica(double valor);
    TarifaFree getTarifaFree(Tarifa trf);
    TarifaReducida getTarifaReducida(Tarifa trf);
}
