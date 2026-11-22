package petshop.factory;

import petshop.service.Banho;
import petshop.service.Servico;

// factory concreta pro servico de banho
public class BanhoFactory extends ServicoFactory {

    @Override
    public Servico criarServico() {
        return new Banho();
    }
}
