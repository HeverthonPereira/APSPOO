public class ItemPedido {
    private Pedido pedido;  // A referência para o pedido
    private Produto produto;
    private double precoPagar;
    private int quantidade;

    public ItemPedido(Pedido pedido, Produto produtoEscolhido, int quantidadeProduto) {
    }


    public boolean ItensPedido(Pedido pedido, Produto produto, int quantidade, double precoPagar) {
        if (produto.getQuantidadeProduto() >= quantidade) {
            this.setQuantidade(quantidade);
            this.setPrecoPagar(precoPagar);
            this.produto = produto;
            this.pedido = pedido;  // Associando o pedido ao item
            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - this.getQuantidade());
            return true;
        } else {
            return false;
        }
    }

    
    public Pedido getPedido() {
        return pedido;  
    }

   

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoPagar() {
        return precoPagar;
    }

    public void setPrecoPagar(double precoPagar) {
        this.precoPagar = precoPagar;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
    public void mostrarItemPedido() {
        System.out.println("Produto: " + produto.getNomeProduto());
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Preço Total: " + precoPagar);
    }
}

