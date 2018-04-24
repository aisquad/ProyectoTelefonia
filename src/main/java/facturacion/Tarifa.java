package facturacion;

import java.io.Serializable;

/**
 * Created by al361930 on 27/02/18.
 */
public abstract class Tarifa implements Serializable {
    //Atributos
    private double valor = .15 / 60;

    /*
    private double basica = .15 / 60;
    private double reducida = .05 / 60;
    private double gratis = 0d;
     */

    //Contructores
    public  Tarifa(Double valor) {
        this.valor = valor;
    }

    //Metodos
    public Double getValor() {
        return valor;
    }

    public String getTarifaConFormato() {
        return String.format("%01.2f", valor);
    }

    public abstract String getDescripcion();

    public abstract Double getCosteLlamada(Llamada llamada);
}
