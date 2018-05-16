package controlador;

import modelo.Modelo;
import vista.Vista;

/**
 * Created by al361930 on 08/05/18.
 */
public class Controlador {

    private Vista vista;
    private Modelo modelo;

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void setModelo(Modelo modelo) { this.modelo = modelo;}

    public void quierenDarDeAltaUnCliente() {
        String nombre = vista.dameNombre();
        //Srting

    }
}
