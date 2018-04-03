package principal;

import execpciones.ExcepcionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tiempo.SegundosATextoParametrizadaTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExcepcionTest.class,
        GestorTest.class,
        SegundosATextoParametrizadaTest.class
})
public class TodasLasPruebas{};
