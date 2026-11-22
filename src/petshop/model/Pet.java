package petshop.model;

// entidade pet ligada ao seu tutor
public class Pet {
    private String nome;
    private String especie;
    private String raca;
    private Tutor tutor;

    public Pet(String nome, String especie, String raca, Tutor tutor) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.tutor = tutor;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public Tutor getTutor() {
        return tutor;
    }

    @Override
    public String toString() {
        return nome + " (" + especie + " - " + raca + ")";
    }
}
