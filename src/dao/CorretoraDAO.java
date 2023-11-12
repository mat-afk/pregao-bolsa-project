package dao;

import entities.Corretora;
import estruturasdedados.LinkedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class CorretoraDAO {

    private static final String FILE_PATH = "database/corretoras.txt";

    public static void save(@NotNull Corretora corretora) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(corretora.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static @Nullable Corretora findById(int id) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Corretora corretora = resultSetToCorretora(linha);
                if (corretora != null && corretora.getId() == id) {
                    return corretora;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Corretora findByName(String nome) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Corretora corretora = resultSetToCorretora(linha);
                if (corretora != null && corretora.getNome().equals(nome)) {
                    return corretora;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Corretora findByCnpj(String cnpj) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Corretora corretora = resultSetToCorretora(linha);
                if (corretora != null && corretora.getCnpj().equals(cnpj)) {
                    return corretora;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable LinkedList<Corretora> findAll() {
        LinkedList<Corretora> corretoras = new LinkedList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Corretora corretora = resultSetToCorretora(linha);
                if (corretora != null) {
                    corretoras.addLast(corretora);
                }
            }

            return corretoras;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Corretora resultSetToCorretora(@NotNull String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String nome = linha.substring(5, 35).trim();
            String cnpj = linha.substring(35, 50).trim();

            int corretoraId = Integer.parseInt(idStr);

            return new Corretora(corretoraId, nome, cnpj);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}

