/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho.poo2;

import java.util.ArrayList;

/**
 *
 * @author 20122BSI0387
 */
public class Controladora {

    private final ArrayList<Cliente> clientes;
    private final ArrayList<Fornecedor> fornecedores;
    private final ArrayList<Produto> produtos;
    private final ArrayList<Compras> compras;
    private final ArrayList<Vendas> vendas;

    private Relatorio relatorio;

    public Controladora(String clientes, String fornecedores, String produtos, String compras, String vendas) {
        LeArquivo arquivo = new LeArquivo();

        this.clientes = arquivo.MontaClientes(clientes);
        this.fornecedores = arquivo.MontaFornecedores(fornecedores);
        this.produtos = arquivo.MontaProdutos(produtos);
        this.compras = arquivo.MontaCompras(compras, this.produtos, this.fornecedores);
        this.vendas = arquivo.MontaVendas(vendas, this.produtos, this.clientes);

    }

    public void geraRelatorio() {
        relatorio = new Relatorio();
        relatorio.aPagar(this.compras);
        relatorio.aReceber(this.vendas, this.clientes);
        relatorio.vendasProd(this.vendas, this.produtos);
        relatorio.vendasPgto(this.vendas);
        relatorio.estoque(this.vendas, this.produtos, this.compras);
    }

}
