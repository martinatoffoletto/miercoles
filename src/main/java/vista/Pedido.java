package vista;

import javax.swing.*;
import clases.*;
import controllers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends JDialog{
    private JButton comprarButton;
    private JPanel pnlPrincipal;
    private JList listaProductos;
    private JComboBox pagoComboBox2;
    private JLabel lblPrecioFinal;


    private List<producto> productos = new ArrayList<producto>();

    public Pedido(Window owner,user Usuario, carrito compra){
        super(owner, "Pedidos");
        this.setSize(400, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Elije Medio de Pago



        //Muestra Precio


        //Finaliza pedido
        comprarButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    //cerrar ventana
                    JOptionPane.showMessageDialog(null, "Pedido Realizado");
                    setVisible(false);
                }
        });


    }
    //Muestra Productos
    public void asignarDatosUser(carrito compra) {

        DefaultListModel model = new DefaultListModel();
        model.addAll(compra.getProductos());
        listaProductos.setModel(model);
    }

}
