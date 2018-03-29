package principal;

import clientes.Cliente;
import clientes.Empresa;
import clientes.Particular;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import facturacion.Factura;
import facturacion.Llamada;
import facturacion.PeriodoFacturacion;
import facturacion.Tarifa;
import generadores.GeneradorDatos;
import generadores.GeneradorEmpresas;
import generadores.GeneradorParticulares;
import generadores.GeneradorPoblacion;
import menu.OpcionesMenu;
import poblaciones.Poblacion;
import tiempo.SegundosATexto;
import tiempo.FormateadorFecha;

/**
 * Created by al361930 on 20/02/18.
 */
public class Consola extends FormateadorFecha {
    private final String RUTA = "src/main/resources/data/";
    private Gestor gestor = new Gestor();
    private Random random = new Random();

    public static void main(String args[]) {
        new Consola().ejecuta();
    }

    private void ejecuta() {
        Integer opcion = 0;
        OpcionesMenu opcionMenu;
        boolean salir = false;
        do {
            String menu = OpcionesMenu.getMenu();
            System.out.println(menu);
            Scanner scanner = new Scanner(System.in);
            String usrInput = "";
            /*
            Únicamente aquí nos aseguramos que el dato introducido
            es válido (es un entero dentro del rango del vector)
            En el resto del código asumimos que el usuario inserta
            valores que se ajustarán a las exigencias del script.
            */
            int veces = 1;
            String datos = "";
            do {
                System.out.print("Elige una opción: ");
                usrInput = scanner.next();
                if (!usrInput.equals("") && usrInput.matches("^\\d+$")) {
                    opcion = Integer.valueOf(usrInput);
                } else if (usrInput.contains(":")) {
                    String tokens[] = usrInput.split(":");
                    opcion = Integer.valueOf(tokens[0]);
                    veces = Integer.valueOf(tokens[1]);
                    datos = tokens.length > 2 ? tokens[2] : tokens.length > 1 ? tokens[1] : "";
                    if (opcion == 6 && veces > 0 && datos.equalsIgnoreCase("all")) {
                        datos = String.format("all:%d", veces);
                        veces = 1;
                    }
                }
            } while (opcion - 1 < 0 || opcion > OpcionesMenu.values().length);
            opcionMenu = OpcionesMenu.getOpcion(opcion);

            for (int i = 0; i < veces; i++) {
                switch (opcionMenu) {
                    case ALTA_NUEVO_CLIENTE:
                        altaCliente(datos);
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
                        insertarLlamada(datos);
                        break;
                    case LISTAR_LLAMADAS:
                        listarLlamadasCliente();
                        break;
                    case EMITIR_FATURA:
                        emitirFactura(datos);
                        break;
                    case OBTENER_FACTURA:
                        obtenerFactura();
                        break;
                    case LISTAR_FACTURAS_CLIENTE:
                        listarFacturasCliente();
                        break;
                    case CARGAR_DATOS:
                        cargarDatos();
                        break;
                    case GUARDAR_DATOS:
                        guardarDatos();
                        break;
                    case SALIR:
                        salir = true;
                        break;
                }

                if (!salir) {
                    //Si hemos introducido una sintaxis compleja, datos != "" y por lo tanto estamos
                    //en modo 'automático', solo hay que pedir pulsar intro al acabar el modo automático.
                    if (datos.equals("") || i == veces - 1)
                        pideSeguir();
                    opcion = 0;
                }
            }
        } while (!salir);
    }

    //Metodos para los clientes

    public void altaCliente(String datos) {
        /*
        Para generar varios clientes particulares:
            1:10:P creará 10 particulares
            1:5:E creará 5 empresas
         */
        String usrInput;
        boolean manual = datos.equals("");
        if (manual) {
            System.out.print("El nuevo cliente será (P)articular o (E)mpresa? ");
            Scanner scanner = new Scanner(System.in);
            usrInput = scanner.next();
            System.out.println();
        } else {
            usrInput = datos;
        }

        GeneradorPoblacion genPobl = new GeneradorPoblacion();
        Poblacion poblacion = genPobl.getPoblacion();
        GeneradorParticulares genPart = new GeneradorParticulares();
        /*
        nombre y apellido nos hacen falta para el mail tanto de
        particular como de empresa (en este último caso, por simplicidad).
        */
        String nombre = genPart.getNombre();
        String apellido = genPart.getApellido();
        Cliente cliente;
        if (usrInput.equals("P"))
            cliente = new Particular(
                    new Tarifa(.001d),
                    nombre,
                    genPart.getDNI(),
                    poblacion,
                    genPart.getEmail(nombre, apellido),
                    apellido
            );
        else {
            GeneradorEmpresas genEmp = new GeneradorEmpresas();
            cliente = new Empresa(
                    new Tarifa(.002d),
                    genEmp.getAleatorio(poblacion),
                    genEmp.getCIF(),
                    poblacion,
                    genEmp.getEmail(nombre, apellido)
            );
        }
        cliente = gestor.altaNuevoCliente(cliente);
        if (cliente != null)
            System.out.printf("El cliente se ha añadido satisfactoriamente.\nNuevo cliente: %s%n", cliente);
        else
            System.out.println("El cliente ya existía.");
    }

