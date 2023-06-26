package controllers;

import clases.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class controllerCompras {
     private ArrayList<producto> prods;
    private ArrayList<factura> facturas;
    private ArrayList<user> usuario;
    private ArrayList<pedido> pedidos;

    public controllerCompras(){
        this.prods = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.usuario = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        CargarDatos(prods,facturas,usuario,pedidos);

    }


    //carga datos de la base de datos
    public void CargarDatos(ArrayList<producto> prods, ArrayList<factura> facturas, ArrayList<user> usuario, ArrayList<pedido> pedidos){
        String uri = "mongodb+srv://matoffo:Jimin3002@cluster0.6ertzut.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TP");
            MongoCollection<Document> productos = database.getCollection("productos");
            MongoCollection<Document> pedidoss = database.getCollection("pedidos");
            MongoCollection<Document> factura = database.getCollection("facturas");
            MongoCollection<Document> usuarios = database.getCollection("usuarios");


            for (Document doc : productos.find()) {
                producto product = new producto();
                prods.add(product);
            }

            for (Document doc : usuarios.find()) {
                user usuario = new user();
                usuario.add(usuario);
            }

            for (Document doc : factura.find()) {
                factura fact = new factura();
                facturas.add(fact);
            }

            for (Document doc : pedidoss.find()) {
                pedido pedido = new pedido();
                pedidos.add(pedido);
            }
        }
    }



    public void agregarProducto(producto prod){
        Prods.add(prod);


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

    public static ArrayList<user> getUsuario() {
        return usuario;
    }

    public ArrayList<pedido> getPedidos() {
        return pedidos;
    }
}
