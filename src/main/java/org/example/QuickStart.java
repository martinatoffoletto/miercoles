package org.example;
import clases.*;
import vista.*;
import controllers.*;

import javax.swing.*;


public class QuickStart  {

    public static void main(String[] args) {
        controllerCompras mm=new controllerCompras();
        InicioSesion win= new InicioSesion();
        win.setVisible(true);
    }

}