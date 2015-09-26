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
public class Estoque implements Comparable<Estoque> {
    private int cod_produto;
    private String desc;
    private int qtd;
    private int min;
    private String obs;

    public Estoque() {
    }

    public Estoque(int cod_produto, String desc, int qtd, String obs, int min) {
        this.cod_produto = cod_produto;
        this.desc = desc;
        this.qtd = qtd;
        this.obs = obs;
        this.min = min;
    }
    
    /**
     * @return the cod_produto
     */
    public int getCod_produto() {
        return cod_produto;
    }

    /**
     * @param cod_produto the cod_produto to set
     */
    public void setCod_produto(int cod_produto) {
        this.cod_produto = cod_produto;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the qtd
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param qtd the qtd to set
     */
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }
    
    /**
     *
     * @param qt
     */
    public void diminuiEstoque(int qt) {        
        this.qtd -= qt;
    }

    public void aumentaEstoque(int qtd) {
        this.qtd += qtd;
    }
    public void analisaObs() {
        if(this.qtd <= this.min)
            this.setObs("COMPRAR MAIS");
    }
    
    @Override
    public int compareTo(Estoque o) {
        return (this.desc.compareTo(o.desc));    
    }

}
