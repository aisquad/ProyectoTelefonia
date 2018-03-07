package main;

import clientes.Cliente;
import facturacion.Factura;
import facturacion.Llamada;
import facturacion.PeriodoFacturacion;
import facturacion.Tarifa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by al361930 on 27/02/18.
 */
public class Gestor {
    //Atributos
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Factura> facturas;
    private HashMap<String, ArrayList<Factura>> facturasPorCliente;
    private HashMap<String, ArrayList<Llamada>> llamadas;

    //Contructores
    public Gestor() {
        clientes = null;
        facturas = null;
        llamadas = null;
    }

    //Metodos

    //Metodos relacionados con los clientes

    public boolean altaNuevoCliente(Cliente cliente) {
        String nif = cliente.getNIF();
        if (clientes.get(nif) != null)
            return false;
        clientes.put(nif, cliente);
        return true;
    }

    public boolean bajaCliente(String nif){
        if (clientes.get(nif) == null)
            return false;
        clientes.remove(nif);
        return true;
    }

    public boolean cambioTarifa(String nif, Double importe) {
        Tarifa nuevaTarifa = new Tarifa(importe);
        Cliente cliente = clientes.get(nif);
        if (cliente == null)
            return false;
        cliente.setTarifa(nuevaTarifa);
        return true;
    }

    public Cliente buscarCliente(String nif) {
        return clientes.get(nif);
    }

    public HashMap<String, Cliente> listarClientes(){
        return  clientes;
    }

    //Metodos relacionados con las llamdas

    public boolean insertarLlamada(String nif, String telefono, int duracion) {
        Cliente cliente = clientes.get(nif);
        if (cliente==null)
            return false;
        Llamada llamada = new Llamada(telefono, duracion, cliente);
        ArrayList listaLlamadasCliente = llamadas.get(nif);
        if (listaLlamadasCliente==null) {
            listaLlamadasCliente = new ArrayList();
            llamadas.put(nif, listaLlamadasCliente);
        }
        listaLlamadasCliente.add(llamada);
        return true;
    }

    public boolean insertarLlamada(Date fecha, String nif, String telefono, int duracion) {
        Cliente cliente = clientes.get(nif);
        if (cliente==null)
            return false;
        Llamada llamada = new Llamada(telefono, duracion, cliente);
        llamada.setFecha(fecha);
        ArrayList listaLlamadasCliente = llamadas.get(nif);
        if (listaLlamadasCliente==null) {
            listaLlamadasCliente = new ArrayList();
            llamadas.put(nif, listaLlamadasCliente);
        }
        listaLlamadasCliente.add(llamada);
        return true;
    }

    public ArrayList<Llamada> listarLlamadasCliente(String nif) {
        ArrayList<Llamada> aDevolver = llamadas.get(nif);
        return aDevolver;
    }

    //Metodos relacionados con las facturas

    public boolean emitirFactura(String nif) {
        Cliente cliente = clientes.get(nif);
        ArrayList<Llamada> llamadasTotales = llamadas.get(nif);
        if(cliente == null && llamadasTotales == null)
            return false;

        ArrayList<Llamada> llamadasPeriodoFacturacion = new ArrayList<Llamada>();
        PeriodoFacturacion periodo = new PeriodoFacturacion();
        periodo.calcularPeriodo(new Date());

        for(Llamada llamada : llamadasTotales){
            Date fechaEmision = llamada.getFecha();
            if (
                    (fechaEmision.after(periodo.getInicioPeriodo()) || fechaEmision.equals(periodo.getInicioPeriodo()))
                    && (fechaEmision.before(periodo.getFinPeriodo()) || fechaEmision.equals(periodo.getFinPeriodo()))){
                llamadasPeriodoFacturacion.add(llamada);
            }
        }

        Factura factura = new Factura(cliente, llamadasPeriodoFacturacion);
        facturas.put(factura.getIdFactura(), factura);
        ArrayList<Factura> lista =  facturasPorCliente.get(nif);
        if(lista == null)
            lista = new ArrayList<Factura>();
        lista.add(factura);

        return true;
    }

    public Factura obtenerFactura(int codigo) {
        return facturas.get(codigo);
    }
    public ArrayList<Factura> listarFacturasCliente(String nif) {
        return facturasPorCliente.get(nif);
    }


}
