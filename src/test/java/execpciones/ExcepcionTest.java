package execpciones;

import excepciones.LetraIncorrectaException;
import org.junit.Test;

import static org.junit.Assert.fail;

public class ExcepcionTest {

    @Test
    public void comprobarExcepcionDeNIF(){
        String nif = "52799498P";
        try {
            String tabla = "TRWAGMYFPDXBNJZSQVHLCKE";
            int num = Integer.parseInt(nif.substring(0, 8));
            char letraAlgoritmo = tabla.charAt(num % tabla.length());
            if (letraAlgoritmo == 'P')
                System.out.println("OK");
            else
                System.out.printf("La letra del algoritmo es %s", letraAlgoritmo);
            char c = 'G'; //el usuario ha introducido G (en vez de P)
            if (c != 'P')
                throw new LetraIncorrectaException();
            fail("No se ha producido la excepción.");
        } catch (LetraIncorrectaException e){
            e.printStackTrace();
        }
    }

}
