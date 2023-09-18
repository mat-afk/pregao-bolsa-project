package main;

import ativos.Ativo;
import ativos.Ordinaria;
import ativos.Preferencial;
import entities.Bolsa;
import entities.Corretora;
import entities.Empresa;
import entities.Investidor;

public class Main {

    public static void main(String[] args) {

        Bolsa b3 = new Bolsa("B3");

        Empresa em1 = new Empresa(1, "Google LLC", "GOOG", 2000000000.0, "Tecnologia");
        Empresa em2 = new Empresa(2, "Petrobrás", "PETR", 1000000000.0, "Petróleo");
        Empresa em3 = new Empresa(3, "Amazon", "AMZN", 30000000.0, "Tecnologia");

        b3.addEmpresa(em1);
        b3.addEmpresa(em2);
        b3.addEmpresa(em3);

        Ativo at1 = new Ordinaria(1, "GOOG3", em1, 100.72);
        Ativo at2 = new Ordinaria(2, "PETR3", em2, 80.90);
        Ativo at3 = new Preferencial(3, "AMZN4", em3, 157.34);

        Corretora c1 = new Corretora(1, "Rico", "58.272.630/0001-03");
        Corretora c2 = new Corretora(2, "XP Investimentos", "20.064.474/0001-04");

        Investidor in1 = new Investidor(1, "Domingos Latorre", "004.572.878-02", 30000.0, c1);
        Investidor in2 = new Investidor(2, "Ugo Henrique", "855.950.838-40", c2);
        in2.depositar(50000.0);

    }
}
