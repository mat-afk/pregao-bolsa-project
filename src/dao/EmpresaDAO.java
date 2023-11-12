package dao;

import entities.Empresa;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class EmpresaDAO {

    private static final String FILE_PATH = "database/empresas.txt";

    public static void save(@NotNull Empresa empresa) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(empresa.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static @Nullable Empresa findById(int id) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Empresa empresa = resultSetToEmpresa(linha);
                if (empresa != null && empresa.getId() == id) {
                    return empresa;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Empresa findByName(String nome) {

        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Empresa empresa = resultSetToEmpresa(linha);
                if (empresa != null && empresa.getNome().equals(nome)) {
                    return empresa;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Empresa resultSetToEmpresa(@NotNull String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String nome = linha.substring(5, 35).trim();
            String simbolo = linha.substring(35, 45).trim();
            String capitalizacaoStr = linha.substring(45, 60).trim();
            String setor = linha.substring(60, 80).trim();

            int empresaId = Integer.parseInt(idStr);
            double capitalizacao = Double.parseDouble(capitalizacaoStr);

            return new Empresa(empresaId, nome, simbolo, capitalizacao, setor);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

}
