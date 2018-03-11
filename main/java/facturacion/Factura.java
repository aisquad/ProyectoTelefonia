package facturacion;

import clientes.Cliente;
import interfaces.Fecha;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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
    private ArrayList<Llamada> llamadas;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat(
        "EEEE d 'de' MMMM 'de' YYYY",
        new Locale("es", "ES")
    );

    //Constructores
    public Factura () {
        idFactura++;
        fechaEmision = new Date();
        periodoFacturacion = new PeriodoFacturacion();
        importe = 0d;
        cliente = null;
        llamadas = new ArrayList<Llamada>();
    }

    public Factura (Cliente cliente, ArrayList<Llamada> llamadas) {
        idFactura++;
        fechaEmision = new Date();
        periodoFacturacion.calcularPeriodo(fechaEmision);
        calcularImporte();
        this.cliente = cliente;
        this.llamadas = llamadas;
    }

    //Metodos
    public int getIdFactura(){
        return idFactura;
    }

    public Date getFecha() {
        return fechaEmision;
    }

    public PeriodoFacturacion getPeriodoDeFacturacion() {
        return periodoFacturacion;
    }

    public Double getImporte(){
        return importe;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void calcularImporte() {
        int duracionTotal = 0;
        for(int i = 0 ; i < llamadas.size(); i++)
            duracionTotal += llamadas.get(i).getDuracion();
        importe = duracionTotal * cliente.getTarifa().getTarifa();
    }

    @Override
    public String toString() {
        calcularImporte();
        String rtn;
        rtn = String.format(
            "%05d %s %.2f",
            idFactura,
            formatoFecha.format(fechaEmision),
            importe
        );
        return rtn;
    }
}
