package trabalho.poo2;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Relatorio {

    public void aPagar(ArrayList<Compras> c) {
        HashMap<Fornecedor, Float> forn = new HashMap<>();
        float total, acc, atual;

        for (Compras compras : c) {
            forn.put(compras.getFornecedor(), new Float(0));
        }

        for (Compras compras : c) {
            Fornecedor fornecedor = compras.getFornecedor();

            total = compras.getValorCompra();
            atual = forn.get(fornecedor);

            acc = total + atual;

            forn.replace(fornecedor, acc);
        }

        Map<Fornecedor, Float> mapaSorted = new TreeMap<>(forn);
        Iterator<Fornecedor> iForn = mapaSorted.keySet().iterator();

        StringBuilder st = new StringBuilder();
        while (iForn.hasNext()) {
            Fornecedor key = iForn.next();
            st.append(key.getNome()).append(";");
            st.append(key.getCnpj()).append(";");
            st.append(key.getContato()).append(";");
            st.append(key.getTelefone()).append(";");
            String dinheiro;
            dinheiro = String.format("%.2f", forn.get(key));
            dinheiro = dinheiro.replace(".", ",");
            st.append("R$ ").append(dinheiro).append("\n");
        }

        escreverArquivo(st, "apagar.csv", "Fornecedor;CNPJ;Pessoa de contato;Telefone;Total a pagar\n");

    }

    public void aReceber(ArrayList<Vendas> vendas, ArrayList<Cliente> clientes) {

        HashMap<Cliente, Float> cli = new HashMap<>();
        float total, acc, atual;

        for (Cliente c : clientes) {
            if(c != null)
                cli.put(c, new Float(0));
        }

        for (Vendas v : vendas) {
            Cliente cliente = v.getCliente();
            if(cliente != null) {
                total = v.getValor();
                atual = cli.get(cliente);
                acc = total + atual;

                cli.replace(cliente, acc);
            }
        }

        Map<Cliente, Float> mapaSorted = new TreeMap<>(cli);
        Iterator<Cliente> iForn = mapaSorted.keySet().iterator();

        StringBuilder st = new StringBuilder();
        while (iForn.hasNext()) {
            Cliente key = iForn.next();
            StringBuilder append = st.append(key.getNome()).append(";");
            st.append(key).append(";");
            st.append(key.getTelefone()).append(";");
            st.append(key.getData_cad()).append(";");
            String dinheiro;
            dinheiro = String.format("%.2f", cli.get(key));
            dinheiro = dinheiro.replace(".", ",");
            st.append("R$ ").append(dinheiro).append("\n");
        }

        escreverArquivo(st, "areceber.csv", "Cliente;Tipo;CPF/CNPJ;Telefone;Data de cadastro;Total a pagar\n");
    }

    

    public void vendasProd(ArrayList<Vendas> vendas, ArrayList<Produto> produtos) {   
        /*
        * Mais facil fazer criando uma classe intermediadora entre as vendas... dai pra ordenar fica melhor
        */
        ArrayList<VendasLucro> vendasLucro = new ArrayList<>();
        
        for (Produto p : produtos) {
            vendasLucro.add( new VendasLucro(p, 0, 0));         
        }
        float acc, atual, total;
        Produto produto;
        for (Vendas v : vendas) {
            // Calculo da receita bruta ....:
            produto = v.getProduto();
            total = v.getValor();
            for(VendasLucro vl: vendasLucro) {
                if(vl.getProduto().getCod() == produto.getCod()) {
                    vl.updateBruto(total);
                    total = v.getValor() - (v.getProduto().getCusto() * v.getQtd());
                    vl.updateLiquida(total);
                }
            }    
        }
        Collections.sort(vendasLucro);
        StringBuilder st = new StringBuilder();
        for(VendasLucro vl: vendasLucro)
        {
            Produto p = vl.getProduto();
            st.append(p.getCod()).append(";");
            st.append(p.getDescricao()).append(";");
            st.append("R$ ").append(vl.getReceitaBrutaFormatted()).append(";");
            st.append("R$ ").append(vl.getLucroVendaFormatted()).append("\n");
        }

        escreverArquivo(st, "vendasprod.csv", "Código;Produto;Receita bruta;Lucro\n");
    }

    public void vendasPgto(ArrayList<Vendas> vendas) {
        ArrayList<PgtoLucro> pgto = new ArrayList<>();
        pgto.add ( new PgtoLucro("F", 0, 0));
        pgto.add ( new PgtoLucro("C", 0, 0));
        pgto.add ( new PgtoLucro("$", 0, 0));
        pgto.add ( new PgtoLucro("D", 0, 0));
        pgto.add ( new PgtoLucro("T", 0, 0));
        pgto.add ( new PgtoLucro("X", 0, 0));
        
        float acc, atual, total;
        Produto produto;
        for (Vendas v : vendas) {
            produto = v.getProduto();
            total = v.getValor();
            for(PgtoLucro pg: pgto) {
                if(pg.getForma().equals(v.getFormaPagamento())) {
                    pg.updateBruto(total);
                    total = v.getValor() - (v.getProduto().getCusto() * v.getQtd());
                    pg.updateLiquida(total);
                }
            }                
        }
        
        Collections.sort(pgto);
        StringBuilder st = new StringBuilder();
        
        for(PgtoLucro pg: pgto) {
            st.append(pg.getForma()).append(";");
            st.append("R$ ").append(pg.getReceitaBrutaFormatted()).append(";");
            st.append("R$ ").append(pg.getLucroVendaFormatted()).append("\n");            
        }
        escreverArquivo(st, "vendaspgto.csv", "Modo de pagamento;Receita bruta;Lucro\n"); 
    }    
    
    public void estoque(ArrayList<Vendas> vendas, ArrayList<Produto> produtos, ArrayList<Compras> compras) { 
        ArrayList<Estoque> estoque = new ArrayList<>();
        for(Produto p: produtos) {
            estoque.add( new Estoque(p.getCod(), p.getDescricao(), p.getEstoqueAtual(), "", p.getEstoqueMin()));
        }
        for(Vendas v: vendas) {
            for(Estoque e: estoque) {                
                if(e.getCod_produto() == v.getProduto().getCod()) {                    
                    e.diminuiEstoque(v.getQtd());                    
                }
            }
        }
        for(Compras c: compras) {
            for(Estoque e: estoque) {
                if(e.getCod_produto() == c.getProduto().getCod()) {                    
                    e.aumentaEstoque(c.getQtd());                    
                }                
            }
        }
        for(Estoque e: estoque) {
            e.analisaObs();
        }
        Collections.sort(estoque);   
        StringBuilder st = new StringBuilder();
        for(Estoque e: estoque) {
            st.append(e.getCod_produto()).append(";");
            st.append(e.getDesc()).append(";");
            st.append(e.getQtd()).append(";");
            st.append(e.getObs()).append("\n");
            //System.out.println(""+e.getCod_produto() + ";" + e.getDesc() + ";" + e.getQtd()+ ";" + e.getObs());            
        }
        escreverArquivo(st, "estoque.csv", "Código;Produto;Quantidade em estoque;Observações\n"); 
        
    }
    
    public void escreverArquivo(StringBuilder st, String arquivo, String cabecalho) {
        try (BufferedWriter arq = new BufferedWriter(new FileWriter(arquivo));) {
            arq.write(cabecalho);
            arq.write(st.toString());
        } catch (IOException e) {
            System.out.println("Houve um erro ao escrever no arquivo " + e.getMessage());
            System.exit(1); //encerra programa. 
        }
    }
    public static void printMap(Map<Fornecedor, Float> map) {
        for (Entry<Fornecedor, Float> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }

}
