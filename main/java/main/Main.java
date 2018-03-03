package main;

import clientes.Cliente;
import clientes.Particular;
import es.uji.belfern.generador.GeneradorDatosINE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import facturacion.PeriodoFacturacion;
import generadores.GeneradorParticulares;
import generadores.GeneradorPoblacion;
import poblaciones.Poblacion;

/**
 * Created by al361930 on 20/02/18.
 */
public class Main {
    public static void main(String args[]) {
        //Generador profe
        System.out.println("Datos proporcionados por el generador del profesorado.");
        GeneradorDatosINE gen = new GeneradorDatosINE();
        for (int i = 0; i < 5; i++) {
            String provincia = gen.getProvincia();
            String poblacion = gen.getPoblacion(provincia);

            System.out.printf("%s %s %s %s %s%n\t%s (%s)%n", gen.getNombreHombre(),
                    gen.getApellido(), gen.getApellido(), gen.getEdad(), gen.getNIF(),
                    poblacion, provincia
            );
        }

        /*
        Generador propio

        Nuestro generador crea nombres de personas basado en la lista de admitidos en las
        universidades de la Comunidad Valenciana del año 2016. Y genera un Cliente con nombre,
        apellido, dni, email  (cosa que no hace el generador proporcionado por el profesor,
        pero que se nos pide en el enunciado).

        También escoge al 'azar' un municipio obteniendo su CP (ídem que email).
         */
        System.out.println("\n\n\nEmpieza el muestreo de datos del generador propio.");
        GeneradorParticulares genCli = new GeneradorParticulares();
        GeneradorPoblacion genPobl = new GeneradorPoblacion();
        for(int i = 0; i<5; i++){
            Poblacion poblacion = genPobl.getPoblacion();
            String nombre = genCli.getNombre();
            String apellido =  genCli.getApellido();
            System.out.printf(
                    "%s %s %d %s %s%n\t%s %s (%s)%n",
                    nombre, apellido, genCli.getEdad(), genCli.getDNI(), genCli.getEmail(nombre, apellido),
                    poblacion.getCodigoPostal(), poblacion.getNombre(), poblacion.getProvincia()
            );
        }

        //Prueba manipulación fechas
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
        PeriodoFacturacion p = new PeriodoFacturacion();
        p.calcularPeriodo(new Date());
        System.out.printf("Periodo de facturación por defecto: %s%n", p.getPeriodo());

        //Código interfaz consola.
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
                Particular cliente = new Particular();

                altaCliente(cliente);
                break;
            case BAJA_CLIENTE:
                //bajaCliente();
                break;
        }
    }

    public static void altaCliente(Cliente cliente){

    }
}
