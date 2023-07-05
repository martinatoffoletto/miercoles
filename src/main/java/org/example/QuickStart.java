package org.example;
import clases.*;
import vista.*;
import controllers.*;

import javax.swing.*;
import java.text.ParseException;


public class QuickStart  {

    public static void main(String[] args) throws ParseException {
        controllerCompras mm=new controllerCompras();
        InicioSesion win= new InicioSesion();
        win.setVisible(true);
    }

}