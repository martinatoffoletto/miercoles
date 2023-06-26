package vista;

import javax.swing.*;
import java.util.ArrayList;
import controllers.*;
import clases.*;

public class FrmPrincipal {
    private JTextArea textArea1;
    private JComboBox comboBox1;




    private void asignarDatosCombo() {

        ArrayList<user> listaUsers= controllerCompras.getUsuario();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addAll(listaUsers);
        comboBox1.setModel(modelo);
    }



}
