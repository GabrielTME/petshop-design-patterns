# Pet Shop - Sistema de Agendamentos (Design Patterns)

Projeto desenvolvido para a disciplina de **Design Patterns**, implementando o **Tema 4 (Pet shop)** em Java puro com os padrões **Factory Method** e **Decorator**.

## Descrição do sistema

Aplicação de console em Java para gerenciamento simples de agendamentos de serviços em um pet shop, sem uso de banco de dados. O sistema trabalha com três entidades principais (`Tutor`, `Pet` e `Agendamento`) e permite criar serviços base (como Banho e Tosa) e compor adicionais opcionais (como Hidratação) de forma flexível.

## Tecnologias e pré-requisitos

- **Java (JDK 11 ou superior)**
- Sem dependências externas e sem banco de dados (tudo em memória)

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

## Instruções de execução

No terminal, a partir da raiz do repositório:

### Compilar

**Windows (PowerShell):**
```powershell
javac -encoding UTF-8 -d out src/petshop/model/*.java src/petshop/service/*.java src/petshop/factory/*.java src/petshop/decorator/*.java src/petshop/Main.java
```

**Linux / macOS:**
```bash
javac -encoding UTF-8 -d out src/petshop/model/*.java src/petshop/service/*.java src/petshop/factory/*.java src/petshop/decorator/*.java src/petshop/Main.java
```

### Executar

```bash
java -cp out petshop.Main
```

## Exemplos de uso

Ao executar `petshop.Main`, o sistema cria um tutor, um pet e dois agendamentos (um com Banho + Hidratação e outro apenas com Tosa), exibindo a saída no console:

```text
Agendamento {
  Tutor: Carlos Silva ((48) 99999-1234)
  Pet: Thor (Cachorro - Golden Retriever)
  Data/Hora: 25/11/2026 14:00
  Servico: Banho + Hidratacao
  Total: R$ 75,00
}

Agendamento {
  Tutor: Carlos Silva ((48) 99999-1234)
  Pet: Thor (Cachorro - Golden Retriever)
  Data/Hora: 28/11/2026 10:30
  Servico: Tosa
  Total: R$ 70,00
}
```

## Patterns utilizados

### 1. Factory Method
- **Problema resolvido:** desacopla a criação dos serviços concretos do restante da aplicação. Em vez de concentrar a instanciação em condicionais (`if/switch`), cada serviço possui sua própria fábrica. Para adicionar um novo serviço (como previsto na Parte 2), basta criar a nova classe que implementa `Servico` e sua respectiva subclasse de `ServicoFactory`, sem modificar o código existente.
- **Classes envolvidas:**
  - `Servico` (Product)
  - `Banho`, `Tosa`, `TosaHigienica` (ConcreteProducts)
  - `ServicoFactory` (Creator)
  - `BanhoFactory`, `TosaFactory`, `TosaHigienicaFactory` (ConcreteCreators)

### 2. Decorator
- **Problema resolvido:** permite adicionar serviços extras (adicionais) dinamicamente a qualquer serviço base sem precisar criar combinações de subclasses (como `BanhoComHidratacao`, `TosaComHidratacaoECorteDeUnhas`, etc.). Cada adicional envolve um `Servico`, acumula o preço e concatena sua descrição. Para adicionar um novo adicional na Parte 2, basta estender `AdicionalDecorator`.
- **Classes envolvidas:**
  - `Servico` (Component)
  - `Banho`, `Tosa`, `TosaHigienica` (ConcreteComponents)
  - `AdicionalDecorator` (Decorator)
  - `HidratacaoDecorator`, `CorteUnhasDecorator`, `PerfumeDecorator`, `EscovacaoDentesDecorator` (ConcreteDecorators)

## Declaração de uso de IA

- **Modelo utilizado:** Google Gemini
- **Partes em que foi utilizada:** apoio na estruturação inicial do projeto, organização das classes dos padrões Factory Method e Decorator, e rascunho da documentação inicial do `README.md` na Parte 1.

## Divisão do projeto e contribuição de cada integrante

### Integrante 1 - `Gabriel Tomé (@GabrielTME)` (Base do projeto e núcleo dos patterns)
- Estrutura inicial do repositório (`.gitignore`, organização de pacotes e `README.md` inicial);
- Entidades principais: `Tutor`, `Pet` e `Agendamento`;
- Implementação do padrão **Factory Method**: `Servico`, `Banho`, `Tosa`, `ServicoFactory`, `BanhoFactory` e `TosaFactory`;
- Implementação da base do padrão **Decorator**: `AdicionalDecorator` e `HidratacaoDecorator`;
- Fluxo inicial de ponta a ponta em `Main.java` validando a integração entre entidades e os dois patterns.

### Integrante 2 - `Guilherme Cesarino (@GuilhermeCesarino)` (Expansão de adicionais e serviço complementar)
- Implementar novos adicionais concretos estendendo `AdicionalDecorator` (ex.: `CorteUnhasDecorator`, `PerfumeDecorator` ou `EscovacaoDentesDecorator`);
- Implementar mais um serviço concreto e sua factory (ex.: `TosaHigienica` e `TosaHigienicaFactory`);
- Atualizar a lista de classes envolvidas no `README.md`.

### Integrante 3 - `` (Menu interativo no console e gestão em memória)
- Evoluir o fluxo em `Main.java` (ou classe auxiliar de console) com menu interativo via `Scanner`;
- Manter listas em memória de tutores, pets e agendamentos;
- Permitir cadastrar tutor/pet, escolher serviço via Factory Method, aplicar múltiplos adicionais via Decorator e listar agendamentos criados.

### Integrante 4 - `` (Validações, testes de cenário e fechamento da documentação)
- Adicionar validações básicas nos cadastros e agendamentos (ex.: campos obrigatórios e validação de horário);
- Criar cenários de teste/demonstração validando o cálculo de preço e descrição com múltiplos decorators empilhados;
- Revisar e finalizar o `README.md` com exemplos atualizados de uso e conferência final dos requisitos da entrega.
