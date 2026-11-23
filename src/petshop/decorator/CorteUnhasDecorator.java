package petshop.decorator;

import petshop.service.Servico;

// decorator concreto que adiciona corte de unhas por cima de qualquer servico
public class CorteUnhasDecorator extends AdicionalDecorator {

    public CorteUnhasDecorator(Servico servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        // concatena o adicional na descricao que veio do servico embrulhado
        return servico.getDescricao() + " + Corte de Unhas";
    }

    @Override
    public double getPreco() {
        // soma o preco do adicional ao valor acumulado
        return servico.getPreco() + 15.0;
    }
}
