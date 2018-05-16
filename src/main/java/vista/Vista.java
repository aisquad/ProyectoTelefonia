package vista;

import controlador.Controlador;
import modelo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by al361930 on 08/05/18.
 */
public class Vista {

    Controlador controlador;
    JFrame ventanaPrincipal;
    Modelo modelo;
    JRadioButton botonRadioParticular;
    JRadioButton botonRadioEmpresa;
    JButton altaCliente;
    JTextField jtfNombre;


    public Vista() {
        creaVentana();
    }

    private void creaVentana() {
        ActionListener escuchadoraBotones;
        ventanaPrincipal = new JFrame();
        Container contenedorVP = ventanaPrincipal.getContentPane();
        contenedorVP.setLayout(new FlowLayout());
        altaCliente = new JButton("Nuevo Cliente");
        altaCliente.addActionListener(escuchadoraBotones = new EscuchadoraBotones());
        contenedorVP.add(new JLabel("Nombre: "));
        jtfNombre = new JTextField(40);
        contenedorVP.add(jtfNombre);
        contenedorVP.add(altaCliente);


        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonRadioEmpresa);
        grupo.add(botonRadioParticular);
        ventanaPrincipal.pack();

        ventanaPrincipal.setVisible(true);

    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(Modelo modelo){ this.modelo = modelo; }



    public String dameNombre() {
        return jtfNombre.getText();
    }

    class EscuchadoraBotones implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.quierenDarDeAltaUnCliente();
        }
    }
}
