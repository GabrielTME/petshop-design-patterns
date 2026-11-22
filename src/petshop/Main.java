package petshop;

import petshop.decorator.HidratacaoDecorator;
import petshop.factory.BanhoFactory;
import petshop.factory.ServicoFactory;
import petshop.factory.TosaFactory;
import petshop.model.Agendamento;
import petshop.model.Pet;
import petshop.model.Tutor;
import petshop.service.Servico;

// fluxo basico de ponta a ponta pra testar os dois patterns (o menu interativo entra na proxima parte)
public class Main {
    public static void main(String[] args) {
        Tutor tutor = new Tutor("Carlos Silva", "(48) 99999-1234");
        Pet pet = new Pet("Thor", "Cachorro", "Golden Retriever", tutor);

        // cria o servico pela factory sem dar new direto na classe concreta
        ServicoFactory banhoFactory = new BanhoFactory();
        Servico servico1 = banhoFactory.criarServico();

        // aplica o decorator por cima do servico base pra somar o adicional
        servico1 = new HidratacaoDecorator(servico1);

        Agendamento agendamento1 = new Agendamento(pet, servico1, "25/11/2026 14:00");
        System.out.println(agendamento1);

        System.out.println();

        // segundo exemplo usando outra factory, sem adicional
        ServicoFactory tosaFactory = new TosaFactory();
        Servico servico2 = tosaFactory.criarServico();

        Agendamento agendamento2 = new Agendamento(pet, servico2, "28/11/2026 10:30");
        System.out.println(agendamento2);
    }
}
