package controllers;

import clases.*;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.stargate.grpc.StargateBearerToken;
import io.stargate.proto.StargateGrpc;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    public MongoDatabase IngresarBD(){
        String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TP");
            return database;
        }
    }





   public void CargarDatos() {
       //PARTE MONGO
       String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
       MongoDatabase database = IngresarBD();
       if (database != null) {
           MongoCollection<Document> productos = database.getCollection("productos");
           MongoCollection<Document> usuarios = database.getCollection("usuarios");


           for (Document doc : productos.find()) {

               List LIST = doc.getList("comente", String.class);
               ArrayList<String> comentarios = new ArrayList<>(LIST);
               producto product = new producto(doc.getString("nombre"), doc.getString("desc"), doc.getString("fotos"), comentarios, doc.getInteger("precio"));
               this.prods.add(product);
           }

           for (Document doc : usuarios.find()) {
               user usuario = new user(doc.getString("nombre"), doc.getString("direccion"), doc.getInteger("dni"), doc.getInteger("actividad"), doc.getString("categoria"));
               this.usuario.add(usuario);
           }

       }


       //PARTE CASSANDRA
/*

          String ASTRA_DB_ID      = "<id>";
          String ASTRA_DB_REGION  = "us-east1";
          String ASTRA_TOKEN      = "<token>";
          String ASTRA_KEYSPACE   = "tpfinal";

           //-------------------------------------
           // 1. Initializing Connectivity
           //-------------------------------------
           ManagedChannel channel = ManagedChannelBuilder
                   .forAddress(ASTRA_DB_ID + "-" + ASTRA_DB_REGION + ".apps.astra.datastax.com", 443)
                   .useTransportSecurity()
                   .build();

           // blocking stub version
           StargateGrpc.StargateBlockingStub blockingStub =
                   StargateGrpc.newBlockingStub(channel)
                           .withDeadlineAfter(10, TimeUnit.SECONDS)
                           .withCallCredentials(new StargateBearerToken(ASTRA_TOKEN));


       ResultSet facturasCas = (ResultSet) session.execute("SELECT * FROM tpfinal.facturas");
       ResultSet pedidosCas = (ResultSet) session.execute("SELECT * FROM tpfinal.pedidos");
       ResultSet carritosCas = (ResultSet) session.execute("SELECT * FROM tpfinal.carritos");

       for (Row row : facturasCas) {
           // Access row data using column names or indexes
           String codFacturas = row.getString("codFacturas");
           String fechaFacturas = (String ) row.getString("fechaFacturas");
           String metodoPago = row.getString("metodoPago");
           int nroPedido = row.getInt("nroPedido");
           //crea factura

       }

       for (Row row : pedidosCas) {
           // Access row data using column names or indexes
           int nroPedido = row.getInt("nroPedido");
           String fechaPedidos = (String ) row.getString("fechaPedidos");
           int codCarrito = row.getInt("codCarrito");
           int precio = row.getInt("precio");
           //crea pedido

       }

       for (Row row : carritosCas) {
           // Access row data using column names or indexes
           int codCarrito = row.getInt("codCarrito");
           String carro = row.getString("carro");
           int dni = row.getInt("dni");
           int precio = row.getInt("precio");
           //crea carrito
       }



    }




*/
   }
    //carrito guardar y crear
    public static carrito CrearCarrito(user user){
        carrito car=new carrito(user);
        return car;
    }
    public static void GuardarCarro(carrito car){
        carritos.add(car);//agregar a bd tmbn
       // MongoDatabase bs=IngresarBD();
        //MongoCollection<Document> carritos = bs.getCollection("carritos");
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


    public static ArrayList<carrito> getCarritos(user user) {
        ArrayList<carrito> carritosuser= new ArrayList<carrito>();
        for (carrito carr: carritos){
           int dni= carr.getUsuario().getDni();
           if (dni==user.getDni()){
               carritosuser.add(carr);
           }
        }
        return carritosuser;}


}
