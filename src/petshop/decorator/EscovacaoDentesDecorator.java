package petshop.decorator;

import petshop.service.Servico;

// decorator concreto que adiciona escovacao de dentes por cima de qualquer servico
public class EscovacaoDentesDecorator extends AdicionalDecorator {

    public EscovacaoDentesDecorator(Servico servico) {
        super(servico);
    }

    @Override
    public String getDescricao() {
        // concatena o adicional na descricao que veio do servico embrulhado
        return servico.getDescricao() + " + Escovacao de Dentes";
    }

    @Override
    public double getPreco() {
        // soma o preco do adicional ao valor acumulado
        return servico.getPreco() + 20.0;
    }
}
