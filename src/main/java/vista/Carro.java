package vista;

import javax.swing.*;
import clases.*;
import controllers.*;

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
    private JPanel pnlPedido;
    private JComboBox ElegirCarro;
    private JButton guardarCarritoButton;


    private List<producto> productos = new ArrayList<producto>();

    public Carro(user user){
        //super(user.getNombre()+" Carrito");
        this.setSize(400, 200);
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnlPrincipal);



        //Elije carrito nuevo o viejo:
        DefaultComboBoxModel carritoelegir = new DefaultComboBoxModel();
        ArrayList<carrito> carrosPers=controllerCompras.getCarritos(user);
        carrito nuevo=controllerCompras.CrearCarrito(user);
        carrosPers.add(nuevo);
        carritoelegir.addAll(carrosPers);
        ElegirCarro.setModel(carritoelegir);
        carrito carroElegido= controllerCompras.CrearCarrito(user);
        carroElegido=(carrito) carritoelegir.getSelectedItem();
        carrito finalCarroElegido = carroElegido;


        guardarCarritoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerCompras.GuardarCarro(finalCarroElegido);

            }
        });


        //elije Producto y cantidad
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        DefaultComboBoxModel modelo2 = new DefaultComboBoxModel();
        modelo.addAll(controllerCompras.getProds());
        elijeProducto.setModel(modelo);
        elijeCant.setModel(modelo2);
        //int cant= (int) elijeCant.getSelectedItem();


        //permite agregar un producto
        agregarButton.addActionListener(new ActionListener() {
            //AGREGAR WHILE X CANTIDAD
            @Override
            public void actionPerformed(ActionEvent e) {
                finalCarroElegido.agregarProd((producto) elijeProducto.getSelectedItem());

            }
        });

        //permiteEliminar producto

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //AGREGAR WHILE X CANTIDAD
                finalCarroElegido.borrarProd((producto) elijeProducto.getSelectedItem());


            }
        });

        //Finaliza pedido
        relizarPedidoButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                Pedido Pedidos = new Pedido(user);
                Pedidos.setVisible(true);
                setVisible(false);

                }
        });


    }







}
