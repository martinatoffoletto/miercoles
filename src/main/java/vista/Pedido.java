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

    public Pedido(Window owner, pedido pedido){
        super(owner, "Pedidos");
        this.setSize(600, 600);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Muestra productos
        asignarDatosUser(pedido);

        //Muestra Precio
        lblPrecioFinal.setText(String.valueOf(pedido.getContenido().getPrecio()));

        //Finaliza pedido
        comprarButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                     //Elije Medio de Pago
                     String pagoMedio= pagoComboBox2.getSelectedItem().toString();

                    //Crea Factura
                    factura FacturaFianal= controllerCompras.ConvertirFactura(pedido,pagoMedio);

                    //cerrar ventana
                    JOptionPane.showMessageDialog(null, "Pedido Realizado");
                    setVisible(false);
                }
        });


    }
    //Asigna Productos
    public void asignarDatosUser(pedido pedido) {

        DefaultListModel model = new DefaultListModel();
        model.addAll(pedido.getContenido().getProductos());
        listaProductos.setModel(model);
    }

}
