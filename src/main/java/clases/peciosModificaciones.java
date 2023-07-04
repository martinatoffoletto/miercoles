package clases;

import java.util.Date;
import java.util.Random;
public class peciosModificaciones {

    private int modificacion;
    private producto prodnum;

    private Date fechaMod;

    private int precio;

    public peciosModificaciones ( int precioaux ){
        Random rand=new Random();
        this.modificacion=rand.nextInt();
        this.fechaMod=new Date();
        this.precio=precioaux;
    }

    public void setProd(producto prodaux){
        this.prodnum=prodaux;
    }

}
