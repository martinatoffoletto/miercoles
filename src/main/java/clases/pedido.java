package clases;

import clases.carrito;

import java.util.Date;

public class pedido {

    public int getNroPedido() {
        return nroPedido;
    }

    private int nroPedido;
    private Date fechaPedido;

    private carrito contenido;
    ;

    private int preciofinal;

    public int getPreciofinal() {
        preciofinal= contenido.getPrecio();
        return preciofinal;
    }

    public int getCod() {
        return nroPedido;
    }

    public pedido(carrito contenido) {
        this.contenido = contenido;
        preciofinal=contenido.getPrecio();
    }

    public void setFecha(Date fechaped) {
        this.fechaPedido= fechaped;
    }

    public void setnroPedido(int numped) {
        this.nroPedido= numped;
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
