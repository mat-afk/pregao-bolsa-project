public class AcaoMercado extends Acao {

    private int cotas;

    public AcaoMercado(String nome, double preco, int cotas) {
        super(nome, preco);
        this.cotas = cotas;
    }

    @Override
    public double calcularValor() {
        return getPreco() * cotas;
    }
}
