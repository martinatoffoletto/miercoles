package clases;

public class factura {
    private String Formapago;

    public pedido getPedidoaFacturar() {
        return pedidoaFacturar;
    }

    private pedido pedidoaFacturar;

    public factura(String formapago, pedido pedidoaFacturar) {
        Formapago = formapago;
        this.pedidoaFacturar = pedidoaFacturar;
    }
    public int precioPagar(){
        int pago= pedidoaFacturar.getPreciofinal();
        return pago;

    }
}
