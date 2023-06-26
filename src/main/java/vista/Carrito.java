package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Carrito extends JFrame{

    private JPanel pnlCarrito;
    private JButton comprarButton;
    private JComboBox pagoComboBox2;
    private JList listaProductos;
    private JLabel lblPrecioFinal;
    private JPanel pnlPrincipal;

    public Carrito ( ) {
        super(" Carrito");
        //Pantalla
        this.setContentPane(pnlPrincipal);
        this.setSize(600, 600);
        //this.setModal(true);

        //Establezco cierre
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Compra Realizada");
            }
        });
    }



}
