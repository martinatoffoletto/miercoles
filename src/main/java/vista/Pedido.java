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
    private JButton relizarPedidoButton;
    private JPanel pnlPrincipal;
    private JButton agregarButton;
    private JComboBox elijeProducto;
    private JButton eliminarButton;
    private JComboBox elijeCant;


    private List<producto> productos = new ArrayList<producto>();

    public Pedido(Window owner){
        super(owner, "Pedidos");
        this.setSize(400, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnlPrincipal);



        //carga productos
        ArrayList<producto> Productos = controllerCompras.getProds();

        //elije Producto y cantidad
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addAll(Productos);
        elijeProducto.setModel(modelo);
        elijeCant.setModel(modelo);

        ArrayList<producto> pedido = new ArrayList<producto>();
        //permite agregar un producto
        agregarButton.addActionListener(new ActionListener() {
            int cant_producto;
            String productoElegido;
            producto ppelegido;
            @Override
            public void actionPerformed(ActionEvent e) {
                productoElegido= elijeProducto.getSelectedItem().toString();
                //pedido.add();
            }
        });

        //permiteEliminar producto

        eliminarButton.addActionListener(new ActionListener() {
            String productoEliminado;
            @Override
            public void actionPerformed(ActionEvent e) {
                productoEliminado= elijeProducto.getSelectedItem().toString();
                //pedido.remove()

            }
        });

        //Finaliza pedido
        relizarPedidoButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    //cerrar ventana
                    JOptionPane.showMessageDialog(null, "Pedido Realizado");
                }
        });


    }





}
