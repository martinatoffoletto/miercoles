package clases;

import clases.carrito;

public class pedido {
    private carrito contenido;
    private user comprador;



    private int preciofinal;

    public int getPreciofinal() {
        preciofinal= contenido.getPrecio();
        return preciofinal;
    }
    public pedido(carrito contenido, user comprador) {
        this.contenido = contenido;
        this.comprador = comprador;
        preciofinal=0;
    }


}
