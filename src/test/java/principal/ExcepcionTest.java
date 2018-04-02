package principal;

import clientes.Cliente;
import clientes.Particular;
import excepciones.LetraIncorrectaException;
import facturacion.Tarifa;
import org.junit.Test;
import poblaciones.Poblacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ExcepcionTest {

    @Test
    public void comprobarExcepcionDeNIF(){
        int nif = 52799498;
        try {
            String tabla = "TRWAGMYFPDXBNJZSQVHLCKE";
            char letra = tabla.charAt(nif % tabla.length());
            if (letra == 'G')
                System.out.println("OK");
            char c = 'D'; //el usuario ha introducido D (en vez de G)
            if (c != letra)
                throw new LetraIncorrectaException();
            fail("No se ha producido la excepción.");
        } catch (LetraIncorrectaException e){
            e.printStackTrace();
        }
    }

}
