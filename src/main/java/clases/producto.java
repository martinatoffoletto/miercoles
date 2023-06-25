package clases;

import java.io.File;
import java.util.*;

public class producto {
    private String nombre;
    private String descripcion;
    private ArrayList<File> fotos;
    private ArrayList<File> videos;
    private ArrayList<String> coments;

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    private int precio;

    public producto(String nombre, String descripcion, ArrayList<File> fotos, ArrayList<File> videos, ArrayList<String> coments, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.videos = videos;
        this.coments = coments;
        this.precio = precio;
    }


    public String getNombre() {
        return nombre;
    }



    public String getDescripcion() {
        return descripcion;
    }


    public ArrayList<File> getFotos() {
        return fotos;
    }



    public ArrayList<File> getVideos() {
        return videos;
    }



    public ArrayList<String> getComents() {
        return coments;
    }


}

