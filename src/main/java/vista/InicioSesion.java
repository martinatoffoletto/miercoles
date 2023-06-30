package vista;
import clases.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controllers.*;

public class InicioSesion extends JFrame{
    private JPanel pnlPrincipal;
    private JComboBox comboBox1;
    private JButton ingresarButton;
    private JButton salirButton;

    public InicioSesion() {
        super ("Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setContentPane(pnlPrincipal);
        asignarDatosCombo();

        //user usuar=new user("paula","roosvelt1234",1234);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal menuPrincipal = new Principal((user) comboBox1.getSelectedItem());
                //Principal menuPrincipal = new Principal(usuar);
                menuPrincipal.setVisible(true);

            }
        });
        salirButton.addActionListener(new ActionListener() {
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
