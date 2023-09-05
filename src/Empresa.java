public class Empresa {

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

    private boolean verificarCotas(int cotas){
        if(this.cotas == 0 || this.cotas < cotas){
            return false;
        } else {
            return true;
        }
    }

    public void comprarCotas(int cotas){
        boolean verificador = verificarCotas(cotas);
        if(verificador = true){
            this.cotas -= cotas;
        } else {
            throw new RuntimeException("o numeros de cotas compradas ecede o limite da empresa");
        }
    }

    public String getNome(){
        return nome;
    }
}
