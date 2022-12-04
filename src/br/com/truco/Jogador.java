package br.com.truco;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jogador {
    private String nome;
    private List<Carta> cartas = new ArrayList<>();
    private int pontos = 0;

    private int rodadasGanhas;

    private boolean ehMinhaVez;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
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

    public boolean gritarTruco(){
        System.out.format("Trucar %s? s : n", this.nome);
        Scanner sc = new Scanner(System.in);
        String trucou = sc.nextLine();

        return trucou.equals("s");
    }

    public void exibirCartasJogador(){
        System.out.println("");
        for (Carta carta : this.cartas){
            System.out.print(carta.getValor() + " " + carta.getNaipe() + "   ");
        }
        System.out.println("");
    }
}
