package petshop.factory;

import petshop.service.Servico;
import petshop.service.Tosa;

// factory concreta pro servico de tosa
public class TosaFactory extends ServicoFactory {

    @Override
    public Servico criarServico() {
        return new Tosa();
    }
}
