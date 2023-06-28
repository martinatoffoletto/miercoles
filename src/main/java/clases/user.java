package clases;

import java.sql.Time;
import java.util.ArrayList;

public class user {
    private String nombre;
    private String direccion;
    private int dni;
    private Time actividaDiaria;
    private String categoria;

    public user(String nombre, String direccion, int dni) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.dni = dni;
    }



    public String getDireccion() {
        return direccion;
    }

    public Time getActividaDiaria() {
        return actividaDiaria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setActividaDiaria(Time actividaDiaria) {
        this.actividaDiaria = actividaDiaria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return
                "Nombre: " + nombre  + ",   DNI: " + dni;
    }

    public ArrayList<String> Datos(){
        ArrayList<String> datos= new ArrayList<>();
        datos.add(0,"Nombre: "+ nombre);
        datos.add(1,"DNI: "+ dni);
        datos.add(2,"Direccion: "+ direccion);
        datos.add(2,"Categoria: "+ categoria);

        return datos;

    }
}
