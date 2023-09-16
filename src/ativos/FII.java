package ativos;

public class FII extends Ativo {

    public FII(int id, String nome, String codigo, double cotacao) {
        super(id, nome, codigo, cotacao);
    }

    @Override
    public double calcularValor() {
        return getCotacao();
    }
}
