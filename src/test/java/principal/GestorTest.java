package principal;

import clientes.Cliente;
import clientes.Particular;
import facturacion.Factura;
import facturacion.Llamada;
import facturacion.Tarifa;
import generadores.GeneradorPoblacion;
import org.junit.BeforeClass;
import org.junit.Test;
import poblaciones.Poblacion;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


public class GestorTest {

    //atributos
    private static Cliente cliente;
    private static Llamada llamada;
    private static Gestor gestor = new Gestor();
    private static Factura factura ;


    //metodos
    @BeforeClass
    public static void crearCliente(){
        //Crear cliente para test

        Tarifa tarifa = new Tarifa();
        String nombre = "Alejandra";
        String apellido = "Sinuosa";
        Poblacion poblacion = new Poblacion();
        GeneradorPoblacion genPob = new GeneradorPoblacion();
        poblacion = genPob.getPoblacion();
        String correo = "ale_ale@uji.es";
        String nif = "52793514G";
        cliente = new Particular(tarifa, nombre, nif, poblacion, correo, apellido);
        gestor.altaNuevoCliente(cliente);

        //Crear factura y llamada para test
        llamada = new Llamada("697454669", 70, cliente);
        gestor.insertarLlamada(llamada.getCliente().getNIF(), llamada.getTelefono(), llamada.getDuracion());
        factura = gestor.emitirFactura(cliente.getNIF(), new Date());
    }

    @Test
    public void cambiarTarifaTest(){
        Tarifa tarifa = new Tarifa();
        assertThat(tarifa.getValor(), is(0.0015d));
        tarifa = new Tarifa(.001d);
        assertThat(tarifa.getValor(), is(0.001));
    }

    @Test
    public void buscarCliente(){
        assertThat(gestor.buscarCliente(cliente.getNIF()), is(cliente));
        assertThat(gestor.buscarCliente("4701211B"), is(nullValue()));
    }

    @Test
    public void emitirFacturaTest(){
        assertThat(factura.getImporte(), is(0.105));

    }

    @Test
    public void obtenerFacturaTest(){
        assertThat(gestor.obtenerFactura(factura.getIdFactura()), is(factura));
    }

}
