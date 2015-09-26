package trabalho.poo2;

import java.util.Comparator;

public class Produto implements Comparable<Produto>, Comparator<Produto> {

    private int cod;
    private String descricao;
    private int estoqueMin;
    private int estoqueAtual;
    private float custo;
    private int lucroPercentual;
    private float precoVenda;
    
    public Produto(int cod, String desc, int estoqueMin, int estoqueAtual, float custo, int lucroP) {
        this.cod = cod;
        this.descricao = desc;
        this.estoqueMin = estoqueMin;
        this.estoqueAtual = estoqueAtual;
        this.custo = custo;
        this.lucroPercentual = lucroP;
        float percentual = (float) lucroP / 100;
        this.precoVenda = custo * (1 + percentual);

    }

    public float getCusto() {
        return this.custo;
    }

    public float getPreco() {
        return this.getPrecoVenda();
    }

    public int getCod() {
        return this.cod;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append(" ").append(this.getCod());
        st.append(" ").append(this.getDescricao());
        st.append(" ").append(this.getEstoqueMin());
        st.append(" ").append(this.getEstoqueAtual());
        st.append(" ").append(this.getCusto());
        st.append(" ").append(this.getLucroPercentual());
        st.append(" ").append(this.getPrecoVenda());
        return st.toString();
    }
    
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

   @Override
    public int compare(Produto o1, Produto o2) {
        if(o1.getPreco() != o2.getPreco()) 
            return (int) (o1.getPreco()-o2.getPreco());
        else 
            return o1.getCod()-o2.getCod();
         
    }

    @Override
    public int compareTo(Produto o) {
        if(this.getPreco()==o.getPreco()) {
            return (this.getCod() > o.getCod() ? 1 : -1);                
        }
        return (this.getPreco() > o.getPreco() ? 1 : -1);                                    
    }
    
    public int check(int a, int b)
    {
        return (~a - ~b) >>> 31 | -((a - b) >>> 31);
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the estoqueMin
     */
    public int getEstoqueMin() {
        return estoqueMin;
    }

    /**
     * @param estoqueMin the estoqueMin to set
     */
    public void setEstoqueMin(int estoqueMin) {
        this.estoqueMin = estoqueMin;
    }

    /**
     * @return the estoqueAtual
     */
    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    /**
     * @param estoqueAtual the estoqueAtual to set
     */
    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(float custo) {
        this.custo = custo;
    }

    /**
     * @return the lucroPercentual
     */
    public int getLucroPercentual() {
        return lucroPercentual;
    }

    /**
     * @param lucroPercentual the lucroPercentual to set
     */
    public void setLucroPercentual(int lucroPercentual) {
        this.lucroPercentual = lucroPercentual;
    }

    /**
     * @return the precoVenda
     */
    public float getPrecoVenda() {
        return precoVenda;
    }

    /**
     * @param precoVenda the precoVenda to set
     */
    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

   
}
