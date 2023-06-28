package vista;

import clases.user;
import controllers.controllerCompras;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmLogin extends JFrame{
    private JPanel pnlPrincipal;
    private JComboBox comboBox1;
    private JButton ingresarButton;
    private JButton cerrarButton;

    public FrmLogin() {
        super("Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setContentPane(pnlPrincipal);
        asignarDatosCombo();

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               Principal menuPrincipal = new Principal((user) comboBox1.getSelectedItem());
               menuPrincipal.setVisible(true);
                setVisible(false);
            }
        });
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void asignarDatosCombo() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addAll(controllerCompras.getUsuario());
        comboBox1.setModel(modelo);
    }
}
