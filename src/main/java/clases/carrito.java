package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class carrito {
    public int getCodCarrito() {
        return codCarrito;
    }

    private int codCarrito;
    private ArrayList<producto> carro;

    private user usuario;

    private int precio;

    public user getUsuario() {
        return usuario;
    }
    public int getPrecio() {
        return precio;
    }

    public carrito(user usuario) {
        this.usuario=usuario;
        ArrayList<producto> carro= new ArrayList<producto>();
        this.carro= carro;
        this.precio=0;
        Random random = new Random();
        this.codCarrito= random.nextInt(1000);
    }

    public int getNro(){
        return codCarrito;
    }


    public void agregarProd(producto prod){
            carro.add(prod);
            precio+= prod.getPrecio();
    }
    public void borrarProd(producto prod){
        carro.remove(prod);
        precio-= prod.getPrecio();
    }

    public ArrayList<producto> getProductos() {
        return carro;
    }


    @Override
    public String toString() {
        return
                "Codigo de carrito" + codCarrito + '\'';
    }
}
