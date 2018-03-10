package main;

import clientes.Cliente;
import facturacion.Factura;
import facturacion.Llamada;
import facturacion.PeriodoFacturacion;
import facturacion.Tarifa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by al361930 on 27/02/18.
 */
public class Gestor {
    //Atributos
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Factura> facturasPorCodigo;
    private HashMap<String, ArrayList<Factura>> facturasPorCliente;
    private HashMap<String, ArrayList<Llamada>> llamadas;

    //Contructores
    public Gestor() {
        clientes = new HashMap<String, Cliente>();
        facturasPorCodigo = new HashMap<Integer, Factura>();
        facturasPorCliente = new HashMap<String, ArrayList<Factura>>();
        llamadas = new HashMap<String, ArrayList<Llamada>>();
    }

    //Metodos

    //Metodos relacionados con los clientes

    public Cliente altaNuevoCliente(Cliente cliente) {
        String nif = cliente.getNIF();
        clientes.put(nif, cliente);
        return cliente;
    }

    public Cliente bajaCliente(String nif){
        Cliente cliente = clientes.get(nif);
        if (cliente != null)
            cliente.setActivo(false);
        return cliente;
    }

    public Tarifa cambiarTarifa(String nif, Double importe) {
        Tarifa nuevaTarifa = new Tarifa(importe);
        Tarifa tarifaAntigua = new Tarifa();
        Cliente cliente = clientes.get(nif);
        if (cliente != null)
            tarifaAntigua = cliente.getTarifa();
        cliente.setTarifa(nuevaTarifa);
        return tarifaAntigua;
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
        return  llamadas.get(nif);
    }

    //Metodos relacionados con las facturasPorCodigo

    public Factura emitirFactura(String nif) {
        Cliente cliente = clientes.get(nif);
        ArrayList<Llamada> llamadasTotales = llamadas.get(nif);
        if (cliente == null && llamadasTotales == null)
            return null;

        ArrayList<Llamada> llamadasPeriodoFacturacion = new ArrayList<Llamada>();
        PeriodoFacturacion periodo = new PeriodoFacturacion();
        periodo.calcularPeriodo(new Date());

        for(Llamada llamada : llamadasTotales){
            Date fechaEmision = llamada.getFecha();
            if (
                (fechaEmision.after(periodo.getInicioPeriodo()) || fechaEmision.equals(periodo.getInicioPeriodo()))
                && (fechaEmision.before(periodo.getFinPeriodo()) || fechaEmision.equals(periodo.getFinPeriodo())) ) {
                    llamadasPeriodoFacturacion.add(llamada);
            }
        }

        Factura factura = new Factura(cliente, llamadasPeriodoFacturacion);
        facturasPorCodigo.put(factura.getIdFactura(), factura);
        ArrayList<Factura> lista = facturasPorCliente.get(nif);
        if(lista == null)
            lista = new ArrayList<Factura>();
        lista.add(factura);

        return factura;
    }

    public Factura obtenerFactura(int codigo) {
        return facturasPorCodigo.get(codigo);
    }
    public ArrayList<Factura> listarFacturasCliente(String nif) {
        return facturasPorCliente.get(nif);
    }


}
