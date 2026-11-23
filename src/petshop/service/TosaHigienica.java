package petshop.service;

// servico concreto de tosa higienica (regioes intimas, patas e barriga)
public class TosaHigienica implements Servico {

    @Override
    public String getDescricao() {
        return "Tosa Higienica";
    }

    @Override
    public double getPreco() {
        return 45.0;
    }
}
