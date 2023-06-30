package vista;

import clases.*;
import controllers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class Catalogo extends JDialog {

    private JPanel pnlPrincipal;
    private JComboBox productoComboBox;
    private JLabel lblProducto;
    private JLabel lblComentarios;
    private JLabel lblPrecio;

    //Lista Productos
    private List<producto> productos = controllerCompras.getProds();

    public  Catalogo(Window owner){
        super(owner," Catalogo ");

        //Constructor
        this.setContentPane(pnlPrincipal);
        this.setModal(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Establezco modelo
        DefaultComboBoxModel modelo= new DefaultComboBoxModel();
        modelo.addAll(productos);

        //Obtiene Informacion del Producto

        productoComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                lblProducto.setText(String.valueOf(productoComboBox.getSelectedIndex()));
                producto aux= (producto) productoComboBox.getSelectedItem();
                lblComentarios.setText(String.valueOf(aux.getComents()));
                lblPrecio.setText(String.valueOf(aux.getComents()));
            }

        });
    }




}
