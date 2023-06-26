package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import clases.*;
import controllers.*;

public class IniciarSeccion {
    private JButton ingresarButton;
    private JComboBox comboBox1;
    private JPanel pnlSesion;


    public class FrmLogin extends JFrame{
        private JPanel pnlPrincipal;
        private JComboBox comboBox1;
        private JButton ingresarButton;
        private JButton cerrarButton;

        public FrmLogin() {
            //Constructor
            super("Login");
            this.setSize(400, 200);
            this.setLocationRelativeTo(null);
            this.setContentPane(pnlPrincipal);
            this.asignarDatosCombo();

            //permite ingresar



            ingresarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //genera menu principal

                    Principal menuPrincipal = new Principal((Usuario) comboBox1.getSelectedItem());
                    menuPrincipal.setVisible(true);
                    setVisible(false);
                }
            });
        }
        private void asignarDatosCombo() {
            //agrega usuarios
            ArrayList<user> listaUsuarios = controllerCompras.getUsuario();


            DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            modelo.addAll(listaUsuarios);
            comboBox1.setModel(modelo);
        }
    }

}
