package facturacion;

/**
 * Created by al361930 on 27/02/18.
 */
public class Tarifa {
    //Atributos
    private double tarifa;

    //Contructores
    public Tarifa() {
        tarifa = 0d;
    }

    public  Tarifa(Double tarifa) {
        this.tarifa = tarifa;
    }

    //Metodos
    public String getTarifa() {
        return String.format("%0.2d", tarifa);
    }

    public Double getTarifaDouble() {
        return tarifa;
    }
}
