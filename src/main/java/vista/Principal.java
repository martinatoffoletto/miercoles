package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import clases.user;
import controllers.controllerCompras;

public class Principal extends JFrame{
    private JButton carritoButton;
    private JList list1;
    private JList list2;
    private JList list3;
    private JPanel pnlPrincipal;
    private JButton verCatalogoButton;

    private Principal self;

    public Principal (user usuario){
        //genera
        super("Principal");
        //Pantalla
        this.setContentPane(pnlPrincipal);
        this.setSize(600,600);
        //this.setModal(true);

        //Establezco cierre
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        asignarDatosUser(usuario);
        //asignarDatosFacturas(usuario);
        //asignarDatosPedidos(usuario);

        carritoButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Carro menuPrincipal = new Carro(self, usuario);
                menuPrincipal.setVisible(true);
                setVisible(false);
            }
        });

        verCatalogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Catalogo catalogo = new Catalogo(self);
                catalogo.setVisible(true);
                setVisible(false);
            }
        });

    }



    //Muestra datos del usuario
    public void asignarDatosUser(user user) {

        DefaultListModel model = new DefaultListModel();
        model.addAll(user.Datos());
        list1.setModel(model);
    }

    public void asignarDatosFacturas(user user) {
        DefaultListModel model = new DefaultListModel();
        model.addAll(controllerCompras.getFacturas(user));
        list2.setModel(model);
    }

    public void asignarDatosPedidos(user user) {
        DefaultListModel model = new DefaultListModel();
        model.addAll(controllerCompras.getPedidos(user));
        list3.setModel(model);
    }

    /*public static void main(String[] args) {
        //Inicializo
        user usuar=new user("paula","roosvelt1234",1234);
        Principal frame= new Principal( usuar);
        frame.setVisible(true);

    }*/





}




