package trabalho.poo2;

public class Compras {

    private int NF;
    private Fornecedor fornecedor;
    private String data;
    private Produto produto;
    private int qtd;
    private float valorCompra;

    public Compras(int nf, Fornecedor fornecedor, String data, Produto produto, int qtd) {
        this.NF = nf;
        this.data = data;
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.qtd = qtd;
        this.valorCompra = (produto.getCusto() * this.qtd);
    }

    public int getNF() {
        return NF;
    }

    public void setNF(int nF) {
        NF = nF;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
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

    public float getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(float valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append(" ").append(this.NF);
        st.append(" ").append(this.fornecedor);
        st.append(" ").append(this.data);
        st.append(" ").append(this.produto);
        st.append(" ").append(this.qtd);
        st.append(" ").append(this.valorCompra);
        return st.toString();
    }

}
