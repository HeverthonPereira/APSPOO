public class Pedido {
    private final int numPedido;
    private final Cliente cliente;

    public Pedido(Cliente cliente, int numPedido) {
        this.cliente = cliente;
        this.numPedido = numPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getNumPedido() {
        return numPedido;
    }
}
