package trabalho.poo2;

public class Vendas implements Comparable<Vendas> {

    protected Cliente cliente;
    protected String data;
    protected Produto produto;
    protected int qtd;
    protected String formaPagamento;
    protected float valor;

    public Vendas(String data, Produto produto, int qtd, String formaPagamento, Cliente c) {
        this.cliente = c;
        this.data = data;
        this.produto = produto;
        this.qtd = qtd;
        this.formaPagamento = formaPagamento;
        this.valor = (produto.getPreco() * this.qtd);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return this.getQtd() + "";
    }
    
    
    @Override
    public int compareTo(Vendas o) {
        float oCusto = o.produto.getCusto() * o.getQtd();
        float thisCusto = this.produto.getCusto() * this.getQtd();
        if(oCusto == thisCusto)
            return (o.formaPagamento.compareTo(this.formaPagamento));
        else
            return (oCusto > thisCusto ? 1 : -1);
    
    }

}
