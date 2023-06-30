package clases;

import java.util.ArrayList;
import java.util.List;

public class carrito {
    private ArrayList<producto> carro;

    public user getUsuario() {
        return usuario;
    }

    private user usuario;



    private int precio;
    public int getPrecio() {
        return precio;
    }

    public carrito(user usuario) {
        this.usuario=usuario;
        ArrayList<producto> carro= new ArrayList<producto>();
        precio=0;
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



}
