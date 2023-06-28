package clases;

import clases.carrito;

import java.util.Date;

public class pedido {

    private int nroPedido;
    private Date fechaPedido;

    private carrito contenido;
    ;

    private int preciofinal;

    public int getPreciofinal() {
        preciofinal= contenido.getPrecio();
        return preciofinal;
    }
    public pedido(carrito contenido) {
        this.contenido = contenido;
        preciofinal=0;
    }

    public carrito getContenido() {
        return contenido;
    }


    @Override
    public String toString() {
        return "nroPedido=" + nroPedido +
                ", fechaPedido=" + fechaPedido;
    }
}
