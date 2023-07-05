package controllers;

import clases.*;
import com.mongodb.client.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.stargate.grpc.StargateBearerToken;
import io.stargate.proto.QueryOuterClass;
import io.stargate.proto.StargateGrpc;
import org.bson.*;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class controllerCompras {


    private static ArrayList<producto> prods;

    private static ArrayList<factura> facturas;
    static ArrayList<user> usuario;
    private static ArrayList<pedido> pedidos;

    private static ArrayList<carrito> carritos;

    public controllerCompras() throws ParseException {
        this.prods = new ArrayList<producto>();
        this.facturas = new ArrayList<factura>();
        this.usuario = new ArrayList<user>();
        this.pedidos = new ArrayList<pedido>();
        this.carritos = new ArrayList<carrito>();
        CargarDatos();
    }


    public void CargarDatos() throws ParseException {
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
        }

        /*

            //PARTE CASSANDRA
            String ASTRA_DB_ID = "9d7510a0-3365-40ac-9a12-8ce74d3c0480";
            String ASTRA_DB_REGION = "us-east1";
            String ASTRA_TOKEN = "AstraCS:dAmZPJZhDigsDyKMCkEBDgRo:2d7fd310969a5cbff8dde5664ad4d702568a7334fb2cfce0bd24816ad29b4bc5";
            String ASTRA_KEYSPACE = "tpfinal";

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

            QueryOuterClass.ResultSet facturasCas = facturasCasor.getResultSet();
            QueryOuterClass.ResultSet pedidosCas = pedidosCasor.getResultSet();
            QueryOuterClass.ResultSet carritosCas = carritosCasor.getResultSet();


            //Extrae datos

            for (QueryOuterClass.Row row : carritosCas.getRowsList()) {
                // Access row data using column names or indexes
                QueryOuterClass.Collection setProd = row.getValues(1).getCollection();
                int codCarrito = (int) row.getValues(0).getInt();
                int dni = (int) row.getValues(2).getInt();
                int precio = (int) row.getValues(3).getInt();
                List lprod = setProd.getElementsList();
                //crea carrito
                carrito nuevoCarro = new carrito(buscarUser(dni));
                //agrega productos
                for (Object pro : lprod) {
                    String prod = String.valueOf(pro);
                    producto auxprod = buscarProducto(prod);
                    nuevoCarro.agregarProd(auxprod);
                }
                carritos.add(nuevoCarro);
            }

            for (QueryOuterClass.Row row : pedidosCas.getRowsList()) {
                // Access row data using column names or indexes
                int nroPedido = (int) row.getValues(0).getInt();
                String fechaPedidos = row.getValues(1).getString();
                int codCarrito = (int) row.getValues(2).getInt();
                int precio = (int) row.getValues(3).getInt();
                //crea pedido
                pedido pedidoNuevo = new pedido(buscarCarr(codCarrito));
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date datefor = formato.parse(fechaPedidos);
                pedidoNuevo.setFecha(datefor);
                pedidoNuevo.setnroPedido(nroPedido);
                pedidos.add(pedidoNuevo);


            }

            for (QueryOuterClass.Row row : facturasCas.getRowsList()) {
                // Access row data using column names or indexes
                String codFacturas = row.getValues(0).getString();
                String fechaFacturas = row.getValues(1).getString();
                String metodoPago = row.getValues(2).getString();
                int nroPedido = (int) row.getValues(3).getInt();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date datefor = formato.parse(fechaFacturas);
                //crea factura
                factura facturaCreada = new factura(codFacturas, metodoPago, buscarPed(nroPedido), datefor);
                facturas.add(facturaCreada);

            }

         */

    }



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
        carritos.add(car);
        ///CASSANDRA NUEVO CARRO


    }

      //crear pedidio en base a carrito
    public static pedido ConvertirPedido(carrito car){
        pedido ped= new pedido(car);
        pedidos.add(ped);
        //CASSANDRA NUEVO PEDIDO
        return  ped;



    }

    //crear factura en base a pedido
    public static factura ConvertirFactura(pedido pedido, String pago) {
        String num ="32"; //cambiar pa q sea num random
        factura fac = new factura(num,pago, pedido,pedido.getFechaPedido());
        facturas.add(fac);
        //CASSANDRA NUEVA FACTURA
        return fac;
         }


        //lista productos
        public static ArrayList<producto> getProds () {
            return prods;
        }

        //iniciar seccion. LISTA USUARIOS
        public static ArrayList<user> getUsuario () {
            return usuario;
        }


        //lista facturas, pedidos y carritos por usuario
        public static ArrayList<factura> getFacturas (user user)
        {
            ArrayList<factura> factxusee = new ArrayList<factura>();
            for (factura fact : facturas) {
                int dni = fact.getPedidoaFacturar().getContenido().getUsuario().getDni();
                if (dni == user.getDni()) {
                    factxusee.add(fact);
                }
            }
            return factxusee;
        }

        public static ArrayList<pedido> getPedidos (user user){
            ArrayList<pedido> pedxuser = new ArrayList<pedido>();
            for (pedido ped : pedidos) {
                int dni = ped.getContenido().getUsuario().getDni();
                if (dni == user.getDni()) {
                    pedxuser.add(ped);
                }
            }
            return pedxuser;
        }


        public static ArrayList<carrito> getCarritos (user user){
            ArrayList<carrito> carritosuser = new ArrayList<carrito>();
            for (carrito carr : carritos) {
                int dni = carr.getUsuario().getDni();
                if (dni == user.getDni()) {
                    carritosuser.add(carr);
                }
            }
            return carritosuser;
        }






    }




