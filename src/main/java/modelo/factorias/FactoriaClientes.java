package modelo.factorias;

import modelo.clientes.Empresa;
import modelo.clientes.Particular;
import modelo.facturacion.Tarifa;
import modelo.poblaciones.Poblacion;

public class FactoriaClientes implements IntClientes{
    @Override
    public Particular getParticular() {
        return new Particular();
    }

    @Override
    public Particular getParticular(
            Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico, String apellido
    ) {
        return new Particular(tarifa, nombre, nif, poblacion, correoElectronico, apellido);
    }

    @Override
    public Empresa getEmpresa() {
        return new Empresa();
    }

    @Override
    public Empresa getEmpresa(Tarifa tarifa, String nombre, String nif, Poblacion poblacion, String correoElectronico) {
        return new Empresa(tarifa, nombre, nif, poblacion, correoElectronico);
    }
}
