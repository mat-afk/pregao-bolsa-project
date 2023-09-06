public class Local implements verificadorCotas{
    private String endereco;
    private String nome;
    private int cotas;

    public Local(String endereco, String nome, int cotas){
        this.endereco = endereco;
        this.nome = nome;
        this.cotas = cotas;
    }

    @Override
    public void comprarCotas(int cotas){
        if(this.cotas == 0 || this.cotas < cotas){
           throw new RuntimeException("o numeros de cotas compradas ecede o limite da empresa");
        } else {
            this.cotas -= cotas;
        }
    }

    public String getNome(){
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    
}
