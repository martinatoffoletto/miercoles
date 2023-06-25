package clases;

import java.util.ArrayList;

public class catalogo {
    public ArrayList<producto> getCatalogo() {
        return catalogo;
    }

    private ArrayList<producto> catalogo;

    public catalogo() {
        ArrayList<producto> catalogo= new ArrayList<producto>();
    }
}
