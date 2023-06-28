package org.example;
import clases.user;
import vista.*;
import controllers.*;

import javax.swing.*;


public class QuickStart  {

    public static void main(String[] args) {
        controllerCompras mm=new controllerCompras();
        user pepe= new user("ff","fsf",324);
        user pepe2= new user("ff","fsf",324);
        System.out.println((pepe +" "+ pepe2));
        controllerCompras.getUsuario().add(pepe);
        controllerCompras.getUsuario().add(pepe2);
        FrmLogin win= new FrmLogin();
        win.setVisible(true);
    }

}