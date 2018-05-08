package modelo.facturacion;

import modelo.clientes.Cliente;
import modelo.tiempo.Fecha;
import modelo.tiempo.FormateadorFecha;
import modelo.tiempo.SegundosATexto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by al361930 on 27/02/18.
 */

public class Llamada extends FormateadorFecha implements Serializable, Fecha {
    //Atributos
    private static Integer id;
    private String numeroTelefono;
    private LocalDateTime fechaInicio;
    private int duracion;
    private Cliente cliente;


    //Contructores
    public Llamada () {
        numeroTelefono = "";
        fechaInicio = LocalDateTime.now();
        duracion = 0;
        cliente = null;
        id++;
    }

    public Llamada(String numeroTelefono, int duracion, Cliente cliente) {
        this.numeroTelefono = numeroTelefono;
        fechaInicio = LocalDateTime.now();
        this.duracion = duracion;
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fechaInicio;
    }

    public Integer getId(){
        return id;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public String getTelefono() {
        return numeroTelefono;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setFecha(LocalDateTime fecha){
        fechaInicio = fecha;
    }

    public String toString() {
        return String.format(
            "%40s %14s %15s %5s €",
            formatoFecha.format(fechaInicio),
            numeroTelefono,
            new SegundosATexto().segundosATextoAbreviado(duracion),
            String.format("%01.2f", cliente.getTarifa().getValor() * duracion)
        );
    }

}
