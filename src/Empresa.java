public class Empresa{

    private String nome;
    private int cotas;

    public Empresa(String nome, int cotas){
        this.nome = nome;
        if (cotas <= 0){
            throw new RuntimeException("O numero de cotas da empresa não pode ser abaixo de 1");
        } else {
        this.cotas = cotas;
        }
    }

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
}
