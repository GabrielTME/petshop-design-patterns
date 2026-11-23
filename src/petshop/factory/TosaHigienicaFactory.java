package petshop.factory;

import petshop.service.Servico;
import petshop.service.TosaHigienica;

// factory concreta pro servico de tosa higienica
public class TosaHigienicaFactory extends ServicoFactory {

    @Override
    public Servico criarServico() {
        return new TosaHigienica();
    }
}
