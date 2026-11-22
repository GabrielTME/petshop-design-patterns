package petshop.service;

// interface comum pros servicos, usada tanto no factory method quanto no decorator
public interface Servico {
    String getDescricao();
    double getPreco();
}
