package main;

import es.uji.belfern.generador.GeneradorDatosINE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import generadores.GeneradorClientes;
import generadores.GeneradorPoblacion;
import places.Poblacion;

import static main.OpcionesMenu.getMenu;

/**
 * Created by al361930 on 20/02/18.
 */
public class Main {
    public static void main(String args[]) {
        GeneradorDatosINE gen = new GeneradorDatosINE();
        for (int i = 0; i < 5; i++) {
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
        GeneradorPoblacion genPobl = new GeneradorPoblacion();
        for(int i = 0; i<5; i++){
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


        String menu = OpcionesMenu.getMenu();
        System.out.println(menu);
        Scanner scanner = new Scanner(System.in);
        String usrinput = "";
        Integer opcion = 0;
        do {
            System.out.print("Elije una opción: ");
            usrinput = scanner.next();
            if (!usrinput.equals("") && usrinput.matches("^\\d+$")) {
                opcion = Integer.valueOf(usrinput);
            }
        } while (opcion  - 1 < 0 || opcion - 1 > OpcionesMenu.values().length);
        OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
        System.out.printf("Has elegido la opción: %s%n", opcionMenu.getDescripcion());

        switch(opcionMenu) {
            case ALTA_NUEVO_CLIENTE:
                //altaCliente();
                break;
            case BAJA_CLIENTE:
                //bajaCliente();
                break;
        }
    }

}
