package controllers;

import clases.carrito;
import clases.factura;
import clases.pedido;
import clases.user;

public class controllerCompras {


    public pedido ConvertirPedido(carrito car, user user){
        pedido ped= new pedido(car,user);
        return  ped;

    }

    public factura ConvertirFactura(pedido pedido, String pago){
        factura fac= new factura(pago,pedido );
        return  fac;
    }





}
