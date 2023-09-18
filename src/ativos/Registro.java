package ativos;

import java.time.LocalDateTime;

public class Registro {
    private LocalDateTime data;
    private double preco;
    private int volume;

    public Registro(double preco, int volume) {
        this.data = LocalDateTime.now();
        this.preco = preco;
        this.volume = volume;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
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
