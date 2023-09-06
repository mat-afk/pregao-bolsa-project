public class AcaoFII extends Acao {

    private int cotas;
    private Local local;

    public AcaoFII(String nome, double preco, int cotas, Local local) {
        super(nome, preco);
        this.cotas = cotas;
        this.local = local;
    }

    @Override
    public double calcularValor() {
        return getPreco() * cotas;
    }

    public Local getLocal(){
        return local;
    }
}
