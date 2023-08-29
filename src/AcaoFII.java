public class AcaoFII extends Acao {

    private int cotas;

    public AcaoFII(String nome, double preco, int cotas) {
        super(nome, preco);
        this.cotas = cotas;
    }

    @Override
    public double calcularValor() {
        return getPreco() * cotas;
    }
}
