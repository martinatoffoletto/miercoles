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
    private ArrayList<producto> prods;
    private ArrayList<factura> facturas;
    private ArrayList<user> usuario;
    private ArrayList<pedido> pedidos;

    public controllerCompras(){
        this.prods = new ArrayList<producto>();
        this.facturas = new ArrayList<factura>();
        this.usuario = new ArrayList<user>();
        this.pedidos = new ArrayList<pedido>();
        CargarDatos();
    }


    //carga datos de la base de datos

   public void CargarDatos(){
        String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TP");
            MongoCollection<Document> productos = database.getCollection("productos");
            MongoCollection<Document> pedidoss = database.getCollection("pedidos");
            MongoCollection<Document> factura = database.getCollection("facturas");
            MongoCollection<Document> usuarios = database.getCollection("usuarios");


            for (Document doc : productos.find()) {
                producto product = new producto();
                this.prods.add(product);
            }

            for (Document doc : usuarios.find()) {
                user usuario = new user();
                this.usuario.add(usuario);
            }

            for (Document doc : factura.find()) {
                factura fact = new factura();
                this.facturas.add(fact);
            }

            for (Document doc : pedidoss.find()) {
                pedido pedido = new pedido();
                this.pedidos.add(pedido);
            }
        }
    }





    public void agregarProducto(producto prod){
        this.prods.add(prod);


    }


    public pedido ConvertirPedido(carrito car, user user){
        pedido ped= new pedido(car,user);
        return  ped;

    }

    public factura ConvertirFactura(pedido pedido, String pago){
        factura fac= new factura(pago,pedido );
        return  fac;
    }


    public ArrayList<producto> getProds() {
        return prods;
    }

    public ArrayList<factura> getFacturas() {
        return facturas;
    }

    public  ArrayList<user> getUsuario() {
        return usuario;
    }

    public ArrayList<pedido> getPedidos() {
        return pedidos;
    }


}
