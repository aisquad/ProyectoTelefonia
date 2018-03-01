package main;

import interfaces.Descripcion;

/**
 * Created by al361930 on 27/02/18.
 */
public enum OpcionesMenu implements Descripcion {
    ALTA_NUEVO_CLIENTE("Nuevo cliente."),
    BAJA_CLIENTE("Baja Cliente."),
    MODIFICAR_TARIFA("Modificar tarifa cliente."),
    BUSCAR_CLIENTE("Buscar Cliente por NIF"),
    LISTAR_CLIENTES("Lista de Clientes"),
    ALTA_LLAMADA("Nueva llamada."),
    LISTAR_LLAMADAS("Lista de llamadas de Cliente."),
    EMITIR_FATURA("Emisión Nueva Factura."),
    OBTENER_FACTURA("Mostrar Factura por Código."),
    LISTAR_FACTURAS_CLIENTE("Lista Facturas Cliente.");


    private String descripcion;

    OpcionesMenu(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static OpcionesMenu getOpcion(int posicion) {
        return values()[posicion-1];
    }

    public static String getMenu() {
        String s = "";
        for (OpcionesMenu opcion : OpcionesMenu.values()) {
            s += String.format(
                    "%d.- %s%n",
                    opcion.ordinal()+1,
                    opcion.getDescripcion()
            );
        }
        return s;
    }
}
