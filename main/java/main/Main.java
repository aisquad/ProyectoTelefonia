package main;

import com.sun.org.apache.xpath.internal.SourceTree;
import es.uji.belfern.generador.GeneradorDatosINE;
import places.Places;
import places.Town;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by al361930 on 20/02/18.
 */
public class Main {
    static Places places = new Places();

    public static void main(String args[]) {

        /*
        GeneradorDatosINE gen = new GeneradorDatosINE();
        for (int i = 0; i < 100; i++) {
            System.out.printf("%s %s %s %s %s (%s)\n", gen.getNombreHombre(),
                    gen.getApellido(), gen.getApellido(), gen.getEdad(), gen.getNIF(),
                    gen.getPoblacion(gen.getProvincia()), gen.getProvincia()
            );
        }
*/

        System.out.println("inserta un numero:");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Integer opción = Integer.valueOf(str);
        System.out.printf("has pulsado: %d", opción);


        Date date;
        Date now = new Date();
        Long timestamp = now.getTime();
        System.out.println(timestamp);
        System.out.println(now.toString());


        places.start();
        for (int i = 100; i < 100; i++) {
            Town t = places.fetchTown();
            System.out.printf("%s %s (%s)%n", t.getZipCode(), t.getName(), t.getProvince());


        }
    }
}
