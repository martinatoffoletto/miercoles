package clases;

import java.sql.Time;

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
}
