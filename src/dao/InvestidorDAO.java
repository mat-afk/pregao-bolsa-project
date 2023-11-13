package dao;

import entities.Corretora;
import entities.InvestidorFisico;
import estruturasdedados.LinkedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class InvestidorDAO {

    private static final String FILE_PATH = "database/investidores.txt";

    public static void save(@NotNull InvestidorFisico investidor) {

        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(investidor.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void update(@NotNull InvestidorFisico investidor) {
        LinkedList<String> linhas = new LinkedList<>();

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                InvestidorFisico investidorExistente = resultSetToInvestidor(linha);

                if (investidorExistente != null && investidorExistente.getId() == investidor.getId()) {
                    linhas.addLast(investidor.formatToSave());
                } else {
                    linhas.addLast(linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String linhaAtualizada : linhas) {
                arquivo.println(linhaAtualizada);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static @Nullable InvestidorFisico findById(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                InvestidorFisico investidor = resultSetToInvestidor(linha);
                if (investidor != null && investidor.getId() == id) {
                    return investidor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable InvestidorFisico findByName(String nome) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                InvestidorFisico investidor = resultSetToInvestidor(linha);
                if (investidor != null && investidor.getNome().equals(nome)) {
                    return investidor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable InvestidorFisico findByCpf(String cpf) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                InvestidorFisico investidor = resultSetToInvestidor(linha);
                if (investidor != null && investidor.getCpf().equals(cpf)) {
                    return investidor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable InvestidorFisico resultSetToInvestidor(@NotNull String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String nome = linha.substring(5, 35).trim();
            String cpf = linha.substring(35, 55).trim();
            String saldoStr = linha.substring(55, 70).trim();
            String corretoraNome = linha.substring(70, 100).trim();

            int investidorId = Integer.parseInt(idStr);
            double saldo = Double.parseDouble(saldoStr);

            Corretora corretora = CorretoraDAO.findByName(corretoraNome);

            return new InvestidorFisico(investidorId, nome, cpf, saldo, corretora);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
