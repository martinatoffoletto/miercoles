package clases;

import java.util.Date;

public class factura {
    private String codigoFact;
    private Date fechaFactura;
    private String Formapago;
    private pedido pedidoaFacturar;


    public pedido getPedidoaFacturar() {
        return pedidoaFacturar;
    }



    public factura(String formapago, pedido pedidoaFacturar) {
        Formapago = formapago;
        this.pedidoaFacturar = pedidoaFacturar;
    }
    public int precioPagar(){
        int pago= pedidoaFacturar.getPreciofinal();
        return pago;

    }

    @Override
    public String toString() {
        return
                "Codigo factura='" + codigoFact + '\'' +
                ", Fecha factura=" + fechaFactura ;
    }
}
