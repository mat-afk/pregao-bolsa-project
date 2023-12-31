package main;

public class Validador {

    public static boolean validarCodigo(String codigo) {

        /*
        XXXX = 04 letras maiúsculas que representam o nome do emissor
        Y = 01 número que representa o tipo da ação, adotado 3 para ordinária; 4 para preferencial; 5, 6, 7, 8 para preferenciais classes A, B, C e D, respectivamente
        */

        return codigo.matches("^[A-Z]{4}[345678]$");
    }

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int num = Character.getNumericValue(cpf.charAt(i));
            soma += num * (10 - i);
        }
        int resto = soma % 11;
        int primeiroDigito = (resto < 2) ? 0 : (11 - resto);

        soma = 0;
        for (int i = 0; i < 10; i++) {
            int num = Character.getNumericValue(cpf.charAt(i));
            soma += num * (11 - i);
        }
        resto = soma % 11;
        int segundoDigito = (resto < 2) ? 0 : (11 - resto);


        return (primeiroDigito == Character.getNumericValue(cpf.charAt(9))
                && segundoDigito == Character.getNumericValue(cpf.charAt(10)));
    }
    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        if (cnpj.length() != 14) {
            return true;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return true;
        }

        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            int num = Character.getNumericValue(cnpj.charAt(i));
            soma += num * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        int resto = soma % 11;
        int primeiroDigito = (resto < 2) ? 0 : (11 - resto);

        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            int num = Character.getNumericValue(cnpj.charAt(i));
            soma += num * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }
        resto = soma % 11;
        int segundoDigito = (resto < 2) ? 0 : (11 - resto);

        return (primeiroDigito != Character.getNumericValue(cnpj.charAt(12))
                || segundoDigito != Character.getNumericValue(cnpj.charAt(13)));
    }
}
