import ativos.Ativo;

public class Investidor {

    private final int id;
    private final String nome;
    private String cpf;
    private double saldo;
    private Carteira carteira;

    public Investidor(int id, String nome, String cpf, double valor) {
        this.id = id;
        this.nome = nome;
        setCpf(cpf);
        this.saldo = valor;
        this.carteira = new Carteira();
    }

    public Investidor(int id, String nome, String cpf) {
        this(id, nome, cpf, 0.0);
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void comprarAcao(Ativo acao){
        if(acao.calcularValor() > saldo){
            throw new RuntimeException("Seu saldo é insuficiente para a compra desta ação.");
        }
        saldo -= acao.calcularValor();
        carteira.addAtivo(acao);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        if(!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$") && !Validador.validarCPF(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
}
