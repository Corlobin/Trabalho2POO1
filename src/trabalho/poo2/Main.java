package trabalho.poo2;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        String clientes = args[0];
        String fornecedores = args[1];
        String produtos = args[2];
        String compras = args[3];
        String vendas = args[4];

        Controladora controladora;
        controladora = new Controladora(clientes, fornecedores, produtos, compras, vendas);
        controladora.geraRelatorio();

    }
}
