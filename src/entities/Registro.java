package entities;

import entities.ordens.Ordem;

import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime data;
    private final Ordem ordem;
    private double preco;
    private int volume;

    public Registro(Ordem ordem, double preco, int volume) {
        this.data = LocalDateTime.now();
        this.ordem = ordem;
        this.preco = preco;
        this.volume = volume;
    }

    public Registro(LocalDateTime data, Ordem ordem, double preco, int volume) {
        this.data = data;
        this.ordem = ordem;
        this.preco = preco;
        this.volume = volume;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
