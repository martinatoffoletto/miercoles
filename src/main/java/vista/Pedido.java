package vista;

import javax.swing.*;
import clases.*;
import controllers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends JFrame{
    private JButton relizarPedidoButton;
    private JPanel pnlPrincipal;
    private JButton agregarButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton eliminarButton;

    private Pedido self;

    private List<producto> productos = new ArrayList<producto>();

    public Pedido(){
        super("Pedidos");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setContentPane(pnlPrincipal);
        asignarDatosCombo();

        //


    }

    private void asignarDatosCombo() {
        ArrayList<producto> Productos = controllerCompras.getProds();


        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addAll(Productos);
        comboBox1.setModel(modelo);
    }

}
