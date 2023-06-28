package controllers;

import clases.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
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
        //CargarDatos();
    }


    //carga datos de la base de datos (MODIFICAR)


    public MongoDatabase IngresarBD(){
        String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TP");
            return database;
        }
    }


   public void CargarDatos(){
        String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
        MongoDatabase database= IngresarBD();
        if (database!=null) {
            MongoCollection<Document> productos = database.getCollection("productos");
            MongoCollection<Document> pedidoss = database.getCollection("pedidos");
            MongoCollection<Document> factura = database.getCollection("facturas");
            MongoCollection<Document> usuarios = database.getCollection("usuarios");
            MongoCollection<Document> carritos = database.getCollection("carritos");


            for (Document doc : productos.find()) {
                //producto product = new producto();
                //this.prods.add(product);
            }

            for (Document doc : usuarios.find()) {
               // user usuario = new user();
                //this.usuario.add(usuario);
            }

            for (Document doc : factura.find()) {
               // factura fact = new factura();
                //this.facturas.add(fact);
            }

            for (Document doc : pedidoss.find()) {
                //pedido pedido = new pedido();
                //this.pedidos.add(pedido);
            }
        }
    }





    //carrito guardar y crear
    public carrito CrearCarrito(user user){
        carrito car=new carrito(user);
        return car;
    }
    public void GuardarCarro(carrito car){
        carritos.add(car);//agregar a bd tmbn
        MongoDatabase bs=IngresarBD();
        MongoCollection<Document> carritos = bs.getCollection("carritos");
        //carritos.insertOne(new Document().append("_id", new ObjectId()).append("title", "Ski Bloopers").append("genres", Arrays.asList("Documentary", "Comedy")));




    }

      //crear pedidio en base a carrito
    public pedido ConvertirPedido(carrito car){
        pedido ped= new pedido(car);
        pedidos.add(ped);
        MongoDatabase bs=IngresarBD();
        MongoCollection<Document> pedidos = bs.getCollection("pedidos");
        //pedidos.insertOne(new Document().append("_id", new ObjectId()).append("title", "Ski Bloopers").append("genres", Arrays.asList("Documentary", "Comedy")));
        return  ped;

    }

    //crear factura en base a pedido
    public factura ConvertirFactura(pedido pedido, String pago){
        factura fac= new factura(pago,pedido );
        facturas.add(fac);
        MongoDatabase bs=IngresarBD();
        MongoCollection<Document> facturas = bs.getCollection("facturas");
        //facturas.insertOne(new Document().append("_id", new ObjectId()).append("title", "Ski Bloopers").appe
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


    public ArrayList<carrito> getCarritos(user user) {
        ArrayList<carrito> carritosuser= new ArrayList<carrito>();
        for (carrito carr: carritos){
           int dni= carr.getUsuario().getDni();
           if (dni==user.getDni()){
               carritosuser.add(carr);
           }
        }
        return carritosuser;}


}
