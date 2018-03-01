package facturacion;

import clientes.Cliente;
import interfaces.Fecha;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by al361930 on 27/02/18.
 */
public class Factura implements Fecha {
    //Atributos
    private static int idFactura;
    private Date fechaEmision;
    private PeriodoFacturacion periodoFacturacion;
    private Double importe;
    private Cliente cliente;

    //Constructores
    public Factura () {
        idFactura++;
        fechaEmision = new Date();
        periodoFacturacion = null;
        importe = 0d;
        cliente = null;
    }

    public Factura (Double importe, Cliente cliente) {
        idFactura++;
        fechaEmision = new Date();
        periodoFacturacion.calcularPeriodo(fechaEmision);
        this.importe = importe;
        this.cliente = cliente;
    }

    //Metodos
    public String getIdFactura(){
        return String.format("%10d", idFactura);
    }

    public Date getFecha() {
        return fechaEmision;
    }
}
