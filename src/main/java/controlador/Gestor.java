package controlador;

import modelo.clientes.Cliente;
import modelo.factorias.FactoriaTarifas;
import modelo.facturacion.Factura;
import modelo.facturacion.Llamada;
import modelo.facturacion.PeriodoFacturacion;
import modelo.facturacion.Tarifa;
import modelo.tiempo.Fecha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

/**
 * Created by al361930 on 27/02/18.
 */

public class Gestor implements Serializable {
    //Atributos
    private FactoriaTarifas factTarifas;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Factura> facturasPorCodigo;
    private HashMap<String, ArrayList<Factura>> facturasPorCliente;
    private HashMap<String, ArrayList<Llamada>> llamadas;
    private HashMap<Integer, Llamada> llamadasPorCodigo;


    //Contructores
    public Gestor() {
        clientes = new HashMap<String, Cliente>();
        facturasPorCodigo = new HashMap<Integer, Factura>();
        facturasPorCliente = new HashMap<String, ArrayList<Factura>>();
        llamadas = new HashMap<String, ArrayList<Llamada>>();
        llamadasPorCodigo = new HashMap<Integer, Llamada>();
    }

    //Metodos

    //Metodos relacionados con los modelo.clientes

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
        Tarifa nuevaTarifa =factTarifas.getTarifaBasica(importe);
        Tarifa tarifaAntigua =null;
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

    public Llamada insertarLlamada(String nif, String telefono, int duracion) {
        Cliente cliente = clientes.get(nif);
        if (cliente==null)
            return null;
        Llamada llamada = new Llamada(telefono, duracion, cliente);
        ArrayList listaLlamadasCliente = llamadas.get(nif);
        if (listaLlamadasCliente==null) {
            listaLlamadasCliente = new ArrayList();
            llamadas.put(nif, listaLlamadasCliente);
        }
        listaLlamadasCliente.add(llamada);
        llamadasPorCodigo.put(llamada.getId(), llamada);
        return llamada;
    }

    public Llamada insertarLlamada(LocalDateTime fecha, String nif, String telefono, int duracion) {
        Cliente cliente = clientes.get(nif);
        if (cliente==null)
            return null;
        Llamada llamada = new Llamada(telefono, duracion, cliente);
        llamada.setFecha(fecha);
        ArrayList listaLlamadasCliente = llamadas.get(nif);
        if (listaLlamadasCliente==null) {
            listaLlamadasCliente = new ArrayList();
            llamadas.put(nif, listaLlamadasCliente);
        }
        listaLlamadasCliente.add(llamada);
        llamadasPorCodigo.put(llamada.getId(), llamada);
        return llamada;
    }

    public ArrayList<Llamada> listarLlamadasCliente(String nif) {
        return  llamadas.get(nif);
    }

    //Metodos relacionados con las facturasPorCodigo

    public Factura emitirFactura(String nif, LocalDateTime fecha) {
        Cliente cliente = clientes.get(nif);
        ArrayList<Llamada> llamadasTotales = llamadas.get(nif);
        if (cliente == null && llamadasTotales == null)
            return null;

        ArrayList<Llamada> llamadasPeriodoFacturacion = new ArrayList<Llamada>();
        PeriodoFacturacion periodo = new PeriodoFacturacion();
        periodo.calcularPeriodo(fecha);

        for(Llamada llamada : llamadasTotales){
            LocalDateTime fechaEmision = llamada.getFecha();
            ChronoLocalDateTime inicio = periodo.getInicioPeriodo();
            ChronoLocalDateTime fin = periodo.getFinPeriodo();
            if ((fechaEmision.isAfter(inicio) || fechaEmision.equals(inicio)) && (fechaEmision.isBefore(fin) || fechaEmision.equals(fin))) {
                    llamadasPeriodoFacturacion.add(llamada);
            }
        }

        Factura factura = new Factura(cliente, llamadasPeriodoFacturacion);
        factura.setPeriodoDeFacturacion(fecha);
        facturasPorCodigo.put(factura.getIdFactura(), factura);
        ArrayList<Factura> lista = facturasPorCliente.get(nif);
        if(lista == null) {
            lista = new ArrayList<Factura>();
            facturasPorCliente.put(nif, lista);
        }
        lista.add(factura);

        return factura;
    }

    public Factura obtenerFactura(int codigo) {
        return facturasPorCodigo.get(codigo);
    }

    public ArrayList<Factura> listarFacturasCliente(String nif) {
        return facturasPorCliente.get(nif);
    }

    public Object [] getClientes(){
        Set<String> nifs = clientes.keySet();
        for (String nif : clientes.keySet())
            if (!clientes.get(nif).estadoActivo())
                nifs.remove(nif);
        return nifs.toArray();
    }

    public String escogeNIF(){
        Random random = new Random();
        Object array[] = getClientes();
        return (String) array[random.nextInt(array.length)];
    }

    public Set<Llamada> llamadasCliente(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return entreDosFechas(llamadasPorCodigo, fechaInicio, fechaFin);
    }

    public Set<Factura> facturasCliente(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return entreDosFechas(facturasPorCodigo,fechaInicio, fechaFin);
    }

    public Set<Cliente> altasClientes(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        return entreDosFechas(clientes, fechaInicio, fechaFin);
    }

    public <T,V extends Fecha> Set<V> entreDosFechas(HashMap<T, V> diccElementos, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        Set<V> listaElementos = new HashSet<V>();
        for(V objeto : diccElementos.values()){
            ChronoLocalDateTime inicio = fechaInicio;
            ChronoLocalDateTime fin = fechaFin;
            LocalDateTime fecha = objeto.getFecha();
            if ((fecha.isAfter(inicio) || fecha.equals(inicio)) && (fecha.isBefore(fin) || fecha.equals(fin))) {
                listaElementos.add(objeto);
            }

        }
        return listaElementos;
    }

}