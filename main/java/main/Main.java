package main;

import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;
import es.uji.belfern.generador.GeneradorDatosINE;

import java.text.SimpleDateFormat;
import java.util.*;

import facturacion.Factura;
import facturacion.Llamada;
import facturacion.PeriodoFacturacion;
import facturacion.Tarifa;
import generadores.GeneradorEmpresas;
import generadores.GeneradorParticulares;
import generadores.GeneradorPoblacion;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import poblaciones.Poblacion;

/**
 * Created by al361930 on 20/02/18.
 */
public class Main {
    private static Gestor gestor = new Gestor();

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

        System.out.println("\n");
        GeneradorEmpresas empresa = new GeneradorEmpresas();
        for (int i = 0; i<5; i++) {
            Poblacion poblacion = genPobl.getPoblacion();
            String nombre = genCli.getNombre();
            String apellido = genCli.getApellido();
            System.out.printf(
                    "Empresa: %s %s %s%n\t%s %s (%s)%n",
                    empresa.getAleatorio(poblacion),
                    empresa.getCIF(),
                    empresa.getEmail(nombre, apellido),
                    poblacion.getCodigoPostal(),
                    poblacion.getNombre(),
                    poblacion.getProvincia()
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
                "%n%ntimestamp de now: %d(long)%nnow.toString(): %s%nnow en castellano: %s%n%n"
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
            System.out.print("Elige una opción: ");
            usrinput = scanner.next();
            if (!usrinput.equals("") && usrinput.matches("^\\d+$")) {
                opcion = Integer.valueOf(usrinput);
            }
        } while (opcion  - 1 < 0 || opcion - 1 > OpcionesMenu.values().length);
        OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
        System.out.printf("Has elegido la opción: %s%n", opcionMenu.getDescripcion());

        switch(opcionMenu) {
            case ALTA_NUEVO_CLIENTE:
                altaCliente();
                break;
            case BAJA_CLIENTE:
                bajaCliente();
                break;
            case MODIFICAR_TARIFA:
                cambioTarifa();
                break;
            case BUSCAR_CLIENTE:
                buscarCliente();
                break;
            case LISTAR_CLIENTES:
                listarClientes();
                break;
            case ALTA_LLAMADA:
                altaLlamada();
                break;
            case LISTAR_LLAMADAS:
                listarLlamadasCliente();
                break;
            case EMITIR_FATURA:
                emitirFactura();
                break;
            case OBTENER_FACTURA:
                obtenerFactura();
                break;
            case LISTAR_FACTURAS_CLIENTE:
                listarFacturasCliente();
                break;
        }
    }

    //Metodos para los clientes

    public static void altaCliente(){
        System.out.print("El nuevo cliente será (P)articular o (E)mpresa?");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String usrinput = scanner.next();

        GeneradorPoblacion genPobl = new GeneradorPoblacion();
        Poblacion poblacion = genPobl.getPoblacion();
        GeneradorParticulares genPart = new GeneradorParticulares();
        String nombre = genPart.getNombre();
        String apellido = genPart.getApellido();
        boolean bool;
        if(usrinput.equals("P")) {
            Particular particular = new Particular(
                    new Tarifa(.2d),
                    nombre,
                    genPart.getDNI(),
                    poblacion,
                    genPart.getEmail(nombre, apellido),
                    apellido
            );
            bool = gestor.altaNuevoCliente(particular);
        } else {
            GeneradorEmpresas genEmp = new GeneradorEmpresas();
            Empresa empresa = new Empresa(
                    new Tarifa(.1d),
                    genEmp.getAleatorio(poblacion),
                    genEmp.getCIF(),
                    poblacion,
                    genEmp.getEmail(nombre, apellido)
            );
            bool = gestor.altaNuevoCliente(empresa);
        }
        if (bool) {
            System.out.println("El cliente se ha añadido satisfactoriamente.");
        } else {
            System.out.println("El cliente ya existía.");
        }
    }

