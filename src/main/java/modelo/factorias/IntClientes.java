package modelo.factorias;

import modelo.clientes.Empresa;
import modelo.clientes.Particular;
import modelo.facturacion.Tarifa;
import modelo.poblaciones.Poblacion;

public interface IntClientes {
    Particular getParticular();
    Particular getParticular(
            Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico, String apellido
    );

    Empresa getEmpresa();
    Empresa getEmpresa(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico);
}
