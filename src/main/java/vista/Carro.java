package vista;

import javax.swing.*;
import clases.*;
import controllers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Carro extends JDialog{
    private JButton relizarPedidoButton;
    private JPanel pnlPrincipal;
    private JButton agregarButton;
    private JComboBox elijeProducto;
    private JButton eliminarButton;
    private JComboBox elijeCant;
    private JComboBox ElegirCarro;
    private JButton guardarCarritoButton;
    private Carro self;


    public Carro(Window owner, user user){
        super(owner, "Carrito");
        this.setSize(400, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnlPrincipal);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Asocia Productos
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addAll(controllerCompras.getProds());

        //Elije carrito nuevo o viejo:
        DefaultComboBoxModel carritoelegir = new DefaultComboBoxModel();
        //Lista Carritos
        ArrayList<carrito> carrosPers=controllerCompras.getCarritos(user);
        carrito nuevo=controllerCompras.CrearCarrito(user);
        carrosPers.add(nuevo);
        carritoelegir.addAll(carrosPers);
        ElegirCarro.setModel(carritoelegir);

        carrito carroElegido=(carrito) carritoelegir.getSelectedItem();
        carrito finalCarroElegido = carroElegido;




        //elije Producto y cantidad
        DefaultComboBoxModel modelo1 = new DefaultComboBoxModel();
        DefaultComboBoxModel modelo2 = new DefaultComboBoxModel();
        modelo1.addAll(controllerCompras.getProds());
        elijeProducto.setModel(modelo1);
        ArrayList<Integer> cant= new ArrayList<>();
        cant.add(1);
        cant.add(2);
        cant.add(3);
        modelo2.addAll(cant);
        elijeCant.setModel(modelo2);

        //permite agregar un producto


        guardarCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerCompras.GuardarCarro(finalCarroElegido);

            }
        });





        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cant= (int) elijeCant.getSelectedItem();
                while (cant!=0) {
                    finalCarroElegido.agregarProd((producto) elijeProducto.getSelectedItem());
                    cant--;
                }
            }
        });

        //permite eliminar producto

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cant= (int) elijeCant.getSelectedItem();
                while (cant!=0) {
                finalCarroElegido.borrarProd((producto) elijeProducto.getSelectedItem());
                    cant--;
                }
            }
        });

        //Finaliza pedido
        relizarPedidoButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                pedido pedidoG= controllerCompras.ConvertirPedido(finalCarroElegido);
                Pedido Pedidos = new Pedido(self, pedidoG);
                Pedidos.setVisible(true);
                setVisible(false);
                }
        });
    }
}