    public void bajaCliente() {
        String nif = pideNIF();
        Cliente cliente = gestor.bajaCliente(nif);
        if (cliente != null)
            System.out.println(
                    "El cliente se ha eliminado satisfactoriamente.\nCliente eliminado: " +
                            cliente.mostrarDatosAnterioresABaja()
            );
        else
            System.out.println("El cliente no existe.");
    }

    public void cambiarTarifa() {
        String nif = pideNIF();

        //Todo: Estaría permitido esto?
        // Double importe = Double.valueOf(pideDato("el importe de la nueva tarifa"));
        System.out.print("Introduce el importe de la nueva tarifa: ");
        Scanner scanner = new Scanner(System.in);
        Double importe = scanner.nextDouble();
        System.out.println();

        Tarifa tarifaAntigua = gestor.cambiarTarifa(nif, importe);
        if (tarifaAntigua != null)
            if (!tarifaAntigua.getTarifa().equals(importe))
                System.out.printf(
                        "Se ha cambiado la tarifa del cliente con el NIF %s de %.2f a %.2f",
                        nif,
                        tarifaAntigua.getTarifa(),
                        importe
                );
            else
                System.out.println("No se ha producido ningún cambio en la tarifa.");
        else
            System.out.println("El cliente no existe.");
    }

