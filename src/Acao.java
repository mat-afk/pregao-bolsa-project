public abstract class Acao {

    private String nome;
    private double preco;

    public Acao(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public abstract double calcularValor();

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
