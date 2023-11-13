package main;

import entities.ativos.Ativo;
import entities.ativos.Ordinaria;
import entities.ativos.Preferencial;
import entities.Bolsa;
import entities.Corretora;
import entities.Empresa;
import entities.InvestidorFisico;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        resetarArquivos();

        Bolsa b3 = new Bolsa("B3");

        Empresa em1 = new Empresa("Google LLC", "GOOG", 2000000000.0, "Tecnologia");
        Empresa em2 = new Empresa("Petrobrás", "PETR", 1000000000.0, "Petróleo");
        Empresa em3 = new Empresa("Amazon", "AMZN", 30000000.0, "Tecnologia");

        b3.addEmpresa(em1);
        b3.addEmpresa(em2);
        b3.addEmpresa(em3);

        Corretora c1 = new Corretora("Rico", "58.272.630/0001-03");
        Corretora c2 = new Corretora("XP Investimentos", "20.064.474/0001-04");

        b3.addCorretora(c1);
        b3.addCorretora(c2);

        em1.setCorretora(c2);
        em2.setCorretora(c1);
        em3.setCorretora(c2);

        Ativo at1 = new Ordinaria(em1, 100.72);
        Ativo at2 = new Ordinaria(em2, 80.90);
        Ativo at3 = new Preferencial(em3, 157.34);

        em1.lancarAtivo(at1);
        em2.lancarAtivo(at2);
        em3.lancarAtivo(at3);



        InvestidorFisico in1 = new InvestidorFisico("Domingos Latorre", "004.572.878-02", 30000.0, c1);
        InvestidorFisico in2 = new InvestidorFisico("Ugo Henrique", "855.950.838-40", c2);

        c1.addCliente(in1);
        c2.addCliente(in2);
        in2.depositar(50000.0);

        in1.solicitarCompra(at2, 82.50, 2);

        b3.processarOrdens();
    }

    public static void resetarArquivos() {
        String[] filesPaths = {
                "database/ativos.txt",
                "database/empresas.txt",
                "database/corretoras.txt",
                "database/investidores.txt",
                "database/ordens.txt",
                "database/registros.txt"
        };

        try {
            for (String filesPath : filesPaths) {
                Path path = Path.of(filesPath);
                if (Files.exists(path)) {
                    FileWriter fileWriter = new FileWriter(filesPath);

                    try (PrintWriter writer = new PrintWriter(fileWriter)) {
                        writer.print("");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
