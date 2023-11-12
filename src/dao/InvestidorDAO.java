package dao;

import entities.Corretora;
import entities.Investidor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class InvestidorDAO {

    private static final String FILE_PATH = "database/investidores.txt";

    public static void save(@NotNull Investidor investidor) {

        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(investidor.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static @Nullable Investidor findById(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Investidor investidor = resultSetToInvestidor(linha);
                if (investidor != null && investidor.getId() == id) {
                    return investidor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Investidor findByName(String nome) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Investidor investidor = resultSetToInvestidor(linha);
                if (investidor != null && investidor.getNome().equals(nome)) {
                    return investidor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Investidor findByCpf(String cpf) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Investidor investidor = resultSetToInvestidor(linha);
                if (investidor != null && investidor.getCpf().equals(cpf)) {
                    return investidor;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Investidor resultSetToInvestidor(@NotNull String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String nome = linha.substring(5, 35).trim();
            String cpf = linha.substring(35, 50).trim();
            String saldoStr = linha.substring(50, 65).trim();
            String corretoraNome = linha.substring(65, 95).trim();

            int investidorId = Integer.parseInt(idStr);
            double saldo = Double.parseDouble(saldoStr);

            Corretora corretora = CorretoraDAO.findByName(corretoraNome);

            return new Investidor(investidorId, nome, cpf, saldo, corretora);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
