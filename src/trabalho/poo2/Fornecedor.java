package trabalho.poo2;

public class Fornecedor extends PessoaJuridica {

    private String contato;

    public Fornecedor(String contato, String cnpj, int ie, int cod, String nome, String end, String tel, String data) {
        super(cnpj, ie, cod, nome, end, tel, data);
        this.contato = contato;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append(" ").append(this.codigo);
        st.append(" ").append(this.nome);
        st.append(" ").append(this.endereco);
        st.append(" ").append(this.telefone);
        st.append(" ").append(this.cnpj);
        st.append(" ").append(this.contato);
        return st.toString();
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String getCnpj() {
        return cnpj;
    }

}
