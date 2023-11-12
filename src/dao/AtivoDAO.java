package dao;

import entities.Empresa;
import entities.ativos.*;
import estruturasdedados.LinkedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;

public class AtivoDAO {

    private static final String FILE_PATH = "database/ativos.txt";

    public static void save(@NotNull Ativo ativo) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(ativo.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static @Nullable Ativo findById(int id) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Ativo ativo = resultSetToAtivo(linha);
                if (ativo != null && ativo.getId() == id) {
                    return ativo;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Ativo findBySimbolo(String simbolo) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while((linha = arquivo.readLine()) != null) {
                Ativo ativo = resultSetToAtivo(linha);
                if (ativo != null && ativo.getSimbolo().equals(simbolo)) {
                    return ativo;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable LinkedList<Ativo> findAll() {
        LinkedList<Ativo> ativos = new LinkedList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Ativo ativo = resultSetToAtivo(linha);
                if(ativo != null) {
                    ativos.addLast(ativo);
                }
            }

            return ativos;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Ativo resultSetToAtivo(@NotNull String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String codigo = linha.substring(5, 15).trim();
            String nomeEmpresa = linha.substring(15, 45).trim();
            String cotacaoStr = linha.substring(45, 60).trim();
            String naturezaStr = linha.substring(60, 75).trim();

            int ativoId = Integer.parseInt(idStr);
            double cotacao = Double.parseDouble(cotacaoStr);
            Empresa empresa = EmpresaDAO.findByName(nomeEmpresa);

            Natureza natureza = Natureza.valueOf(naturezaStr);

            switch(natureza) {
                case ORDINARIA -> {
                    return new Ordinaria(ativoId, codigo, empresa, cotacao);
                }
                case PREFERENCIAL -> {
                    return new Preferencial(ativoId, codigo, empresa, cotacao);
                }
                case FII -> {
                    return new FII(ativoId, codigo, empresa, cotacao);
                }
            }

            return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
