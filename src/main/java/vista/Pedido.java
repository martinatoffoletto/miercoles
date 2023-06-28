package vista;

import javax.swing.*;
import clases.*;
import controllers.controllerCompras;

public class Pedido extends JFrame{

    private JPanel pnlCarrito;
    private JButton comprarButton;
    private JComboBox pagoComboBox2;
    private JList listaProductos;
    private JLabel lblPrecioFinal;
    private JPanel pnlPrincipal;

    public Pedido (user user) {
        super(" Pedido");
        //Pantalla
        this.setContentPane(pnlPrincipal);
        this.setSize(600, 600);
        //this.setModal(true);

        //Establezco cierre
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
/*
        comprarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Compra Realizada");
            }
        });

 */
    }





}
