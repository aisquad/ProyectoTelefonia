package modelo.tiempo;

import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract  class FormateadorFecha {
    public static SimpleDateFormat formatoFecha = new SimpleDateFormat(
            "EEEE d 'de' MMMM 'de' YYYY",
            new Locale("es", "ES")
    );
}
