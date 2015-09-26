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
public class PgtoLucro implements Comparable<PgtoLucro> {
    String forma;
    float receitabruta;
    float lucrovenda;

    public PgtoLucro() {
    }
    
    public PgtoLucro(String forma, float receitabruta, float lucrovenda) {
        this.forma = forma;
        this.receitabruta = receitabruta;
        this.lucrovenda = lucrovenda;
    }

    

    public float getLucrovenda() {
        return lucrovenda;
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

    public void setReceitabruta(float receitabruta) {
        this.receitabruta = receitabruta;
    }
    public void updateBruto(float bruto) {
        this.receitabruta += bruto;
    }
    public void updateLiquida(float liquida) {
        this.lucrovenda += liquida;
    }

    public String getForma() {
        return forma;
    }
    
    @Override
    public int compareTo(PgtoLucro o) {
        if(o.lucrovenda == this.lucrovenda)
            return (this.forma.compareTo(o.forma));
        else
            return (o.lucrovenda > this.lucrovenda ? 1 : -1);
    
    }   
}
