package clases;

import clases.carrito;

public class pedido {
    public carrito getContenido() {
        return contenido;
    }

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


}
