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
import io.stargate.proto.QueryOuterClass;
import io.stargate.proto.StargateGrpc;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
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
        //CargarDatos();

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

          String ASTRA_DB_ID      = "9d7510a0-3365-40ac-9a12-8ce74d3c0480";
          String ASTRA_DB_REGION  = "us-east1";
          String ASTRA_TOKEN      = "AstraCS:dAmZPJZhDigsDyKMCkEBDgRo:2d7fd310969a5cbff8dde5664ad4d702568a7334fb2cfce0bd24816ad29b4bc5";
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

       //Generar datos

       QueryOuterClass.Response facturasCasor = blockingStub.executeQuery(QueryOuterClass
               .Query.newBuilder()
               .setCql("SELECT * FROM " + ASTRA_KEYSPACE + ".facturas").build());

       QueryOuterClass.Response pedidosCasor = blockingStub.executeQuery(QueryOuterClass
               .Query.newBuilder()
               .setCql("SELECT * FROM " + ASTRA_KEYSPACE + ".pedidos").build());

       QueryOuterClass.Response carritosCasor = blockingStub.executeQuery(QueryOuterClass
               .Query.newBuilder()
               .setCql("SELECT * FROM " + ASTRA_KEYSPACE + ".carritos").build());

       QueryOuterClass.ResultSet facturasCas= facturasCasor.getResultSet();
       QueryOuterClass.ResultSet pedidosCas= pedidosCasor.getResultSet();
       QueryOuterClass.ResultSet carritosCas= carritosCasor.getResultSet();


       //Extrae datos

       /*for (QueryOuterClass.Row row : carritosCas.getRowsList()) {
           // Access row data using column names or indexes
           int codCarrito  = (int) row.getValues(0).getInt();
           int dni = (int) row.getValues(2).getInt();
           int precio = (int) row.getValues(3).getInt();
           //ArrayList<String> lprod=row.getValues(1).getInt();
           //crea carrito
           carrito nuevoCarro= new carrito(buscarUser(dni));
           //agrega productos
           for (String pro:lprod){
               producto auxprod= buscarProducto(pro);
               nuevoCarro.agregarProd(auxprod);
           }
       }

       for (QueryOuterClass.Row row : pedidosCas.getRowsList()) {
           // Access row data using column names or indexes
           int nroPedido  = (int) row.getValues(0).getInt();
           //String fechaPedidos = (String) row.getValues(1).getDate();
           int codCarrito = (int) row.getValues(2).getInt();
           int precio = (int) row.getValues(3).getInt();
           //crea pedido
           pedido pedidoNuevo= new pedido(buscarCarr(codCarrito));
           //pedidoNuevo.setFecha(fechaPedidos);
           pedidoNuevo.setnroPedido(nroPedido);

       }

       for (QueryOuterClass.Row row : facturasCas.getRowsList()) {
           // Access row data using column names or indexes
           String codFacturas = row.getValues(0).getString();
           //Date fechaFacturas = (Date) row.getValues(1).getDate();
           String metodoPago = row.getValues(2).getString();
           int nroPedido = (int) row.getValues(3).getInt();
           //crea factura
           //factura facturaCreada= new factura(codFacturas,metodoPago,fechaFacturas, buscarPed(nroPedido));


       }*/


   }
   //devuelve carrito segun num
   public pedido buscarPed(int codaux) {
       pedido resultado = null;
       for (pedido auxusu : pedidos) {
           if (auxusu.getCod()==codaux) {
               resultado = auxusu;
               break;
           }
       }
       return resultado;
   }

    public carrito buscarCarr(int codaux) {
        carrito resultado = null;
        for (carrito auxusu : carritos) {
            if (auxusu.getNro()==codaux) {
                resultado = auxusu;
                break;
            }
        }
        return resultado;
    }

   //devuelve usuario segurn dni

    public user buscarUser(int dniaux) {
        user resultado = null;
        for (user auxusu : usuario) {
            if (auxusu.getDni()==dniaux) {
                resultado = auxusu;
                break;
            }
        }
        return resultado;
    }

    public producto buscarProducto(String nombreaux ) {
        producto resultado = null;
        for (producto auxusu : prods) {
            if (auxusu.getNombre().equals(nombreaux)) {
                resultado = auxusu;
                break;
            }
        }
        return resultado;
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
    //public factura ConvertirFactura(pedido pedido, String pago){
        //factura fac= new factura(pago,pedido );
        //facturas.add(fac);
        //MongoDatabase bs=IngresarBD();
        //MongoCollection<Document> facturas = bs.getCollection("facturas");
        //facturas.insertOne(new Document().append("_id", new ObjectId()).append("title", "Ski Bloopers").appe
        //return  fac;
    //}


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
