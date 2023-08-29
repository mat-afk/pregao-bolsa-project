import estruturasdedados.DynamicArray;

public class Investidor {

    private int codigo;
    private String nome;
    private double saldo;
    private DynamicArray<Acao> carteira;

    public Investidor(int codigo, String nome, double saldo) {
        this.codigo = codigo;
        this.nome = nome;
        this.saldo = saldo;
        this.carteira = new DynamicArray<>();
    }

    public Investidor(int codigo, String nome) {
        this(codigo, nome, 0.0);
    }

    public double calcularTotal() {
        double valorTotal = 0.0;
        for (Acao acao : carteira) {
            valorTotal += acao.calcularValor();
        }
        return valorTotal;
    }
}
