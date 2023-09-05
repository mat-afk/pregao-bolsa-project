public class AcaoMercado extends Acao {

    private int cotas;
    private Empresa empresa;

    public AcaoMercado(String nome, double preco, int cotas, Empresa empresa) {
        super(nome, preco);
        this.cotas = cotas;
        this.empresa = empresa;
    }

    @Override
    public double calcularValor() {
        return getPreco() * cotas;
    }
}
