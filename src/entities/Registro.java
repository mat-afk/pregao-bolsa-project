package entities;

import entities.ativos.Ativo;
import entities.ordens.Ordem;
import entities.ordens.OrdemCompra;
import entities.ordens.OrdemVenda;

import java.time.LocalDateTime;

public class Registro {

    private static int count = 0;
    private final int id;
    private LocalDateTime data;
    private final Ativo ativo;
    private final OrdemVenda ordemVenda;
    private final OrdemCompra ordemCompra;
    private double precoNegociado;
    private int volume;

    public Registro(int id, LocalDateTime data, Ativo ativo, OrdemVenda ordemVenda, OrdemCompra ordemCompra, double precoNegociado, int volume) {
        this.id = id;
        this.data = data;
        this.ativo = ativo;
        this.ordemVenda = ordemVenda;
        this.ordemCompra = ordemCompra;
        this.precoNegociado = precoNegociado;
        this.volume = volume;
    }

    public Registro(Ativo ativo, OrdemVenda ordemVenda, OrdemCompra ordemCompra, double precoNegociado, int volume) {
        this(++count, LocalDateTime.now(), ativo, ordemVenda, ordemCompra, precoNegociado, volume);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public OrdemVenda getOrdemVenda() {
        return ordemVenda;
    }

    public OrdemCompra getOrdemCompra() {
        return ordemCompra;
    }

    public double getPrecoNegociado() {
        return precoNegociado;
    }

    public void setPrecoNegociado(double precoNegociado) {
        this.precoNegociado = precoNegociado;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String formatToSave() {
        return String.format("%-5s%-5s%-5s%-30s%-10s%-15s%-7s",
                getId(), getOrdemVenda().getId(), ordemCompra.getId(), getData(), getAtivo().getSimbolo(), getPrecoNegociado(), getVolume());
    }
}
