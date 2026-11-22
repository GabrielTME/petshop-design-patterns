package petshop.factory;

import petshop.service.Servico;

// creator abstrato do factory method: define o metodo de criacao sem acoplar nas classes concretas
public abstract class ServicoFactory {

    // cada subclasse implementa esse metodo pra devolver o servico especifico
    public abstract Servico criarServico();

    // usa o metodo fabrica trabalhando so com a interface servico
    public String resumoServico() {
        Servico servico = criarServico();
        return servico.getDescricao() + " - R$ " + String.format("%.2f", servico.getPreco());
    }
}
