package trabalho.poo2;

import java.util.Comparator;

public abstract class Cliente implements Comparator<Cliente>, Comparable<Cliente> {

    protected int codigo;
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected String data_cad;

    public Cliente(int cod, String nome, String ende, String tel, String data) {
        this.codigo = cod;
        this.nome = nome;
        this.endereco = ende;
        this.telefone = tel;
        this.data_cad = data;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData_cad() {
        return data_cad;
    }

    public void setData_cad(String data_cad) {
        this.data_cad = data_cad;
    }

    @Override
    public int compare(Cliente o1, Cliente o2) {
        return o1.getNome().compareTo(o2.getNome());
    }

    @Override
    public int compareTo(Cliente o) {
        return this.getNome().compareTo(o.getNome());
    }

}
