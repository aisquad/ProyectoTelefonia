package principal;

import clientes.Cliente;
import clientes.Particular;
import facturacion.Factura;
import facturacion.Llamada;
import facturacion.Tarifa;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import poblaciones.Poblacion;
import principal.Gestor;

import java.util.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class GestorTest {

    //atributos
    private Cliente cliente;
    private Llamada llamada;
    private Gestor gestor = new Gestor();
    private Factura factura ;


    //metodos
    @Before
    public void crearCliente(){
        //Crear cliente para test

        Tarifa tarifa = new Tarifa();
        String nombre = "Alejandra";
        String apellido = "Sinuosa";
        Poblacion poblacion = new Poblacion();
        String correo = "ale_ale@uji.es";
        String nif = "52793514G";
        cliente = new Particular(tarifa, nombre,nif, poblacion, correo, apellido);
        gestor.altaNuevoCliente(cliente);

        //Crear factura y llamada para test
        llamada = new Llamada("697454669", 70, cliente);
        gestor.insertarLlamada(llamada.getCliente().getNIF(), llamada.getTelefono(), llamada.getDuracion());
        factura = gestor.emitirFactura(cliente.getNIF(), new LocalDateTime());
    }

    @Test
    public void cambiarTarifaTest(){
        Tarifa tarifa = new Tarifa(.001d);
        assertEquals(0.001, tarifa.getTarifa(), 0.0001);
    }

    @Test
    public void buscarCliente(){
        assertEquals(cliente , gestor.buscarCliente(cliente.getNIF()));
        assertEquals(null, gestor.buscarCliente("4701211B"));
    }

    @Test
    public void emitirFacturaTest(){
        assertEquals(10.50, factura.getImporte(), 0.000001);

    }

    @Test
    public void obtenerFacturaTest(){
        assertEquals(factura, gestor.obtenerFactura(factura.getIdFactura()));
    }

}
