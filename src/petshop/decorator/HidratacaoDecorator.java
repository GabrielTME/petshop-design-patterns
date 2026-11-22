package petshop.decorator;

import petshop.service.Servico;

// decorator concreto que adiciona hidratacao por cima de qualquer servico
public class HidratacaoDecorator extends AdicionalDecorator {

    public HidratacaoDecorator(Servico servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        // concatena o adicional na descricao que veio do servico embrulhado
        return servico.getDescricao() + " + Hidratacao";
    }

    @Override
    public double getPreco() {
        // soma o preco da hidratacao ao valor acumulado
        return servico.getPreco() + 25.0;
    }
}
