package modelo.generadores;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Created by al361930 on 27/03/18.
 */
public class GeneradorDatos {
    private Random random = new Random();

    public String getTelefono(){
        int pref = new int[] {9, 9, 9, 6, 6, 6, 6, 6, 9, 9, 6, 6, 6, 8, 9, 6, 9, 6, 9, 6, 9}[random.nextInt(20)];
        return String.format("%d%08d", pref, random.nextInt(99999999));
    }

    public int getDuracion(){
        /*
        Generamos tres posibles tipos de llamadas, llamadas cortas, medianas y largas
        las largas serán poco frecuentes, las media tendrán más posibilidades y las
        cortas serán la más habituales para ello utilizamos un vector donde aparece
        mayoritariamente un indice para llamadas cortas, unos pocos para media y menos
        aun para largas.
         1: cortas (>=300") +/- 5 min.;
         2: medianas (>600") +/- 20 min.;
         3: largas (> 7200") +/- 2 horas;
         */
        int vector[] = {
                1, 1, 1, 2, 1, 1, 1, 2, 1, 1,
                1, 2, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 3, 1, 1, 2, 1, 1, 1, 1,
                1, 2, 1, 1, 1, 1, 1, 1, 1, 1
        };
        int indice = vector[random.nextInt(vector.length)];
        return random.nextInt(indice == 1 ? 300 : indice == 2 ? 1200 : 7200);
    }

    public LocalDateTime getFecha() {
        LocalDateTime fecha = LocalDateTime.now();
        int i = random.nextInt(9);
        int positivo = random.nextInt(50);
        if (positivo < 25) i = -i;
        return fecha.withDayOfMonth(i);
    }


}
