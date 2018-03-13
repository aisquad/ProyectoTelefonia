package principal;

import clientes.Cliente;
import facturacion.Llamada;
import facturacion.Tarifa;
import org.junit.Test;


public class GestorTest {

    //atributos
    private Cliente cliente;
    private Llamada llamada;

    //metodos
    @Test
    public void cambiarTarifaTest(){
        //assertThat();
        Tarifa tarifa = new Tarifa(.001d);
        //assertEquals(0.001, tarifa.getTarifa());
    }

    @Test
    public void bajaClienteTest(){

    }

    @Test
    public void buscarCliente(){

    }

    @Test
    public void emitirFacturaTest(){

    }

    @Test
    public void obtenerFacturaTest(){

    }

    @Test
    public void listrFacturasCliente(){

    }
}
