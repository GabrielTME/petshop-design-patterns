package petshop.model;

import petshop.service.Servico;

// junta o pet, a data/hora e o servico escolhido
public class Agendamento {
    private Pet pet;
    private Servico servico;
    private String dataHora;

    // recebe a interface servico, entao aceita tanto servico simples quanto com decorators
    public Agendamento(Pet pet, Servico servico, String dataHora) {
        this.pet = pet;
        this.servico = servico;
        this.dataHora = dataHora;
    }

    public Pet getPet() {
        return pet;
    }

    public Servico getServico() {
        return servico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public double getValorTotal() {
        // o preco ja vem somado caso tenha adicionais embrulhando o servico
        return servico.getPreco();
    }

    @Override
    public String toString() {
        return "Agendamento {" +
                "\n  Tutor: " + pet.getTutor() +
                "\n  Pet: " + pet +
                "\n  Data/Hora: " + dataHora +
                "\n  Servico: " + servico.getDescricao() +
                "\n  Total: R$ " + String.format("%.2f", getValorTotal()) +
                "\n}";
    }
}
