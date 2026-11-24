# Pet Shop - Sistema de Agendamentos (Design Patterns)

Projeto desenvolvido para a disciplina de **Design Patterns**, implementando o **Tema 4 (Pet Shop)** em Java puro, utilizando os padrões de projeto **Factory Method** e **Decorator**.

## Descrição do sistema

Aplicação de console em Java para gerenciamento de agendamentos de serviços em um pet shop, sem utilização de banco de dados.

O sistema permite cadastrar tutores e seus respectivos pets, criar agendamentos, selecionar serviços e adicionar serviços extras de forma dinâmica.

As informações são armazenadas em memória durante a execução. Portanto, os dados são perdidos quando a aplicação é encerrada.

## Tecnologias e pré-requisitos

- **Java (JDK 11 ou superior)**
- Sem dependências externas.
- Sem banco de dados: armazenamento em memória utilizando coleções Java.
- Interface interativa executada pelo terminal.

## Estrutura do projeto

```text
src/
└── petshop/
    ├── Main.java
    ├── model/
    │   ├── Tutor.java
    │   ├── Pet.java
    │   └── Agendamento.java
    ├── service/
    │   ├── Servico.java
    │   ├── Banho.java
    │   ├── Tosa.java
    │   └── TosaHigienica.java
    ├── factory/
    │   ├── ServicoFactory.java
    │   ├── BanhoFactory.java
    │   ├── TosaFactory.java
    │   └── TosaHigienicaFactory.java
    └── decorator/
        ├── AdicionalDecorator.java
        ├── HidratacaoDecorator.java
        ├── CorteUnhasDecorator.java
        ├── PerfumeDecorator.java
        └── EscovacaoDentesDecorator.java
```



## Como obter o projeto

Antes de executar, é necessário ter o Git instalado e clonar o repositório:

```bash
git clone https://github.com/GabrielTME/petshop-design-patterns.git
```

Entre na pasta do projeto:

```bash
cd petshop-design-patterns
```

## Instruções de execução
No terminal, a partir da raiz do repositório:

### 1. Compilar

**Windows (PowerShell):**

```powershell
javac -encoding UTF-8 -d out src/petshop/model/*.java src/petshop/service/*.java src/petshop/factory/*.java src/petshop/decorator/*.java src/petshop/Main.java
```

**Linux / macOS:**

```bash
javac -encoding UTF-8 -d out src/petshop/model/*.java src/petshop/service/*.java src/petshop/factory/*.java src/petshop/decorator/*.java src/petshop/Main.java
```

### 2. Executar

```bash
java -cp out petshop.Main
```

## Funcionalidades do sistema

Ao executar a aplicação, é apresentado um menu interativo com as seguintes opções:

1. **Cadastrar tutor:** registra o nome e o telefone do tutor, com validação dos campos.
2. **Cadastrar pet:** registra nome, espécie e raça, vinculando o pet a um tutor já cadastrado.
3. **Criar agendamento:** permite selecionar um pet, escolher um serviço base, adicionar serviços extras e informar a data e o horário.
4. **Listar agendamentos:** exibe os agendamentos cadastrados, incluindo tutor, pet, serviço, data, horário e valor total.
5. **Listar tutores e pets:** apresenta os cadastros realizados durante a execução.
6. **Sair:** encerra a aplicação.

### Validações implementadas

- Impede o cadastro de campos obrigatórios vazios.
- Valida o formato do telefone do tutor, considerando números com 10 ou 11 dígitos.
- Exige que exista um tutor cadastrado para realizar o cadastro de um pet.
- Impede a seleção de opções inválidas no menu.
- Valida o formato da data e do horário.
- Não permite agendamentos em datas ou horários anteriores ao momento atual.
- Impede que o mesmo pet tenha dois agendamentos com a mesma data e horário informados.
- Impede a seleção repetida do mesmo adicional em um único agendamento.

## Serviços e valores

### Serviços base

| Serviço | Valor |
|---|---:|
| Banho | R$ 50,00 |
| Tosa | R$ 70,00 |
| Tosa Higiênica | R$ 45,00 |

### Serviços adicionais

| Adicional | Valor |
|---|---:|
| Hidratação | R$ 25,00 |
| Corte de Unhas | R$ 15,00 |
| Perfume | R$ 10,00 |
| Escovação de Dentes | R$ 20,00 |

Os valores adicionais são somados ao preço do serviço base por meio do padrão Decorator.

## Exemplos de cenários de utilização

Os exemplos abaixo demonstram os valores esperados para diferentes combinações de serviços:

| Serviço selecionado | Valor total |
|---|---:|
| Banho | R$ 50,00 |
| Banho + Hidratação | R$ 75,00 |
| Tosa | R$ 70,00 |
| Tosa Higiênica + Perfume | R$ 55,00 |
| Banho + todos os adicionais | R$ 120,00 |
| Tosa + Hidratação + Corte de Unhas | R$ 110,00 |

### Exemplo de saída de um agendamento

```text
Agendamento {
  Tutor: Carlos Silva ((48) 99999-1234)
  Pet: Thor (Cachorro - Golden Retriever)
  Data/Hora: 25/11/2026 14:00
  Servico: Banho + Hidratacao
  Total: R$ 75,00
}
```

