package facturacion;

import java.io.Serializable;

/**
 * Created by al361930 on 27/02/18.
 */
public class Tarifa implements Serializable {
    //Atributos
    private double tarifa;

    //Contructores
    public Tarifa() {
        tarifa = 0.15d;
    }

    public  Tarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    //Metodos
    public Double getTarifa() {
        return tarifa;
    }

    public String getTarifaConFormato() {
        return String.format("%01.2f", tarifa);
    }
}