    public static void bajaCliente(){
        System.out.print("Introduce el NIF del cliente a eliminar: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String usrinput = scanner.next();
        boolean bool = gestor.bajaCliente(usrinput);
        if (bool) {
            System.out.println("El cliente se ha eliminado satisfactoriamente.");
        } else {
            System.out.println("El cliente no existía.");
        }
    }

    public static void cambioTarifa() {
        System.out.print("Introduce el NIF del cliente: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String nif = scanner.next();

        System.out.print("Introduce el importe de la nueva tarifa: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        Double importe = scanner.nextDouble();

       boolean bool =  gestor.cambioTarifa(nif, importe);
        if (bool) {
            System.out.printf("Se ha cambiado la tarifa del cliente con el NIF %s satisfactoriamente.", nif);
        } else {
            System.out.println("El cliente no existía.");
        }
    }

    public static void buscarCliente() {
        System.out.print("Introduce el NIF del cliente: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String nif = scanner.next();

        Cliente cliente = gestor.buscarCliente(nif);
        if (cliente != null) {
            System.out.printf(
                    "Datos del Cliente:\n" +
                            "Nombre completo: %s\n" +
                            "NIF: %s\n" +
                            "Poblacion: %s\n" +
                            "Correo electrónico: %s\n" +
                            "Fecha de Alta: %s\n" +
                            "Tarifa: %s",
                    cliente.getNombreCompleto(),
                    cliente.getNIF(),
                    cliente.getPoblacion(),
                    cliente.getEmail(),
                    cliente.getFecha(),
                    cliente.getTarifa().getTarifa()
            );
        } else {
            System.out.println("No existe el cliente.");
        }
    }

    public static void listarClientes() {
        HashMap<String , Cliente> clientes = gestor.listarClientes();
        System.out.println("Listado de todos los clientes: \n");
        int i=1;
        for (Cliente cliente : clientes.values()){
            System.out.printf("%-3d.- %s %s", i++, cliente.getNIF(), cliente.getNombreCompleto());
        }
    }

    //Metodos para las llamadas

    public static void altaLlamada(){
        Random random = new Random();
        String tlf = String.format("9%d" ,random.nextInt(99999999));
        int duración = random.nextInt(9999);

        System.out.print("Introduce el NIF del cliente: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String nif = scanner.next();

        boolean bool = gestor.altaLlamada(nif, tlf, duración);
        if (bool) {
            System.out.println("Nueva llamada registrada.");
        } else {
            System.out.println("No se ha podido registrar la llamada.");
        }
    }

    public static void listarLlamadasCliente() {
        System.out.print("Introduce el NIF del cliente: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String nif = scanner.next();

        ArrayList<Llamada> llamadas = gestor.listarLlamadasCliente(nif);
        System.out.println("Relación de llamadas del cliente con NIF: "+nif);
        int i = 1;
        if(llamadas == null)
            System.out.println("No hay llamadas para este cliente");
        else {
            for(Llamada llamada : llamadas) {
                System.out.printf(
                        "%-3d.- Fecha: %s Tel.: %s Dur.: %d ",
                        i++,
                        llamada.getFecha(),
                        llamada.getTelefono(),
                        llamada.getDuracion()
                );
            }
        }
    }

    //Metodos para las facturas

    public static void emitirFactura() {
        System.out.print("Introduce el NIF del cliente: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String nif = scanner.next();

        boolean bool = gestor.emitirFactura();
        if( bool )
            System.out.printf("Se ha emitido la factura del cliente %s satisfactoriamente", nif);
        else
            System.out.println("No se ha podido emitir la factura");
    }

    public static void obtenerFactura() {
        System.out.print("Introduce el código de la factura: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int codigo = scanner.nextInt();

        Factura factura = gestor.obtenerFactura(codigo);
        System.out.printf("Datos de la factura con el código %010d: \n", codigo);
        System.out.printf("Fecha Emisión: %s \n" +
                "Periodo de Facturación: %s \n" +
                "Importe: %f euros \n" +
                "Nombre Completo del cliente: %s \n" +
                "NIF del cliente: %s",
                factura.getFecha(),
                factura.getPeriodoDeFacturacion().getPeriodo(),
                factura.getImporte(),
                factura.getCliente().getNombreCompleto(),
                factura.getCliente().getNIF()
                );
    }

    public static void listarFacturasCliente(){
        System.out.print("Introduce el NIF del cliente: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String nif = scanner.next();

        ArrayList<Factura> facturas = gestor.listarFacturasCliente(nif);
        int i =1;
        System.out.printf("Listado de las facturas del cliente %s", nif);
        for(Factura factura : facturas){
            System.out.printf("%-3d.- id Factura: %d\n" +
                    "Fecha Emision: %s\n" +
                    "Periodo de Facturación: %s \n" +
                    "Importe: %f euros \n" +
                    "Nombre Completo del cliente: %s \n" +
                    "NIF del cliente: %s",
                    i,
                    factura.getIdFactura(),
                    factura.getFecha(),
                    factura.getPeriodoDeFacturacion().getPeriodo(),
                    factura.getImporte(),
                    factura.getCliente().getNombreCompleto(),
                    factura.getCliente().getNIF() );
        }
    }

}
