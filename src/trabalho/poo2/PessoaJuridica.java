package trabalho.poo2;

import java.util.Comparator;

public class PessoaJuridica extends Cliente {

    protected String cnpj;
    protected int ie;

    public PessoaJuridica(String cnpj, int ie, int cod, String nome, String ende, String tel, String data) {
        super(cod, nome, ende, tel, data);
        this.cnpj = cnpj;
        this.ie = ie;
    }

    public String toString() {
        String str = "J;" + this.cnpj;
        return str;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getIe() {
        return ie;
    }

    public void setIe(int ie) {
        this.ie = ie;
    }

}
