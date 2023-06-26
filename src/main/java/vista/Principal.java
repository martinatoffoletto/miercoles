package vista;

import clases.*;
import controllers.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame{
    private JButton carritoButton;
    private JList list1;
    private JList list2;
    private JList list3;
    private JButton pedidoButton;
    private JPanel pnlPrincipal;

    private Principal self;


    public Principal (user usuario){
        //genera
        super(" Menu Principal ");
        //Pantalla
        this.setContentPane(pnlPrincipal);
        this.setSize(600,600);
        //this.setModal(true);

        //Establezco cierre
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Muestra datos del usuario
        //

        //Accede a pedido

        pedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pedido frame = new Pedido (self);
                frame.setVisible(true);

            }
        });
    }

}
