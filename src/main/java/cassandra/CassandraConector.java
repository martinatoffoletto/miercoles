package cassandra;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.session.Session;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.*;
import com.datastax.oss.driver.api.core.time.TimestampGenerator;
import com.datastax.oss.driver.internal.core.type.codec.TimeCodec;

import java.util.ArrayList;
import static java.lang.System.out;

public class CassandraConector {

    private Session session;

    public void main(String[] args) {
        // Set up the configuration for the Cassandra cluster
        //CqlSessionBuilder sessionBuilder = CqlSession.builder();
        //sessionBuilder.withConfigLoader(DriverConfigLoader.fromClasspath("application.conf"));
        // Connect to the Cassandra cluster
        // CqlSessionBuilder session = CqlSession.builder();
        //Actividad

        //datos







        }


            //Cierra cluster
        //session.close();
    }


}
/*
//agregar datos

<dependencies>
  <dependency>
    <groupId>io.stargate.grpc</groupId>
    <artifactId>grpc-proto</artifactId>
    <version>1.0.41</version>
  </dependency>
  <dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-netty-shaded</artifactId>
    <version>1.41.0</version>
  </dependency>
</dependencies>

private static final String ASTRA_DB_ID      = "<id>";
private static final String ASTRA_DB_REGION  = "<region>";
private static final String ASTRA_TOKEN      = "<token>";
private static final String ASTRA_KEYSPACE   = "<keyspace>";

public static void main(String[] args)
throws Exception {
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



   QueryOuterClass.Response queryString = blockingStub.executeQuery(QueryOuterClass
  .Query.newBuilder()
  .setCql("SELECT firstname, lastname FROM " + ASTRA_KEYSPACE + ".users")
  .build());





INSERT INTO
facturas (codFacturas, fechaFacturas, metodoPago, nroPedido)
VALUES ('a1', '2011-02-03', 'MP', 1234);

Procesar datos

// Execute a CQL query
ResultSet resultSet = session.execute("SELECT * FROM my_table");

// Process the query results
for (Row row : resultSet) {
    // Access row data using column names or indexes
    String columnValue = row.getString("column_name");
    int columnValue = row.getInt(0);

    // Process the data
    // ...
}

-----------------------------------------------------
public static void main(final String[] args)
{
   final CassandraConnector client = new CassandraConnector();
   final String ipAddress = args.length > 0 ? args[0] : "localhost";
   final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
   out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
   client.connect(ipAddress, port);
   client.close();
}

//CRea tabla

String createMovieCql =
     "CREATE TABLE movies_keyspace.movies (title varchar, year int, description varchar, "
   + "mmpa_rating varchar, dustin_rating varchar, PRIMARY KEY (title, year))";
client.getSession().execute(createMovieCql);

*/
