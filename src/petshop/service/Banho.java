package petshop.service;

// servico concreto de banho
public class Banho implements Servico {

    @Override
    public String getDescricao() {
        return "Banho";
    }

    @Override
    public double getPreco() {
        return 50.0;
    }
}
