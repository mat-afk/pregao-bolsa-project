package main;

import algoritmosdeordenacao.ShellSort;
import algoritmosdeordenacao.Sorting;
import dao.*;
import entities.*;
import entities.ativos.Ativo;
import entities.ativos.Ordinaria;
import entities.ativos.Preferencial;
import entities.ordens.Ordem;
import estruturasdedados.LinkedList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        resetarArquivos();

        Bolsa b3 = new Bolsa("B3");

        Empresa em1 = new Empresa("Google LLC", "17.821.897/0001-17", "GOOG", 2000000000.0, "Tecnologia", b3);
        Empresa em2 = new Empresa("Petrobrás", "33.018.580/0001-01", "PETR", 1000000000.0, "Petróleo", b3);
        Empresa em3 = new Empresa("Amazon", "66.641.454/0001-50", "AMZN", 30000000.0, "Tecnologia", b3);

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


        InvestidorFisico in1 = new InvestidorFisico("Domingos Latorre", "004.572.878-02", 30000.0, c1, b3);
        InvestidorFisico in2 = new InvestidorFisico("Ugo Henrique", "855.950.838-40", c2, b3);

        c1.addInvestidorFisico(in1);
        c2.addInvestidorFisico(in2);
        in2.depositar(50000.0);

        in1.solicitarCompra(at2, 82.50, 2);

        Sorting<Empresa> empresaSorting = new ShellSort<>();
        Empresa[] empresas = new Empresa[b3.getEmpresas().size()];
        for(int i = 0; i < EmpresaDAO.findAll().size(); i++) {
            empresas[i] = EmpresaDAO.findAll().get(i);
        }
        empresaSorting.sort(empresas);

        Scanner scan = new Scanner(System.in);
        int op;
        Investidor usuario = null;

        do {
            b3.processarOrdens();

            System.out.println("----------------------------------------------------------");
            System.out.println("                    Olá, investidor!");
            System.out.println("                Bem-vindo ao pregão da B3");
            System.out.println("----------------------------------------------------------");
            System.out.println("\nEscolha uma opção para continuar:");
            System.out.println("[1] Cadastrar-se");
            System.out.println("[2] Entrar");
            System.out.println("[3] Listar empresas");
            System.out.println("[4] Listar ações");
            System.out.println("[5] Listar transações por ativo");
            System.out.println("[6] Comprar ação");
            System.out.println("[7] Vender ação");
            System.out.println("[8] Ver minha carteira");
            System.out.println("[9] Ver minhas ordens");
            System.out.println("[10] Encerrar");
            op = scan.nextInt();

            System.out.println("----------------------------------------------------------\n");

            switch(op) {
                case 1 -> {
                    System.out.println("Você é um investidor físico ou jurídico?");
                    System.out.println("[1] Físico");
                    System.out.println("[2] Jurídico");
                    int op1 = scan.nextInt();

                    switch(op1) {
                        case 1 -> {

                            scan.nextLine();

                            System.out.println("Qual é o seu nome?");
                            String nome = scan.nextLine();

                            System.out.println("Qual é seu CPF?");
                            String cpf = scan.nextLine();

                            System.out.println("Qual seu valor inicial de saldo?");
                            double saldo = scan.nextDouble();

                            System.out.println("Escolha uma corretora:");
                            for(Corretora corretora : Objects.requireNonNull(CorretoraDAO.findAll())) {
                                System.out.println("[" + corretora.getId() + "] " + corretora.getNome());
                            }
                            Corretora corretora = CorretoraDAO.findById(scan.nextInt());

                            usuario = new InvestidorFisico(nome, cpf, saldo, corretora, b3);

                            assert corretora != null;
                            corretora.addInvestidorFisico((InvestidorFisico) usuario);
                        }

                        case 2 -> {

                            System.out.println("Qual é o nome da sua empresa");
                            String nome = scan.nextLine();

                            System.out.println("Qual é o CNPJ da sua empresa?");
                            String cnpj = scan.nextLine();

                            System.out.println("Qual será o seu símbolo na bolsa?");
                            String simbolo = scan.nextLine();

                            System.out.println("Qual é a capitalização dela?");
                            double capitalizacao = scan.nextDouble();

                            System.out.println("Em qual setor sua empresa atua?");
                            String setor = scan.nextLine();

                            System.out.println("Escolha uma corretora:");
                            LinkedList<Corretora> corretoras = Objects.requireNonNull(CorretoraDAO.findAll());
                            for(int i = 0; i < corretoras.size(); i++) {
                                System.out.println("[" + i + "] " + corretoras.get(i).getNome());
                            }
                            Corretora corretora = CorretoraDAO.findById(scan.nextInt());

                            usuario = new Empresa(nome, cnpj, simbolo, capitalizacao, setor, corretora, b3);

                            assert corretora != null;
                            corretora.addIEmpresa((Empresa) usuario);
                        }
                    }
                }

                case 2 -> {
                    System.out.println("Você é um investidor físico ou jurídico?");
                    System.out.println("[1] Físico");
                    System.out.println("[2] Jurídico");
                    int op2 = scan.nextInt();

                    switch(op2) {
                        case 1 -> {
                            scan.nextLine();

                            System.out.println("Qual é o seu nome?");
                            String nome = scan.nextLine();

                            if(InvestidorDAO.findByName(nome) != null) {
                                usuario = InvestidorDAO.findByName(nome);
                            } else {
                                System.out.println("Usuário não cadastrado.");
                            }
                        }

                        case 2 -> {
                            scan.nextLine();

                            System.out.println("Qual é o nome da sua empresa?");
                            String nome = scan.nextLine();

                            if(EmpresaDAO.findByName(nome) != null) {
                                usuario = EmpresaDAO.findByName(nome);
                            } else {
                                System.out.println("Usuário não cadastrado.");
                            }
                        }
                    }
                }

                case 3 -> {
                    for(int i = 0; i < empresas.length; i++) {
                        System.out.println("[" + i + "] " + empresas[i].getNome());
                    }
                    scan.nextLine();
                    scan.nextLine();
                }

                case 4 -> {
                    for(Ativo ativo : Objects.requireNonNull(AtivoDAO.findAll())) {
                        System.out.println(ativo);
                    }
                    scan.nextLine();
                    scan.nextLine();
                }

                case 5 -> {
                    System.out.println("Qual ativo deseja ver o histórico de registros?");
                    for(Ativo ativo : Objects.requireNonNull(AtivoDAO.findAll())) {
                        System.out.println("[" + ativo.getId() + "] " + ativo.getSimbolo());
                    }
                    int ativoId = scan.nextInt();
                    Ativo selectedAtivo = AtivoDAO.findById(ativoId);
                    if (selectedAtivo != null) {
                        LinkedList<Registro> registros = RegistroDAO.findByAtivo(selectedAtivo.getSimbolo());
                        if(registros.isEmpty()) {
                            System.out.println("Essa ação não possui registros de transações");
                        } else {
                            for(Registro registro : registros) {
                                System.out.println("[" + registro.getId() + "] " +
                                        "\tativo: " + registro.getAtivo().getSimbolo() +
                                        "\tpreço negociado: R$" + registro.getPrecoNegociado() +
                                        "\tdata: " + registro.getData());
                            }
                        }
                    } else {
                        System.out.println("Ativo não encontrado.");
                    }

                    scan.nextLine();
                    scan.nextLine();
                }

                case 6 -> {
                    System.out.println("Qual ação você deseja comprar?");
                    LinkedList<Ativo> ativos = Objects.requireNonNull(AtivoDAO.findAll());
                    for(int i = 0; i < ativos.size(); i++) {
                        System.out.println("[" + ativos.get(i).getId() + "] " + ativos.get(i).getSimbolo() + "\t cotação: " + ativos.get(i).getCotacao());
                    }
                    int ativoId = scan.nextInt();

                    System.out.println("Deseja comprá-la por qual preço?");
                    double preco = scan.nextDouble();

                    System.out.println("Quantos lotes deseja comprar?");
                    int lote = scan.nextInt();

                    assert usuario != null;
                    usuario.solicitarCompra(AtivoDAO.findById(ativoId), preco, lote);
                }

                case 7 -> {
                    System.out.println("Qual ação você deseja vender?");
                    assert usuario != null;
                    for(Ativo ativo : usuario.getCarteira().getAtivos()) {
                        System.out.println("[" + ativo.getId() + "] " + ativo.getSimbolo());
                    }
                    int ativoId = scan.nextInt();

                    System.out.println("Deseja vendê-la por qual preço?");
                    double preco = scan.nextDouble();

                    System.out.println("Quantos lotes deseja vender?");
                    int lote = scan.nextInt();

                    usuario.solicitarVenda(AtivoDAO.findById(ativoId), preco, lote);
                }

                case 8 -> {
                    assert usuario != null;
                    LinkedList<Ativo> carteira = usuario.getCarteira().getAtivos();
                    if(carteira.isEmpty()) {
                        System.out.println("Você ainda não possui ações na sua carteira.");
                    } else {
                        System.out.println("Sua carteira ----------------------------------------------\n");
                        for(Ativo ativo : carteira) {
                            System.out.println("[" + ativo.getId() + "] " + ativo.getSimbolo() +
                                    "\tempresa: " + ativo.getEmpresa().getNome() +
                                    "\tcotação: " + ativo.getCotacao() +
                                    "\tnatureza: " + ativo.getNatureza());
                        }
                    }
                    scan.nextLine();
                    scan.nextLine();
                }

                case 9 -> {
                    LinkedList<Ordem> ordens = OrdemDAO.findAll();

                    if (ordens.isEmpty()) {
                        System.out.println("Você ainda não realizou nenhuma ordem de compra ou venda");
                    } else {
                        System.out.println("Suas ordens ----------------------------------------------\n");
                        for (Ordem ordem : ordens) {
                            Investidor ordemInvestidor = ordem.getInvestidor();
                            assert usuario != null;

                            if (ordemInvestidor != null && ordemInvestidor.getId() == usuario.getId()) {
                                System.out.println("[" + ordem.getId() + "] " + ordem.getAtivo().getSimbolo() + "\tpreço: " + ordem.getPreco() + "\ttipo: " + ordem.getTipo() +
                                        "\tlotes: " + ordem.getQuantidade() + "\t\tdata de emissão: " + ordem.getDataEmissao());
                            }
                        }
                    }
                    scan.nextLine();
                    scan.nextLine();
                }
            }

        } while(op < 10);
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