*Exemplo ilustrativo de saída. Os dados são cadastrados pelo usuário durante a execução.*

## Patterns utilizados

### 1. Factory Method

**Problema resolvido:** desacopla a criação dos serviços concretos do restante da aplicação. Cada serviço possui sua própria fábrica, responsável por criar a instância correspondente.

Dessa forma, a aplicação utiliza uma estrutura organizada para criar serviços, facilitando a inclusão de novos tipos sem concentrar toda a lógica de criação em um único local.

**Classes envolvidas:**

- `Servico` (Product)
- `Banho`, `Tosa`, `TosaHigienica` (ConcreteProducts)
- `ServicoFactory` (Creator)
- `BanhoFactory`, `TosaFactory`, `TosaHigienicaFactory` (ConcreteCreators)

### 2. Decorator

**Problema resolvido:** permite adicionar serviços extras dinamicamente a qualquer serviço base, sem precisar criar diversas classes para representar todas as combinações possíveis.

Cada adicional envolve um objeto `Servico`, acrescentando seu próprio valor ao preço total e complementando a descrição do serviço.

**Classes envolvidas:**

- `Servico` (Component)
- `Banho`, `Tosa`, `TosaHigienica` (ConcreteComponents)
- `AdicionalDecorator` (Decorator)
- `HidratacaoDecorator`, `CorteUnhasDecorator`, `PerfumeDecorator`, `EscovacaoDentesDecorator` (ConcreteDecorators)

## Testes realizados

Foram realizados testes manuais pela interface do console, verificando:

- Cadastro de tutores.
- Cadastro de pets vinculados aos tutores.
- Criação de agendamentos.
- Seleção dos diferentes serviços disponíveis.
- Aplicação de múltiplos adicionais.
- Listagem dos agendamentos e dos cadastros.
- Validação de campos obrigatórios.
- Validação de telefone e data.
- Impedimento de agendamentos duplicados para o mesmo pet, na mesma data e horário.
- Verificação do funcionamento geral do menu.

Os cenários de combinação de serviços apresentados anteriormente também servem como referência para conferir os cálculos de preços.

## Declaração de uso de IA

- **Modelos utilizados:** Google Gemini e ChatGPT.
- **Partes em que foram utilizados:** apoio na estruturação inicial do projeto, organização das classes dos padrões, elaboração do desenvolvimento, implementação de validações e revisão do README.md.

A utilização de IA ocorreu como ferramenta de apoio ao desenvolvimento, com revisão e testes do código pela equipe.

## Divisão do projeto e contribuição de cada integrante

### Gabriel Tomé ([@GabrielTME](https://github.com/GabrielTME)) - Arquitetura base, entidades e núcleo dos patterns

- Estruturação inicial do repositório (`.gitignore`, organização dos pacotes `model`, `service`, `factory` e `decorator`, e versão inicial do `README.md`).
- Modelagem e implementação das entidades principais do domínio: `Tutor`, `Pet` e `Agendamento` (incluindo cálculo do valor total e formatação do comprovante).
- Implementação do núcleo do padrão **Factory Method**: interface `Servico`, produtos concretos `Banho` e `Tosa`, creator abstrato `ServicoFactory` e fábricas `BanhoFactory` e `TosaFactory`.
- Implementação da base do padrão **Decorator**: classe abstrata `AdicionalDecorator` para composição dinâmica de serviços e o primeiro decorator concreto `HidratacaoDecorator`.
- Criação do fluxo inicial ponta a ponta em `Main.java`, integrando as entidades com **Factory Method** e **Decorator**.

### Guilherme Cesarino ([@GuilhermeCesarino](https://github.com/GuilhermeCesarino)) - Expansão de adicionais e serviço complementar

- Implementação de novos adicionais concretos estendendo `AdicionalDecorator`: `CorteUnhasDecorator`, `PerfumeDecorator` e `EscovacaoDentesDecorator`.
- Implementação do serviço `TosaHigienica` e sua respectiva factory.
- Expansão das possibilidades de composição dos serviços utilizando Decorator.
- Atualização da documentação das classes e dos padrões utilizados.

### Elison Miller ([@elison4](https://github.com/elison4)) - Menu interativo, validações, testes e documentação

- Evolução do `Main.java` com menu interativo no console (`Scanner`), gerenciamento em memória das listas de tutores, pets e agendamentos e exibição das listagens.
- Integração no fluxo interativo da criação de serviços via **Factory Method** e da aplicação de múltiplos adicionais via **Decorator**.
- Implementação das validações de entrada (campos obrigatórios, telefone, data/horário futuros, adicionais duplicados e conflito de horário para o mesmo pet).
- Execução de testes manuais dos fluxos, conferência dos cálculos de preços e revisão final do `README.md`.

---

**Observação:** os dados cadastrados são mantidos somente enquanto a aplicação estiver em execução. O sistema não utiliza banco de dados nem realiza persistência dos registros.

## Referências

- ORACLE. *Java Documentation*. Disponível em: https://docs.oracle.com/en/java/. Acesso em: 24 nov. 2026.
- GAMMA, Erich; HELM, Richard; JOHNSON, Ralph; VLISSIDES, John. *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley, 1994.
- ORACLE. *The Java Tutorials*. Disponível em: https://docs.oracle.com/javase/tutorial/. Acesso em: 24 nov. 2026.
