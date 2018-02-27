package main;

import clientes.Cliente;
import facturacion.Factura;
import facturacion.Llamada;

import java.util.Set;

/**
 * Created by al361930 on 27/02/18.
 */
public class Gestor {
    //Atributos
    private Set<Cliente> clientes;
    private Set<Factura> facturas;
    private Set<Llamada> llamadas;

    //Contructores
    public Gestor() {
        clientes = null;
        facturas = null;
        llamadas = null;
    }

    public Gestor (Set<Cliente> clientes, Set<Factura> facturas, Set<Llamada> llamadas) {
        this.clientes = clientes;
        this.facturas = facturas;
        this.llamadas = llamadas;
    }

    //Metodos
    public Set<Cliente> listarClientes(){
      return  null;
    }

    public Set<Llamada> listarLlamadasCliente(Cliente cliente) {
        return null;
    }

    public Set<Factura> listarFacturasCliente(Cliente cliente) {
        return null;
    }



}
