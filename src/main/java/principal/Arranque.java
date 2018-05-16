package principal;

import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

/**
 * Created by al361930 on 08/05/18.
 */
public class Arranque {
    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador();
        Modelo modelo = new Modelo();

        modelo.setVista(vista);
        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setControlador(controlador);
        vista.setModelo(modelo);

    }
}
