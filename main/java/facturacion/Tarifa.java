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
    public Double getTarifa() {
        return tarifa;
    }

    public String getTarifaConFormato() {
        return String.format("%0.2f", tarifa);
    }
}
