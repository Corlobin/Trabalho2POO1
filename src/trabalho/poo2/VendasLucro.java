/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.poo2;

/**
 *
 * @author 20122BSI0387
 */
public class VendasLucro implements Comparable<VendasLucro> {
    Produto produto;
    String forma;
    float receitabruta;
    float lucrovenda;

    public VendasLucro(Produto produto, float receitabruta, float lucrovenda) {
        this.produto = produto;
        this.receitabruta = receitabruta;
        this.lucrovenda = lucrovenda;
    }

    public float getLucrovenda() {
        return lucrovenda;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
    
    public float getReceitabruta() {        
        return receitabruta;
    }
    public String getReceitaBrutaFormatted() {
        String dinheiro;
        dinheiro = String.format("%.2f", this.receitabruta);
        dinheiro = dinheiro.replace(".", ",");
        return dinheiro;
    }
    public String getLucroVendaFormatted() {
        String dinheiro;
        dinheiro = String.format("%.2f", this.lucrovenda);
        dinheiro = dinheiro.replace(".", ",");
        return dinheiro;
    }
        
    public void setLucrovenda(float lucrovenda) {
        this.lucrovenda = lucrovenda;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setReceitabruta(float receitabruta) {
        this.receitabruta = receitabruta;
    }
    public void updateBruto(float bruto) {
        this.receitabruta += bruto;
    }
    public void updateLiquida(float liquida) {
        this.lucrovenda += liquida;
    }

    @Override
    public int compareTo(VendasLucro o) {
        if(o.lucrovenda == this.lucrovenda)
            return (o.produto.getCod() < this.getProduto().getCod() ? 1 : -1);
        else
            return (o.lucrovenda > this.lucrovenda ? 1 : -1);
    
    }
}
