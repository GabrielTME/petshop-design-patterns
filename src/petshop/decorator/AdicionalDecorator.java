package petshop.decorator;

import petshop.service.Servico;

// decorator base: implementa servico e guarda outro servico dentro pra ir empilhando adicionais
public abstract class AdicionalDecorator implements Servico {
    protected Servico servico;

    public AdicionalDecorator(Servico servico) {
        this.servico = servico;
    }

    @Override
    public String getDescricao() {
        return servico.getDescricao();
    }

    @Override
    public double getPreco() {
        return servico.getPreco();
    }
}
