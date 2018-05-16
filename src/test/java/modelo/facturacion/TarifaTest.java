package modelo.facturacion;

import modelo.clientes.Particular;
import modelo.factorias.FactoriaClientes;
import modelo.factorias.FactoriaTarifas;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TarifaTest {
    static int indice = 1;
    static Llamada llamada;
    static FactoriaTarifas factTarifas;
    static FactoriaClientes factClientes;
    static DateTimeFormatter dtFormatter;
    static LocalDateTime fecha;
    static Tarifa tarifa;
    static Particular particular;

    @BeforeClass
    public static void inicio(){
        llamada = new Llamada();
        factTarifas  = new FactoriaTarifas();
        factClientes = new FactoriaClientes();
        dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    private void setFecha(String pattern) {
        fecha = LocalDateTime.parse(pattern, dtFormatter);
        System.out.printf("%d fecha: %s\n", indice++, fecha);
        llamada.setFecha(fecha);
    }

    @Test
    public void tarifaBasicaTest(){
        setFecha("2018-04-04 09:00:00");
        Particular particular = factClientes.getParticular();
        tarifa = factTarifas.getTarifaBasica(.15d/60);
        llamada = new Llamada("962123123", 60, particular );
        assertThat(factTarifas.getTarifaBasica(0.15/60).getCosteLlamada(llamada), is(0.15));

    }

    @Test
    public void tarifaReducidaTest() {
        setFecha("2018-04-04 17:00:00");
        Particular particular = factClientes.getParticular();
        llamada = new Llamada("962123123", 120, particular );
        tarifa = factTarifas.getTarifaBasica(.15d/60);
        assertThat(factTarifas.getTarifaFree(tarifa).getCosteLlamada(llamada), is(0.10));
    }


    @Test
    public void tarifaFreeTest() {
        setFecha("2018-04-29 09:00:00");
        Particular particular = factClientes.getParticular();
        llamada = new Llamada("962123123", 60, particular);
        Tarifa tarifa = particular.getTarifa();
        assertThat(tarifa.getCosteLlamada(llamada), is(0.0));
    }
}
