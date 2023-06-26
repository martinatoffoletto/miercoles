package vista;

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


    public Principal (String titulo){
        super(titulo);
        //Pantalla
        this.setContentPane(pnlPrincipal);
        this.setSize(600,600);
        //this.setModal(true);

        //Establezco cierre
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Centrada
        this.setLocationRelativeTo(null);
        this.self=this;

        pedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pedido frame = new Pedido (self);
                frame.setVisible(true);

            }
        });
    }

    public static void main(String[] args) {
        //Inicializo
        Principal frame= new Principal( " Página Principal ");
        frame.setVisible(true);

    }
}
