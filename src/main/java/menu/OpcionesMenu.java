package menu;

/**
 * Created by al361930 on 27/02/18.
 *
 * - Preferimos que el 'enumerador' empiece por 1 por lo que hemos hecho las modificaciones
 *   pertinentes al repecto.
 *
 * - TODO: Nos gustaría saber si hay alguna manera eficaz de mostrar en el menú algunas
 *   secciones como un apartado para "clientes", otro para "llamadas" y otro para "facturas".
 */
public enum OpcionesMenu implements Descripcion, Apartado {
    /* CLIENTES */
    ALTA_NUEVO_CLIENTE("Nuevo cliente.", 1),
    BAJA_CLIENTE("Baja Cliente.", 1),
    MODIFICAR_TARIFA("Modificar tarifa cliente.", 1),
    BUSCAR_CLIENTE("Buscar Cliente por NIF", 1),
    LISTAR_CLIENTES("Lista de Clientes", 1),
    /* LLAMADAS */
    ALTA_LLAMADA("Nueva llamada.", 2),
    LISTAR_LLAMADAS("Lista de llamadas de Cliente.", 2),
    /* FACTURAS */
    EMITIR_FATURA("Emisión Nueva Factura.", 3),
    OBTENER_FACTURA("Mostrar Factura por Código.", 3),
    LISTAR_FACTURAS_CLIENTE("Lista Facturas Cliente.", 3),
    /* SALIR */
    SALIR("Salir del programa.", 4);


    private String descripcion;
    private int apartado;

    OpcionesMenu(String descripcion, int apartado){
        this.descripcion = descripcion;
        this.apartado = apartado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getApartado() {
        return apartado;
    }

    public static OpcionesMenu getOpcion(int posicion) {
        return values()[posicion-1];
    }

    public static String getMenu() {
        String s = "";
        int seccion = 0;
        final String apartados[] = {"", "Clientes", "Llamadas", "Facturas", "Salir"};

        for (OpcionesMenu opcion : OpcionesMenu.values()) {
            if (seccion < opcion.getApartado())
                s += String.format("== %s ==%n", apartados[++seccion]);
            s += String.format(
                "%5d.- %s%n",
                opcion.ordinal()+1,
                opcion.getDescripcion()
            );
        }
        return s;
    }
}
