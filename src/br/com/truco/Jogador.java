package br.com.truco;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Jogador {
    private String nome;
    private List<Carta> cartas = new ArrayList<>();
    private int pontos = 0;

    private int rodadasGanhas;

    private boolean ehMinhaVez;

    private boolean maoRodada;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getRodadasGanhas() {
        return rodadasGanhas;
    }

    public void setRodadasGanhas(int rodadasGanhas) {
        this.rodadasGanhas = rodadasGanhas;
    }

    public boolean isEhMinhaVez() {
        return ehMinhaVez;
    }

    public void setEhMinhaVez(boolean ehMinhaVez) {
        this.ehMinhaVez = ehMinhaVez;
    }

    public boolean isMaoRodada() {
        return maoRodada;
    }

    public void setMaoRodada(boolean maoRodada) {
        this.maoRodada = maoRodada;
    }


    public boolean gritarTruco(){
        System.out.format("Trucar %s? s : n  ", this.nome);
        Scanner sc = new Scanner(System.in);
        String trucou = sc.nextLine();

        while (!trucou.equals("s") && !trucou.equals("n")){
            System.out.println("Opcao invalida! Digite novamente!");
            trucou = sc.nextLine();
        }
        return trucou.equals("s");
    }

    public void exibirCartasJogador(){
        System.out.println("");
        for (Carta carta : this.cartas){
            System.out.print(carta.getValor() + " " + carta.getNaipe() + "   ");
        }
        System.out.println("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return pontos == jogador.pontos && rodadasGanhas == jogador.rodadasGanhas && ehMinhaVez == jogador.ehMinhaVez && maoRodada == jogador.maoRodada && Objects.equals(nome, jogador.nome) && Objects.equals(cartas, jogador.cartas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, cartas, pontos, rodadasGanhas, ehMinhaVez, maoRodada);
    }
}
