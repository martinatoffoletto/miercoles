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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
                int codCarrito = (int) row.getValues(0).getInt();
                String setProd = row.getValues(1).getString();
                int dni = (int) row.getValues(2).getInt();
                int precio = (int) row.getValues(3).getInt();
                //crea carrito
                carrito nuevoCarro = new carrito(buscarUser(dni));
                producto prodaux= buscarProducto(setProd);
                //agrega productos
                carritos.add(nuevoCarro);
            }

            for (QueryOuterClass.Row row : pedidosCas.getRowsList()) {
                // Access row data using column names or indexes
                int nroPedido = (int) row.getValues(0).getInt();
                long fechaPedidos = row.getValues(1).getTime();
                int codCarrito = (int) row.getValues(2).getInt();
                int precio = (int) row.getValues(3).getInt();
                //crea pedido
                pedido pedidoNuevo = new pedido(buscarCarr(codCarrito));
                Date datefor = new Date(fechaPedidos);
                pedidoNuevo.setFecha(datefor);
                pedidoNuevo.setnroPedido(nroPedido);
                pedidos.add(pedidoNuevo);
            }

            for (QueryOuterClass.Row row : facturasCas.getRowsList()) {
                // Access row data using column names or indexes
                String codFacturas = row.getValues(0).getString();
                long fechaFacturas = row.getValues(1).getTime();
                String metodoPago = row.getValues(2).getString();
                int nroPedido = (int) row.getValues(3).getInt();
                Date datefor = new Date(fechaFacturas);
                //crea factura
                factura facturaCreada = new factura(codFacturas, metodoPago, buscarPed(nroPedido), datefor);
                facturas.add(facturaCreada);

            }

    }



    public pedido buscarPed(int codaux) {
        pedido resultado = null;
        for (pedido auxusu : pedidos) {
            resultado = auxusu;
            if (auxusu.getCod()==codaux) {
                break;
            }
        }
        return resultado;
    }

    public carrito buscarCarr(int codaux) {
        carrito resultado = null;
        for (carrito auxusu : carritos) {
            resultado = auxusu;
            if (auxusu.getNro() == codaux) {
                break;
            }
        }
        return resultado;
    }

    //devuelve usuario segurn dni

    public user buscarUser(int dniaux) {
        user resultado = null;
        for (user auxusu : usuario) {
            resultado = auxusu;
            if (auxusu.getDni()==dniaux) {
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

        //agrego carrito
        int ccar=car.getCodCarrito();
        ArrayList<producto> lprod= car.getProductos();
        String carro = "";
        for (producto pro: lprod){
            carro+=pro.getNombre()+", ";
        }
        int dniaux=car.getUsuario().getDni();
        int precio=car.getPrecio();

        blockingStub.executeBatch(
                QueryOuterClass.Batch.newBuilder()
                        .addQueries(
                                QueryOuterClass.BatchQuery.newBuilder()
                                        .setCql("INSERT INTO tpfinal.carritos (codCarrito, carro,dni,precio) VALUES" +
                                                "("+ccar+",'"+carro+"',"+dniaux+","+precio+")")
                                        .build()).build());
    }

      //crear pedidio en base a carrito
    public static pedido ConvertirPedido(carrito car){
        pedido ped= new pedido(car);
        pedidos.add(ped);
        //CASSANDRA NUEVO PEDIDO
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
        //agrego pedido
        Random rr= new Random();
        int nped=rr.nextInt(1000);
        ped.setnroPedido(nped);
        int ccar=car.getCodCarrito();
        Date hoy=new Date();
        ped.setFecha(hoy);
        long fped= hoy.getTime();
        int precio=car.getPrecio();
        blockingStub.executeBatch(
                QueryOuterClass.Batch.newBuilder()
                        .addQueries(
                                QueryOuterClass.BatchQuery.newBuilder()
                                        .setCql("INSERT INTO tpfinal.pedidos (nroPedido, fechaPedido,codCarrito,precio) VALUES" +
                                                "("+nped+", '"+fped+"',"+ccar+","+precio+")")
                                        .build()).build());
        return  ped;
    }

    //crear factura en base a pedido
    public static factura ConvertirFactura(pedido pedido1, String pago) {
        Random rr= new Random();
        int num=rr.nextInt(1000);
        factura fac = new factura(String.valueOf(num),pago, pedido1,pedido1.getFechaPedido());
        facturas.add(fac);
        //CASSANDRA NUEVA FACTURA
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
        //agrego factura
        Date hoy=new Date();
        long fped= hoy.getTime();
        blockingStub.executeBatch(
                QueryOuterClass.Batch.newBuilder()
                        .addQueries(
                                QueryOuterClass.BatchQuery.newBuilder()
                                        .setCql("INSERT INTO tpfinal.facturas (codFacturas, fechaFacturas,metodoPago,nroPedido) VALUES" +
                                                "('"+num+"','"+fped+"','"+pago+"',"+pedido1.getCod()+")")
                                        .build()).build());
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




