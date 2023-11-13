package dao;

import entities.InvestidorFisico;
import entities.ativos.Ativo;
import entities.ordens.Ordem;
import entities.ordens.OrdemCompra;
import entities.ordens.OrdemVenda;
import entities.ordens.TipoOrdem;
import estruturasdedados.LinkedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.time.LocalDateTime;

public class OrdemDAO {

    private static final String FILE_PATH = "database/ordens.txt";

    public static void save(@NotNull Ordem ordem) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(ordem.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static @
            Nullable Ordem findById(int id) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Ordem ordem = resultSetToOrdem(linha);
                if (ordem != null && ordem.getId() == id) {
                    return ordem;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable LinkedList<Ordem> findByInvestidor(InvestidorFisico investidor) {
        LinkedList<Ordem> ordens = new LinkedList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Ordem ordem = resultSetToOrdem(linha);
                if (ordem != null && ordem.getInvestidor().equals(investidor)) {
                    ordens.addLast(ordem);
                }
            }
            return ordens;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable LinkedList<Ordem> findByTipo(TipoOrdem tipo) {
        LinkedList<Ordem> ordens = new LinkedList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Ordem ordem = resultSetToOrdem(linha);
                if (ordem != null && ordem.getTipo().equals(tipo)) {
                    ordens.addLast(ordem);
                }
            }
            return ordens;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable LinkedList<Ordem> findByAtivo(String simboloAtivo) {
        LinkedList<Ordem> ordensAtivo = new LinkedList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Ordem ordem = resultSetToOrdem(linha);
                if (ordem != null && ordem.getAtivo().getSimbolo().equals(simboloAtivo)) {
                    ordensAtivo.addLast(ordem);
                }
            }

            return ordensAtivo;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static @Nullable Ordem resultSetToOrdem(@NotNull String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String simbolo = linha.substring(5, 15).trim();
            String nomeInvestidor = linha.substring(15, 45).trim();
            String cpfInvestidor = linha.substring(45, 60).trim();
            String precoStr = linha.substring(60, 75).trim();
            String quantidadeStr = linha.substring(75, 80).trim();
            String tipoStr = linha.substring(80, 90).trim();
            String dataEmissao = linha.substring(90, 110).trim();

            int ordemId = Integer.parseInt(idStr);
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(quantidadeStr);
            InvestidorFisico investidor = InvestidorDAO.findByCpf(cpfInvestidor);

            Ativo ativo = AtivoDAO.findBySimbolo(simbolo);

            LocalDateTime data = LocalDateTime.parse(dataEmissao);

            TipoOrdem tipo = TipoOrdem.valueOf(tipoStr);

            switch(tipo) {
                case COMPRA -> {
                    return new OrdemCompra(ordemId, ativo, investidor, preco, quantidade, data);
                }
                case VENDA -> {
                    return new OrdemVenda(ordemId, ativo, investidor, preco, quantidade, data);
                }
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
}

