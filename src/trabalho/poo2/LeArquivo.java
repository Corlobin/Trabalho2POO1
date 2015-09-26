package trabalho.poo2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeArquivo {

    private int qtdClientes;
    private int qtdFornecedores;
    private int qtdProdutos;
    private int qtdCompras;
    private int qtdVendas;

    /*    
        Leitura de arquivos simples.
    */
    public ArrayList<Cliente> MontaClientes(String nameArq) {
        ArrayList<Cliente> clientes = new ArrayList<>();        
        try (BufferedReader bf = new BufferedReader(new FileReader(nameArq));) {
            // Saltar a primeira linha
            String linha = bf.readLine();
            
            while ((linha = bf.readLine()) != null) {
                String[] splitter = linha.split(";");                
                int codigo = Integer.parseInt(splitter[0]);
                String nome = splitter[1];
                String endereco = splitter[2];
                String telefone = splitter[3];
                String data = splitter[4];
                String tipo = splitter[5];
                String cadastro = splitter[6];
                
                if (tipo.equals("J")) {
                    int ie = Integer.parseInt(splitter[7]); //Inscricao Estadual
                    clientes.add(new PessoaJuridica(cadastro, ie, codigo, nome, endereco, telefone, data));
                } else {
                    clientes.add(new PessoaFisica(cadastro, codigo, nome, endereco, telefone, data));
                }              
            }

        } catch (IOException e) {
            System.out.println("Erro de I/O");           
            System.exit(1);
        }

        return clientes;
    }
    public ArrayList<Fornecedor> MontaFornecedores(String nameArq) {
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();        
        try (BufferedReader bf = new BufferedReader(new FileReader(nameArq));) {
            String linha = bf.readLine();
                    
            while ((linha = bf.readLine()) != null) {
                String[] splitter = linha.split(";");
                int cod = Integer.parseInt(splitter[0]);
                String nome = splitter[1];
                String end = splitter[2];
                String tel = splitter[3];
                String cnpj = splitter[4];
                String contato = splitter[5];
                fornecedores.add(new Fornecedor(contato, cnpj, 0, cod, nome, end, tel, ""));
            }

        } catch (IOException e) {
            System.out.println("Erro de I/O");           
            System.exit(1);
        }

        return fornecedores;
    }

    /*    
        Produtos arrumado; Problemas com o Scanner consertado.
    */

    public ArrayList<Produto> MontaProdutos(String nameArq) {
        ArrayList<Produto> produtos = new ArrayList<>();        
        try (BufferedReader bf = new BufferedReader(new FileReader(nameArq));) {
            String linha = bf.readLine();
                    
            while ((linha = bf.readLine()) != null) {
                String[] splitter = linha.split(";");               
                int cod = Integer.parseInt(splitter[0]);
                String desc = splitter[1];
                int estMin = Integer.parseInt(splitter[2]);
                int estAtual = Integer.parseInt(splitter[3]);
                String cus = splitter[4];
                String cusNew = cus.replaceAll(",", ".");
                float custo = Float.parseFloat(cusNew);
                int lucroP = Integer.parseInt(splitter[5]);
                produtos.add(new Produto(cod, desc, estMin, estAtual, custo, lucroP));
            }

        } catch (IOException e) {
            System.out.println("Erro de I/O");           
            System.exit(1);
        }
        return produtos;
    }
    // Mantive a leitura dos outros do jeito que voce fez
    public ArrayList<Compras> MontaCompras(String nameArq, ArrayList<Produto> produtos, ArrayList<Fornecedor> fornecedores) {
        this.qtdCompras = CalculaTamanhoArquivo(nameArq);
        int itt = 0;
        int pos = 0;
        ArrayList<Compras> compras = new ArrayList<>();

        try {
            File arquivo = new File(nameArq);
            try (Scanner sc = new Scanner(arquivo).useDelimiter("\n")) {
                while (sc.hasNext()) {
                    //elimina a primeira linha do arquivo
                    if (itt == 0) {
                        sc.next();
                        itt++;
                        sc.useDelimiter("\\n|;");
                    } else {
                        int nf = sc.nextInt();
                        int codFn = sc.nextInt();
                        Fornecedor fornecedor = Busca(fornecedores, codFn);
                        String data = sc.next();
                        int codP = sc.nextInt();
                        Produto produto = null;
                        for (Produto p : produtos) {
                            if (p.getCod() == codP) {
                                produto = p;
                            }
                        }
                        int qtd = sc.nextInt();
                        compras.add(new Compras(nf, fornecedor, data, produto, qtd));
                    }
                }
                //sc.close();  com resources nao precisa fechar
            }
        } catch (IOException e) {
            System.out.println("Erro de I/O");           
            System.exit(1);
        }
        

        return compras;
    }

    public ArrayList<Vendas> MontaVendas(String nameArq, ArrayList<Produto> produtos, ArrayList<Cliente> clientes) {
        int itt = 0;
        int pos = 0;
        ArrayList<Vendas> vendas = new ArrayList<>();

        try {
            File arquivo = new File(nameArq);
            try (Scanner sc = new Scanner(arquivo).useDelimiter("\n")) {
                while (sc.hasNext()) {
                    //elimina a primeira linha do arquivo
                    if (itt == 0) {
                        sc.next();
                        itt++;
                        sc.useDelimiter("\\n|;");
                    } else {
                        String cliente = sc.next();
                        String data = sc.next();
                        int codP = sc.nextInt();
                        Produto produto = null;
                        for (Produto p : produtos) {
                            if (p.getCod() == codP) {
                                produto = p;
                            }
                        }
                        int qtd = sc.nextInt();
                        String formaPG = sc.next();
                        Cliente cl = null;
                        if (formaPG.equals("F")) {
                            int codCliente = Integer.parseInt(cliente);
                            for (Cliente c : clientes) {
                                if (c.getCodigo() == codCliente) {
                                    cl = c;
                                }
                            }
                        }
                        vendas.add(new Vendas(data, produto, qtd, formaPG, cl));
                        
                    }
                }
                //sc.close(); //fecha arquivo
            }
            System.out.println("" + pos);
        } catch (FileNotFoundException e) {
            System.out.println("Erro de I/O");           
            System.exit(1); //encerra programa. 
        }
        //imprimeVet(vendas,this.qtdVendas);
        return vendas;
    }

    public int CalculaTamanhoArquivo(String nameArq) {
        int tam = 0;
        try {
            File arquivo = new File(nameArq);
            try (Scanner sc = new Scanner(arquivo).useDelimiter("\\||\\n")) {
                while (sc.hasNext()) {
                    sc.next();
                    tam++;
                }
                sc.close(); //fecha arquivo
            }

            System.out.println("tamanho " + nameArq + " " + tam);
        } catch (IOException e) {
            System.out.println("Erro de I/O");           
            System.exit(1); //encerra programa. 
        }
        return tam - 1;
    }

    public Fornecedor Busca(ArrayList<Fornecedor> fornecedores, int cod) {
        for (Fornecedor f : fornecedores) {
            if (f.codigo == cod) {
                return f;
            }
        }
        System.out.println(cod + " fornecedor n�o encontrado.");
        return null;
    }

}