    public void buscarCliente() {
        String nif = pideNIF();

        Cliente cliente = gestor.buscarCliente(nif);
        if (cliente != null && cliente.estadoActivo())
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
                    cliente.getTarifa().getTarifaConFormato()
            );
        else
            System.out.println("No existe el cliente.");
    }

    public void listarClientes() {
        HashMap<String, Cliente> clientes = gestor.listarClientes();
        System.out.println("Listado de todos los clientes:\n");
        int i = 1;
        for (Cliente cliente : clientes.values()) {
            System.out.printf("%3d.- %s %s%n", i++, cliente.getNIF(), cliente.getNombreCompleto());
        }
    }

    //Metodos para las llamadas
    private String escogeNIF() {
        return gestor.escogeNIF();
    }

    public void insertarLlamada(String datos) {
        /*
        Modo automático:
            7:10:<DNI> -> genera 10 llamadas al DNI <DNI>.
            7:15:All -> genera de 1 a 15 llamadas a cada uno de los clientes activos.
         */
        Llamada llamada = null;
        String telefono;
        int duracion;
        if (datos.equals("")) {
            System.out.println("Se va a generar un nuevo registro para una llamada.");
            telefono = pideDato("el numero de teléfono");
            System.out.println("Introduzca la duración (en segundos -entre 1 y 7200-): ");
            Scanner resp = new Scanner(System.in);
            System.out.println();
            duracion = resp.nextInt();
            llamada = gestor.insertarLlamada(pideFecha(), pideNIF(), telefono, duracion);
        } else {
            String nif;
            GeneradorDatos genDatos = new GeneradorDatos();
            telefono = genDatos.getTelefono();
            duracion = genDatos.getDuracion();
            Date fecha = genDatos.getFecha();
            if (datos.matches("^\\d+$")) {
                //nos pasan el numero de iteraciones
                nif = escogeNIF();
                llamada = gestor.insertarLlamada(fecha, nif, telefono, duracion);
            } else if (datos.matches("^[\\w\\d]\\d{7}[\\w\\d]$")) {
                //nos pasan un nif
                nif = datos;
                llamada = gestor.insertarLlamada(fecha, nif, telefono, duracion);
            } else {
                //nos pasan un número de iteraciones para todos
                int veces, ttl = 0;
                Object clientes[] = gestor.getClientes();
                for (Object nif2 : clientes) {
                    veces = random.nextInt(Integer.valueOf(datos.split(":")[1]));
                    veces = veces < 3 ? 3 : veces;
                    for (int i = 0; i < veces; i++) {
                        llamada = gestor.insertarLlamada(
                                genDatos.getFecha(), (String) nif2, genDatos.getTelefono(), genDatos.getDuracion()
                        );
                        ttl++;
                    }
                }
                System.out.printf(
                        "Se han añadido %d llamadas entre %d clientes. Datos de última llamada añadida:%n",
                        ttl, clientes.length
                );
            }
        }

        if (llamada != null)
            System.out.printf("Nueva llamada registrada. Datos de la llamada: %s.%n", llamada);
        else
            System.out.println("No se ha podido registrar la llamada.");
    }

    public void listarLlamadasCliente() {
        String nif = pideNIF();
        ArrayList<Llamada> llamadas = gestor.listarLlamadasCliente(nif);
        System.out.printf("Relación de llamadas del cliente con NIF: %s%n", nif);
        int i = 1;
        if (llamadas == null)
            System.out.println("No hay llamadas para este cliente");
        else
            for (Llamada llamada : llamadas)
                System.out.printf(
                        "%3d.- Fecha: %s Tel.: %s Dur.: %s.%n",
                        i++,
                        formatoFecha.format(llamada.getFecha()),
                        llamada.getTelefono(),
                        new SegundosATexto().segundosATextoAbreviado(llamada.getDuracion())
                );
    }

    //Metodos para las facturas

    public void emitirFactura(String datos) {
        String nif = pideNIF();
        Factura fact;
        if (!datos.equals("")) {
            //String mes = pideDato("el mes del periodo de facturación");

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, Integer.parseInt(datos));
            fact = gestor.emitirFactura(nif, cal.getTime());
        } else {
            System.out.print(
                    "Para el periodo del mes anterior pusle [intro]." +
                            "Para especificar un periodo teclee 'P' y a continuación " +
                            "introduzca las dos fechas en las que se comprende el periodo.\n" +
                            "Para un intervalo entre el primer y útlimo día de un mes " +
                            "teclee 'I' y a continuación indique una única fecha." +
                            "Las fechas deben tener el formato 'dd/mm/aaaa'."
            );
            Scanner scanner = new Scanner(System.in);
            String usrInput = scanner.nextLine();
            if (usrInput.equals(""))
                fact = gestor.emitirFactura(nif, new Date());
            else {
                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    if (usrInput.matches("(3[10]|[12]?\\\\d+)/(1[0-2]|[1-9])/20(1[0-8]|0\\d)")) {
                        Date fecha = format.parse(usrInput);
                        fact = gestor.emitirFactura(nif, new Date());
                    } else {
                        Date fecha1, fecha2;
                        usrInput += usrInput.trim();
                        usrInput.replace("  +", " ");
                        String tokens[] = usrInput.split(" ");
                        fecha1 = format.parse(tokens[0]);
                        fecha2 = format.parse(tokens[1]);
                        PeriodoFacturacion periodoFacturacion = new PeriodoFacturacion(fecha1, fecha2);
                        fact = gestor.emitirFactura(nif, fecha1);
                        fact.setPeriodoDeFacturacion(periodoFacturacion);

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    fact = null;
                }
            }
        }

        if (fact != null)
            System.out.printf(
                    "Se ha emitido la factura del cliente %s satisfactoriamente%nDatos factura: %s", nif, fact
            );
        else
            System.out.println("No se ha podido emitir la factura");
    }

    public void obtenerFactura() {
        System.out.print("Introduce el código de la factura: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        int codigo = scanner.nextInt();

        Factura factura = gestor.obtenerFactura(codigo);
        System.out.printf("Datos de la factura con el código %05d:%n", codigo);
        mostrarFactura(factura, 1);
    }

    public void listarFacturasCliente() {
        String nif = pideNIF();
        ArrayList<Factura> facturas = gestor.listarFacturasCliente(nif);
        if (facturas == null) {
            System.out.printf("No se han hallado coincidencias para el NIF %s.%n", nif);
            return;
        }
        int i = 1;
        System.out.printf("Listado de las facturas del cliente %s%n", nif);
        for (Factura factura : facturas)
            mostrarFactura(factura, i++);
    }

    //Otros métodos
    private void pideSeguir() {
        System.out.print("\nPulsa [intro] para continuar");
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        scanner.nextLine();
    }

    private String pideDato(String frase) {
        System.out.printf("Introduce %s: ", frase);
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        return scanner.next();
    }

    private String pideNIF() {
        return pideDato("el NIF del cliente");
    }

    private Date pideFecha() {
        Date fecha = new Date();
        try {
            String usrInput = pideDato("una fecha para la llamada con el formato 'dd/mm/aaaa'");
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            fecha = format.parse(usrInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    private void mostrarFactura(Factura fact, int indice) {
        System.out.printf(
                "%3d.- Fecha Emisión: %s\n\t" +
                        "Periodo de Facturación: %s\n\t" +
                        "Nombre Completo del cliente: %s\n\t" +
                        "NIF del cliente: %s\n",
                indice,
                formatoFecha.format(fact.getFecha()),
                fact.getPeriodoDeFacturacion().getPeriodo(),
                fact.getCliente().getNombreCompleto(),
                fact.getCliente().getNIF()
        );
        for (Llamada llamada : fact.getLlamadas())
            System.out.println("\t" + llamada);
        System.out.printf("%72s  %s%n", " ", String.format("%09d", 0).replace("0", "-"));
        System.out.printf("%72s: %7.2f €%n", "Importe", fact.getImporte());
    }

    private void guardarDatos() {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(RUTA + "gestor.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gestor);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(RUTA + "gestor.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            gestor = (Gestor) ois.readObject();
            ois.close();
            gestor.getClientes();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}