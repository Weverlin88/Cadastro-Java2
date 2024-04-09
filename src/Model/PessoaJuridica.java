package Model;

public class PessoaJuridica extends Pessoa {
    private final String nome;
    private String cnpj;

    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.nome = nome;
        this.cnpj = cnpj;
    }

    @Override
public void exibir() {
    System.out.println("Nome: " + getNome());
    System.out.println("CNPJ: " + getCnpj());
}


    @Override
    public void setNome(String nome) {
        super.setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public int getId() {
        return id;
    }
}
