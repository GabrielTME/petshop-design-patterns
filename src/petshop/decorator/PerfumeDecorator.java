package petshop.decorator;

import petshop.service.Servico;

// decorator concreto que adiciona perfume por cima de qualquer servico
public class PerfumeDecorator extends AdicionalDecorator {

    public PerfumeDecorator(Servico servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        // concatena o adicional na descricao que veio do servico embrulhado
        return servico.getDescricao() + " + Perfume";
    }

    @Override
    public double getPreco() {
        // soma o preco do adicional ao valor acumulado
        return servico.getPreco() + 10.0;
    }
}
