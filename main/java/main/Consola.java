package main;

import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import facturacion.Factura;
import facturacion.Llamada;
import facturacion.Tarifa;
import generadores.GeneradorEmpresas;
import generadores.GeneradorParticulares;
import generadores.GeneradorPoblacion;
import poblaciones.Poblacion;

/**
 * Created by al361930 on 20/02/18.
 */
public class Consola {
    private static Gestor gestor = new Gestor();
    private static SimpleDateFormat formatoFecha = new SimpleDateFormat(
            "EEEE d 'de' MMMM 'de' YYYY",
            new Locale("es", "ES")
    );


    public static void main(String args[]) {
        Integer opcion = 0;
        Integer max = 1000;
        OpcionesMenu opcionMenu;
        boolean salir = false;
        do {
            String menu = OpcionesMenu.getMenu();
            System.out.println(menu);
            Scanner scanner = new Scanner(System.in);
            String usrinput = "";
            /*
            Únicamente aquí nos aseguramos que el dato introducido
            es válido (es un entero dentro del rango del vector)
            En el resto del código asumimos que el usuario inserta
            valores que se ajustarán a las exigencias del script.
            */
            do {
                System.out.print("Elige una opción: ");
                usrinput = scanner.next();
                if (!usrinput.equals("") && usrinput.matches("^\\d+$")) {
                    opcion = Integer.valueOf(usrinput);
                }
            } while (opcion - 1 < 0 || opcion > OpcionesMenu.values().length);
            opcionMenu = OpcionesMenu.getOpcion(opcion);
            System.out.printf("Has elegido la opción: %s%n", opcionMenu.getDescripcion());

            switch (opcionMenu) {
                case ALTA_NUEVO_CLIENTE:
                    altaCliente();
                    break;
                case BAJA_CLIENTE:
                    bajaCliente();
                    break;
                case MODIFICAR_TARIFA:
                    cambiarTarifa();
                    break;
                case BUSCAR_CLIENTE:
                    buscarCliente();
                    break;
                case LISTAR_CLIENTES:
                    listarClientes();
                    break;
                case ALTA_LLAMADA:
                    insertarLlamada();
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
                case SALIR:
                    salir = true;
                    break;
            }
            opcion = 0;
            pideSeguir();
        } while (!salir);
    }

    //Metodos para los clientes

    public static void altaCliente(){
        System.out.print("El nuevo cliente será (P)articular o (E)mpresa? ");
        Scanner scanner = new Scanner(System.in);
        String usrinput = scanner.next();
        System.out.println();

        GeneradorPoblacion genPobl = new GeneradorPoblacion();
        Poblacion poblacion = genPobl.getPoblacion();
        GeneradorParticulares genPart = new GeneradorParticulares();
        /*
        nombre y apellido nos hacen falta para el mail tanto de
        particular como de empresa (en este último caso, por simplicidad).
        */
        String nombre = genPart.getNombre();
        String apellido = genPart.getApellido();
        boolean bool;
        if(usrinput.equals("P")) {
            Particular particular = new Particular(
                new Tarifa(.1d),
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
                new Tarifa(.2d),
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
        String nif = pideNIF();
        boolean bool = gestor.bajaCliente(nif);
        if (bool) {
            System.out.println("El cliente se ha eliminado satisfactoriamente.");
        } else {
            System.out.println("El cliente no existía.");
        }
    }

    public static void cambiarTarifa() {
        String nif = pideNIF();

        //Todo: Estaría permitido esto?
        // Double importe = Double.valueOf(pideDato("el importe de la nueva tarifa"));
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
        String nif = pideNIF();

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
                formatoFecha.format(cliente.getFecha()),
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
            System.out.printf("%3d.- %s %s%n", i++, cliente.getNIF(), cliente.getNombreCompleto());
        }
    }

    //Metodos para las llamadas

    public static void insertarLlamada(){
        System.out.println(
            "Se va a generar un nuevo registro para una llamada.\n" +
            "Si desea introducir manualmente una fecha introduzca 'N' a continuación: "
        );
        Scanner resp = new Scanner(System.in);
        System.out.println();

        Random random = new Random();
        String tlf = String.format("9%d" ,random.nextInt(99999999));
        int duracion = random.nextInt(9999);

        boolean bool;
        if (resp.nextLine().equals("N"))
            bool = gestor.insertarLlamada(pideFecha(), pideNIF(), tlf, duracion);
        else {
            bool = gestor.insertarLlamada(pideNIF(), tlf, duracion);
        }
        if (bool) {
            System.out.println("Nueva llamada registrada.");
        } else {
            System.out.println("No se ha podido registrar la llamada.");
        }
    }

    public static void listarLlamadasCliente() {
        String nif = pideNIF();
        ArrayList<Llamada> llamadas = gestor.listarLlamadasCliente(nif);
        System.out.printf("Relación de llamadas del cliente con NIF: %s%n", nif);
        int i = 1;
        if(llamadas == null)
            System.out.println("No hay llamadas para este cliente");
        else {
            for(Llamada llamada : llamadas) {
                System.out.printf(
                    "%3d.- Fecha: %s Tel.: %s Dur.: %d%n",
                    i++,
                    formatoFecha.format(llamada.getFecha()),
                    llamada.getTelefono(),
                    llamada.getDuracion()
                );
            }
        }
    }

    //Metodos para las facturas

    public static void emitirFactura() {
        String nif = pideNIF();
        boolean bool = gestor.emitirFactura(nif);
        if (bool) {
            System.out.printf("Se ha emitido la factura del cliente %s satisfactoriamente", nif);
        } else {
            System.out.println("No se ha podido emitir la factura");
        }
    }

    public static void obtenerFactura() {
        System.out.print("Introduce el código de la factura: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int codigo = scanner.nextInt();

        Factura factura = gestor.obtenerFactura(codigo);
        System.out.printf("Datos de la factura con el código %010d:%n", codigo);
        mostrarFactura(factura, 1);
    }

    public static void listarFacturasCliente(){
        String nif = pideNIF();
        ArrayList<Factura> facturas = gestor.listarFacturasCliente(nif);
        int i =1;
        System.out.printf("Listado de las facturas del cliente %s%n", nif);
        for(Factura factura : facturas){
            mostrarFactura(factura, i++);
        }
    }


    private static void pideSeguir() {
        System.out.print("\nPulsa [intro] para continuar");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        scanner.nextLine();
    }

    private static String pideDato(String frase) {
        System.out.printf("Introduce %s: ", frase);
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        return scanner.next();
    }

    private static String pideNIF() {
        return pideDato("el NIF del cliente");
    }

    private static Date pideFecha() {
        Date fecha = new Date();
        try {
            String usrimput = pideDato("una fecha para la llamada con el formato 'dd/mm/aaaa'");
            DateFormat format = new SimpleDateFormat("dd/mm/aaaa");
            fecha = format.parse(usrimput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    private static void mostrarFactura(Factura fact, int indice) {
        System.out.printf(
                "%3d.- Fecha Emisión: %s\n\t" +
                "Periodo de Facturación: %s\n\t" +
                "Importe: %.2f €\n\t" +
                "Nombre Completo del cliente: %s\n\t" +
                "NIF del cliente: %s\n",
                indice,
                formatoFecha.format(fact.getFecha()),
                fact.getPeriodoDeFacturacion().getPeriodo(),
                fact.getImporte(),
                fact.getCliente().getNombreCompleto(),
                fact.getCliente().getNIF()
        );
    }
}
