package main;

import es.uji.belfern.generador.GeneradorDatosINE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import generadores.GeneradorClientes;
import generadores.GeneradorPoblacion;
import places.Poblacion;

/**
 * Created by al361930 on 20/02/18.
 */
public class Main {
    public static void main(String args[]) {
        GeneradorDatosINE gen = new GeneradorDatosINE();
        GeneradorPoblacion genPobl = new GeneradorPoblacion();
        for (int i = 0; i < 100; i++) {
            String provincia = gen.getProvincia();
            String poblacion = gen.getPoblacion(provincia);

            System.out.printf("%s %s %s %s %s%n\t%s (%s)%n", gen.getNombreHombre(),
                    gen.getApellido(), gen.getApellido(), gen.getEdad(), gen.getNIF(),
                    poblacion, provincia
            );
        }

        Date now = new Date();
        Long timestamp = now.getTime();
        SimpleDateFormat fecha = new SimpleDateFormat(
                "EEEE dd 'de' MMMM 'de' YYYY",
                new Locale("es", "ES")
        );
        System.out.printf(
                "\n\ntimestamp de now: %d(long)\nnow.toString(): %s\nnow en castellano: %s\n\n"
                , timestamp, now.toString(),fecha.format(now)
        );
        GeneradorClientes genCli = new GeneradorClientes();
        for(int i = 0; i<500; i++){
            Poblacion poblacion = genPobl.getPoblacion();
            String nombre = genCli.getNombre();
            String apellido = genCli.getApellido();
            int edad = genCli.getEdad();
            String dni = genCli.getDNI();
            System.out.printf(
                    "%s %s %d %s%n\t%s %s (%s)%n",
                    nombre, apellido, edad, dni,
                    poblacion.getZipCode(), poblacion.getNombre(), poblacion.getProvince()
            );
        }
    }

}
