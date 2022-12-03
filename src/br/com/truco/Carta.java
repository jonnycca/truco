package br.com.truco;

import java.util.Objects;

public class Carta {

    private Integer valor;
    private Naipe naipe;

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return Objects.equals(valor, carta.valor) && naipe == carta.naipe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, naipe);
    }
}
