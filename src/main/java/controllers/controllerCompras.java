package controllers;

import clases.*;
import com.mongodb.client.*;
import org.bson.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class controllerCompras {


    private static ArrayList<producto> prods;

    private static ArrayList<factura> facturas;
     static ArrayList<user> usuario;
    private static ArrayList<pedido> pedidos;

    private static ArrayList<carrito> carritos;

    public controllerCompras(){
        this.prods = new ArrayList<producto>();
        this.facturas = new ArrayList<factura>();
        this.usuario = new ArrayList<user>();
        this.pedidos = new ArrayList<pedido>();
        this.carritos = new ArrayList<carrito>();
        CargarDatos();
    }


    //carga datos de la base de datos





   public void CargarDatos(){
        //PARTE MONGO
       String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
       try (MongoClient mongoClient = MongoClients.create(uri)) {
           MongoDatabase database = mongoClient.getDatabase("TP");
           System.out.println("ENTRE " + database.getName());

           if (database != null) {
               MongoCollection<Document> productos = database.getCollection("productos");
               MongoCollection<Document> usuarios = database.getCollection("usuarios");
               System.out.println("colecciones");

               for (Document doc : productos.find()) {

                   List LIST = doc.getList("comente", String.class);
                   ArrayList<String> comentarios = new ArrayList<>(LIST);
                   producto product = new producto(doc.getString("nombre"), doc.getString("desc"), doc.getString("imagen"), comentarios, doc.getInteger("precio"));
                   this.prods.add(product);
               }



               for (Document doc : usuarios.find()) {
                   user usuario = new user(doc.getString("nombre"), doc.getString("direccion"), doc.getInteger("dni"), doc.getInteger("actividad"), doc.getString("categoria"));
                   this.usuario.add(usuario);
               }


           }


           //PARTE CASSANDRA


       }
    }





    //carrito guardar y crear
    public static carrito CrearCarrito(user user){
        carrito car=new carrito(user);
        return car;
    }
    public static void GuardarCarro(carrito car){
        carritos.add(car);//agregar a bd tmbn CASSANDRA





    }

      //crear pedidio en base a carrito
    public static pedido ConvertirPedido(carrito car){
        pedido ped= new pedido(car);
        pedidos.add(ped);
        //AGREGAR PEDIDO A BASE DE DATOS
        return  ped;

    }

    //crear factura en base a pedido
    public static factura ConvertirFactura(pedido pedido, String pago){
        factura fac= new factura(pago,pedido );
        facturas.add(fac);
        //AGREGAR FACTURA A BASE DE DATOS
        return  fac;
    }


    //lista productos
    public static ArrayList<producto> getProds() {
        return prods;
    }

   //iniciar seccion. LISTA USUARIOS
    public static ArrayList<user> getUsuario() {
        return usuario;
    }



    //lista facturas, pedidos y carritos por usuario
    public static ArrayList<factura> getFacturas(user user)
    {
        ArrayList<factura> factxusee= new ArrayList<factura>();
        for (factura fact: facturas){
            int dni=fact.getPedidoaFacturar().getContenido().getUsuario().getDni();
            if (dni==user.getDni()){
                factxusee.add(fact);
            }
        }
        return factxusee;
    }

    public static ArrayList<pedido> getPedidos(user user) {
        ArrayList<pedido> pedxuser = new ArrayList<pedido>();
        for (pedido ped: pedidos){
            int dni= ped.getContenido().getUsuario().getDni();
            if (dni==user.getDni()){
                pedxuser.add(ped);
            }
        }
        return pedxuser;
    }


    public static ArrayList<carrito> getCarritos(user user) {
        ArrayList<carrito> carritosuser= new ArrayList<carrito>();
        for (carrito carr: carritos){
           int dni= carr.getUsuario().getDni();
           if (dni==user.getDni()){
               carritosuser.add(carr);
           }
        }
        return carritosuser;}


    //EUGE PASAR FORMATOS

    //para guadar carrito hay q buscar el objeto usuario
    public static user BuscarUser(int dni){
        for (user user: usuario){
            if (user.getDni()==dni){
                return user;
            }
        }
        return null;
    }

    //para guardar pedido hay q buscar el objeto carrito
    public static carrito BuscarCarro(int codCarro){
        for (carrito car: carritos){
            if (car.getCodCarrito()==codCarro){
                return car;
            }
        }
        return null;

    }

    //para guardar factura hay q buscar objeto pedido
    public static pedido BuscarPedido(int nroPedido){
        for (pedido ped: pedidos){
            if (ped.getNroPedido()==nroPedido){
                return ped;
            }
        }
        return null;

    }


}



