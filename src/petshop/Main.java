
package petshop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import petshop.decorator.CorteUnhasDecorator;
import petshop.decorator.EscovacaoDentesDecorator;
import petshop.decorator.HidratacaoDecorator;
import petshop.decorator.PerfumeDecorator;

import petshop.factory.BanhoFactory;
import petshop.factory.ServicoFactory;
import petshop.factory.TosaFactory;
import petshop.factory.TosaHigienicaFactory;

import petshop.model.Agendamento;
import petshop.model.Pet;
import petshop.model.Tutor;

import petshop.service.Servico;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    // Listas que armazenam os dados enquanto o programa estiver aberto
    private static final List<Tutor> tutores = new ArrayList<>();
    private static final List<Pet> pets = new ArrayList<>();
    private static final List<Agendamento> agendamentos = new ArrayList<>();

    // Formato de data e hora utilizado nos agendamentos
    private static final DateTimeFormatter FORMATADOR =
            DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm")
                    .withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {

        int opcao;

        do {
            exibirMenu();

            opcao = lerInteiro("Escolha uma opção: ", 1, 6);

            switch (opcao) {

                case 1:
                    cadastrarTutor();
                    break;

                case 2:
                    cadastrarPet();
                    break;

                case 3:
                    criarAgendamento();
                    break;

                case 4:
                    listarAgendamentos();
                    break;

                case 5:
                    listarCadastros();
                    break;

                case 6:
                    System.out.println("\nEncerrando o sistema Pet Shop...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 6);

        scanner.close();
    }

    // Exibe as opções principais do sistema
    private static void exibirMenu() {

        System.out.println("\n================================");
        System.out.println("          PET SHOP");
        System.out.println("================================");
        System.out.println("1 - Cadastrar tutor");
        System.out.println("2 - Cadastrar pet");
        System.out.println("3 - Criar agendamento");
        System.out.println("4 - Listar agendamentos");
        System.out.println("5 - Listar tutores e pets");
        System.out.println("6 - Sair");
        System.out.println("================================");
    }

    // Lê um número inteiro dentro de um intervalo permitido
    private static int lerInteiro(String mensagem, int minimo, int maximo) {

        while (true) {

            System.out.print(mensagem);

            String entrada = scanner.nextLine().trim();

            try {

                int valor = Integer.parseInt(entrada);

                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }

                System.out.println("Digite uma opção entre "
                        + minimo + " e " + maximo + ".");

            } catch (NumberFormatException e) {

                System.out.println("Digite um número válido.");
            }
        }
    }

    // Lê textos e impede campos vazios
    private static String lerTexto(String mensagem) {

        while (true) {

            System.out.print(mensagem);

            String texto = scanner.nextLine().trim();

            if (!texto.isEmpty()) {
                return texto;
            }

            System.out.println("Este campo é obrigatório.");
        }
    }

    // Cadastra um novo tutor
    private static void cadastrarTutor() {

        System.out.println("\n===== CADASTRO DE TUTOR =====");

        String nome = lerTexto("Nome do tutor: ");

        String telefone;

        while (true) {

            telefone = lerTexto("Telefone com DDD: ");

            String numeros = telefone.replaceAll("\\D", "");

            if (numeros.length() == 10 || numeros.length() == 11) {
                break;
            }

            System.out.println(
                    "Telefone inválido. Informe DDD e número de telefone.");
        }

        Tutor tutor = new Tutor(nome, telefone);

        tutores.add(tutor);

        System.out.println("\nTutor cadastrado com sucesso!");
    }

    // Cadastra um pet e associa ao tutor escolhido
    private static void cadastrarPet() {

        System.out.println("\n===== CADASTRO DE PET =====");

        if (tutores.isEmpty()) {

            System.out.println(
                    "Não existem tutores cadastrados. Cadastre um tutor primeiro.");

            return;
        }

        String nome = lerTexto("Nome do pet: ");
        String especie = lerTexto("Espécie: ");
        String raca = lerTexto("Raça: ");

        System.out.println("\nSelecione o tutor responsável:");

        for (int i = 0; i < tutores.size(); i++) {

            System.out.println((i + 1) + " - " + tutores.get(i));
        }

        int opcao = lerInteiro(
                "Escolha o tutor: ", 1, tutores.size());

        Tutor tutorSelecionado = tutores.get(opcao - 1);

        Pet pet = new Pet(nome, especie, raca, tutorSelecionado);

        pets.add(pet);

        System.out.println("\nPet cadastrado com sucesso!");
    }

    // Lista os serviços disponíveis e utiliza suas respectivas factories
    private static Servico escolherServico() {

        System.out.println("\n===== SERVIÇOS DISPONÍVEIS =====");
        System.out.println("1 - Banho");
        System.out.println("2 - Tosa");
        System.out.println("3 - Tosa Higiênica");

        int opcao = lerInteiro("Escolha o serviço: ", 1, 3);

        ServicoFactory factory;

        switch (opcao) {

            case 1:
                factory = new BanhoFactory();
                break;

            case 2:
                factory = new TosaFactory();
                break;

            case 3:
                factory = new TosaHigienicaFactory();
                break;

            default:
                throw new IllegalStateException("Serviço inválido.");
        }

        return factory.criarServico();
    }

    // Permite adicionar vários adicionais utilizando o padrão Decorator
    private static Servico escolherAdicionais(Servico servico) {

        Set<Integer> adicionaisSelecionados = new HashSet<>();

        int opcao;

        do {

            System.out.println("\n===== ADICIONAIS =====");
            System.out.println("1 - Hidratação (+ R$ 25,00)");
            System.out.println("2 - Corte de Unhas (+ R$ 15,00)");
            System.out.println("3 - Perfume (+ R$ 10,00)");
            System.out.println("4 - Escovação de Dentes (+ R$ 20,00)");
            System.out.println("0 - Finalizar adicionais");

            opcao = lerInteiro("Escolha um adicional: ", 0, 4);

            if (opcao == 0) {
                break;
            }

            if (!adicionaisSelecionados.add(opcao)) {

                System.out.println("Este adicional já foi selecionado.");

                continue;
            }

            switch (opcao) {

                case 1:
                    servico = new HidratacaoDecorator(servico);
                    break;

                case 2:
                    servico = new CorteUnhasDecorator(servico);
                    break;

                case 3:
                    servico = new PerfumeDecorator(servico);
                    break;

                case 4:
                    servico = new EscovacaoDentesDecorator(servico);
                    break;
            }

            System.out.println("Adicional aplicado com sucesso!");

        } while (opcao != 0);

        return servico;
    }

    // Valida e recebe a data e o horário do agendamento
    private static String lerDataHora() {

        while (true) {

            String entrada = lerTexto(
                    "Data e horário (dd/MM/aaaa HH:mm): ");

            try {

                LocalDateTime dataHora =
                        LocalDateTime.parse(entrada, FORMATADOR);

                if (!dataHora.isAfter(LocalDateTime.now())) {

                    System.out.println(
                            "A data e o horário devem ser futuros.");

                    continue;
                }

                return dataHora.format(FORMATADOR);

            } catch (DateTimeParseException e) {

                System.out.println(
                        "Data ou horário inválido. Exemplo: 25/11/2026 14:00");
            }
        }
    }

    // Cria um novo agendamento
    private static void criarAgendamento() {

        System.out.println("\n===== NOVO AGENDAMENTO =====");

        if (pets.isEmpty()) {

            System.out.println(
                    "Não existem pets cadastrados. Cadastre um pet primeiro.");

            return;
        }

        System.out.println("\nSelecione o pet:");

        for (int i = 0; i < pets.size(); i++) {

            System.out.println(
                    (i + 1) + " - " + pets.get(i)
                    + " | Tutor: " + pets.get(i).getTutor().getNome());
        }

        int opcaoPet = lerInteiro(
                "Escolha o pet: ", 1, pets.size());

        Pet petSelecionado = pets.get(opcaoPet - 1);

        Servico servico = escolherServico();

        servico = escolherAdicionais(servico);

        String dataHora = lerDataHora();

        // Impede dois agendamentos para o mesmo pet no mesmo horário
        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getPet() == petSelecionado
                    && agendamento.getDataHora().equals(dataHora)) {

                System.out.println(
                        "\nEste pet já possui um agendamento neste horário.");

                return;
            }
        }

        Agendamento agendamento = new Agendamento(
                petSelecionado,
                servico,
                dataHora
        );

        agendamentos.add(agendamento);

        System.out.println("\n===== AGENDAMENTO REALIZADO =====");
        System.out.println(agendamento);
    }

    // Lista todos os agendamentos cadastrados
    private static void listarAgendamentos() {

        System.out.println("\n===== AGENDAMENTOS =====");

        if (agendamentos.isEmpty()) {

            System.out.println("Nenhum agendamento cadastrado.");

            return;
        }

        for (int i = 0; i < agendamentos.size(); i++) {

            System.out.println("\nAgendamento #" + (i + 1));
            System.out.println(agendamentos.get(i));
        }
    }

    // Lista todos os tutores e seus respectivos pets
    private static void listarCadastros() {

        System.out.println("\n===== TUTORES CADASTRADOS =====");

        if (tutores.isEmpty()) {

            System.out.println("Nenhum tutor cadastrado.");

        } else {

            for (int i = 0; i < tutores.size(); i++) {

                System.out.println(
                        (i + 1) + " - " + tutores.get(i));
            }
        }

        System.out.println("\n===== PETS CADASTRADOS =====");

        if (pets.isEmpty()) {

            System.out.println("Nenhum pet cadastrado.");

        } else {

            for (int i = 0; i < pets.size(); i++) {

                Pet pet = pets.get(i);

                System.out.println(
                        (i + 1) + " - " + pet
                        + " | Tutor: " + pet.getTutor().getNome());
            }
        }
    }
}
