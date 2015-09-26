package trabalho.poo2;


public class PessoaFisica extends Cliente {

    private String cpf;

    public PessoaFisica(String cpf, int cod, String nome, String end, String tel, String data) {
        super(cod, nome, end, tel, data);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        String str = "F;" + this.cpf;
        return str;
    }

}
