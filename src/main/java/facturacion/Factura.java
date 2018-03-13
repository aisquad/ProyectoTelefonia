package facturacion;

import clientes.Cliente;
import tiempo.Fecha;
import tiempo.FormateadorFecha;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by al361930 on 27/02/18.
 */

public class Factura extends FormateadorFecha implements Fecha {
    //Atributos
    private static int idFactura;
    private Date fechaEmision;
    private PeriodoFacturacion periodoFacturacion;
    private Double importe;
    private Cliente cliente;
    private ArrayList<Llamada> llamadas;

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
        periodoFacturacion = new PeriodoFacturacion();
        periodoFacturacion.calcularPeriodo(fechaEmision);
        this.cliente = cliente;
        this.llamadas = llamadas;
        calcularImporte();
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

    public ArrayList<Llamada> getLlamadas(){
        return llamadas;
    }

    public void setPeriodoDeFacturacion(Date fecha) {
        periodoFacturacion = new PeriodoFacturacion();
        periodoFacturacion.calcularPeriodo(fecha);
    }

    public void setPeriodoDeFacturacion(PeriodoFacturacion periodo) {
        periodoFacturacion = periodo;
        calcularImporte();
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
