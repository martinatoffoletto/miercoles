package vista;

import javax.swing.*;
import clases.*;
import controllers.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends JDialog{
    private JButton relizarPedidoButton;
    private JPanel pnlPrincipal;
    private JButton agregarButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton eliminarButton;

    private Pedido self;

    private List<producto> productos = new ArrayList<producto>();

    public Pedido(Window owner){
        super(owner, "Pedidos");
        this.setSize(400, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnlPrincipal);

        //asigna datos
        ArrayList<producto> Productos = controllerCompras.getProds();

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addAll(Productos);
        comboBox1.setModel(modelo);


    }





}
