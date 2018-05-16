package modelo.tiempo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SegundosATextoParametrizadaTest {
    int segundos;
    String resultado;
    SegundosATexto obj = new SegundosATexto();

    public SegundosATextoParametrizadaTest(int seg, String res){
        segundos = seg;
        resultado = res;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parametros(){
        return Arrays.asList(
            new Object[][]{
                    /* segundos */
                {10, "10 s"},

                    /* minutos y segundos */
                {3466, "57 m, 46 s"},
                {1234, "20 m, 34 s"},
                {290, "4 m, 50 s"},

                    /* minutos */
                {300, "5 m"},
                {420, "7 m"},

                    /* horas, minutos y segundos */
                {6347, "1 h, 45 m, 47 s"},

                    /* horas y minutos */
                {4200, "1 h, 10 m"},

                    /* horas y segundos */
                {3606, "1 h, 6 s"},

                    /* dias, horas, minutos y segundos */
                {122350, "1 d, 9 h, 59 m, 10 s"},

                    /* dias, horas y minutos */
                {183840, "2 d, 3 h, 4 m"},

                    /* dias, horas y segundos */
                {187206, "2 d, 4 h, 6 s"},

                    /* dias y horas*/
                {93600, "1 d, 2 h"},

                    /* dias y minutos*/
                {261180, "3 d, 33 m"},

                    /* dias y segundos */
                {432018, "5 d, 18 s"}
            }
        );
    }

    @Test
    public void  segundosATextoAbreviadoTest(){
        int i = 0;
        assertThat (obj.segundosATextoAbreviado(segundos), is(resultado));
    }
}
