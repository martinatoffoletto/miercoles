package org.example;
import clases.*;
import vista.*;
import controllers.*;

import javax.swing.*;


public class QuickStart  {

    public static void main(String[] args) {
        controllerCompras mm=new controllerCompras();
        user pepe= new user("Juan Carlos Gardel","Roosevelt 3231",324);
        user pepe2= new user("Halsey Fraga","Cramer 444",324);
        carrito car=new carrito(pepe);
        pedido ped=new pedido(car);
        factura fact= new factura( "MP" ,ped);
        //System.out.println((pepe +" "+ pepe2));
        controllerCompras.getUsuario().add(pepe);
        controllerCompras.getUsuario().add(pepe2);
        InicioSesion win= new InicioSesion();
        win.setVisible(true);
    }

}