package br.com.truco;

public enum Naipe {
    OURO(1),
    ESPADA(2),
    COPAS(3),
    PAUS(4);

    public int valorNaipe;
    Naipe(int valor) {
        valorNaipe = valor;
    }
}
