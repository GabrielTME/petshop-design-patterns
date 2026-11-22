package petshop.model;

// entidade tutor, guarda os dados basicos do dono do pet
public class Tutor {
    private String nome;
    private String telefone;

    public Tutor(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return nome + " (" + telefone + ")";
    }
}
