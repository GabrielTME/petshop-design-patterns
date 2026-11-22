package petshop.service;

// servico concreto de tosa
public class Tosa implements Servico {

    @Override
    public String getDescricao() {
        return "Tosa";
    }

    @Override
    public double getPreco() {
        return 70.0;
    }
}
