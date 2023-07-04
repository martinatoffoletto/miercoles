package clases;

import java.io.File;
import java.util.*;

public class producto {
    private String nombre;
    private String descripcion;
    private String fotos;
    private ArrayList<String> coments;

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    private int precio;

    public producto(String nombre, String descripcion, String fotos, ArrayList<String> coments, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.coments = coments;
        this.precio = precio;
    }


    public String getNombre() {
        return nombre;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public String getFotos() {
        return fotos;
    }
    

    public ArrayList<String> getComents() {
        return coments;
    }


    @Override
    public String toString() {
        return "producto{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

