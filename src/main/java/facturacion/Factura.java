package facturacion;

import clientes.Cliente;
import tiempo.Fecha;
import tiempo.FormateadorFecha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by al361930 on 27/02/18.
 */

public class Factura extends FormateadorFecha implements Serializable, Fecha {
    //Atributos
    private static int idFactura;
    private LocalDateTime fechaEmision;
    private PeriodoFacturacion periodoFacturacion;
    private Double importe;
    private Cliente cliente;
    private ArrayList<Llamada> llamadas;

    //Constructores
    public Factura () {
        idFactura++;
        fechaEmision = LocalDateTime.now();
        periodoFacturacion = new PeriodoFacturacion();
        importe = 0d;
        cliente = null;
        llamadas = new ArrayList<Llamada>();
    }

    public Factura (Cliente cliente, ArrayList<Llamada> llamadas) {
        idFactura++;
        fechaEmision = LocalDateTime.now();
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

    public LocalDateTime getFecha() {
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

    public void setPeriodoDeFacturacion(LocalDateTime fecha) {
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
        importe = duracionTotal * cliente.getTarifa().getValor();
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
