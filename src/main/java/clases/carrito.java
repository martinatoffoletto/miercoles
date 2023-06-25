package clases;

import java.util.ArrayList;
import java.util.List;

public class carrito {
    private ArrayList<producto> carro;



    private int precio;
    public int getPrecio() {
        return precio;
    }

    public carrito() {
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



}
