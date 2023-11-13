package dao;

import entities.Registro;
import entities.ativos.Ativo;
import entities.ordens.Ordem;
import entities.ordens.OrdemCompra;
import entities.ordens.OrdemVenda;
import entities.ordens.TipoOrdem;
import estruturasdedados.LinkedList;

import java.io.*;
import java.time.LocalDateTime;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistroDAO {

    private static final String FILE_PATH = "database/registros.txt";

    public static void save(Registro registro) {
        try (PrintWriter arquivo = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            arquivo.println(registro.formatToSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Registro findById(int id) {
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Registro registro = resultSetToRegistro(linha);
                if (registro != null && registro.getId() == id) {
                    return registro;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static LinkedList<Registro> findByAtivo(String simboloAtivo) {
        LinkedList<Registro> registrosAtivo = new LinkedList<>();
        try (BufferedReader arquivo = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = arquivo.readLine()) != null) {
                Registro registro = resultSetToRegistro(linha);
                if (registro != null && registro.getAtivo().getSimbolo().equals(simboloAtivo)) {
                    registrosAtivo.addLast(registro);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return registrosAtivo;
    }

    public static Registro resultSetToRegistro(String linha) {
        try {
            String idStr = linha.substring(0, 5).trim();
            String idOrdemVendaStr = linha.substring(5, 10).trim();
            String idOrdemCompraStr = linha.substring(10, 15).trim();
            String dataStr = linha.substring(15, 45).trim();
            String simboloAtivo = linha.substring(45, 55).trim();
            String precoNegociadoStr = linha.substring(55, 70).trim();
            String volumeStr = linha.substring(70, 77).trim();

            int registroId = Integer.parseInt(idStr);
            int idOrdemVenda = Integer.parseInt(idOrdemVendaStr);
            int idOrdemCompra = Integer.parseInt(idOrdemCompraStr);
            LocalDateTime data = LocalDateTime.parse(dataStr);
            Ativo ativo = AtivoDAO.findBySimbolo(simboloAtivo);
            double precoNegociado = Double.parseDouble(precoNegociadoStr);
            int volume = Integer.parseInt(volumeStr);

            OrdemVenda ordemVenda = (idOrdemVenda != 0) ? (OrdemVenda) OrdemDAO.findById(idOrdemVenda) : null;
            OrdemCompra ordemCompra = (idOrdemCompra != 0) ? (OrdemCompra) OrdemDAO.findById(idOrdemCompra) : null;

            return new Registro(registroId, data, ativo, ordemVenda, ordemCompra, precoNegociado, volume);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
