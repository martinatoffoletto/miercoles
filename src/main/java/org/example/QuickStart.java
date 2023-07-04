package org.example;
import clases.*;
import vista.*;
import controllers.*;

import javax.swing.*;


public class QuickStart  {

    public static void main(String[] args) {
        controllerCompras mm=new controllerCompras();
        user pepe= new user("Juan Carlos Gardel","Roosevelt 3231",324,240, "TOP");
        user pepe2= new user("Halsey Fraga","Cramer 444",324,240, "TOP");
        carrito car=new carrito(pepe);
        System.out.println(car);
        pedido ped=new pedido(car);
        //factura fact= new factura( "MP" ,ped);
        //System.out.println((pepe +" "+ pepe2));
        controllerCompras.getUsuario().add(pepe);
        controllerCompras.getUsuario().add(pepe2);
        InicioSesion win= new InicioSesion();
        win.setVisible(true);
    }

}