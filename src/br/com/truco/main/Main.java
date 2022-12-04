package br.com.truco.main;

import br.com.truco.Baralho;
import br.com.truco.Carta;
import br.com.truco.Jogador;
import br.com.truco.Rodada;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static Rodada rodada = new Rodada();

    private static Baralho baralho = new Baralho();
    private static Jogador jogador1 = new Jogador();
    private static Jogador jogador2 = new Jogador();

    private static Carta carta1 = new Carta();
    private static Carta carta2 = new Carta();



    public static void main(String[] args) {

        //receber nome dos jogadores
        System.out.print("Digite o nome do primeiro jogador:");
        jogador1.setNome(sc.nextLine());
        System.out.print("Digite o nome do segundo jogador:");
        jogador2.setNome(sc.nextLine());




        while (jogador1.getPontos() < 12 && jogador2.getPontos() < 12){
            baralho.montarBaralho();
            limparCartasJogadores();
            prepararCartasJogador(jogador1, baralho);
            prepararCartasJogador(jogador2, baralho);
            rodada.setCartasJogadas(0);//verificar se remove daqui, ja tem no metodo veriicar quem ganhou a mao

            System.out.println(jogador1.getNome() + ": "+ jogador1.getPontos()+"                       "+jogador2.getNome() + ": "+ jogador2.getPontos());

            while (jogador1.getRodadasGanhas()< 2 && jogador2.getRodadasGanhas()< 2) { // verificar se der empate pq vai foder tudo
                while (rodada.getCartasJogadas() != 2) {
                    if (jogador2.isEhMinhaVez()) {
                        exibirCartasJogador(jogador2);
                        //pedir truco
                        rodada.verificarSeGritouTruco(jogador2.gritarTruco(), jogador2);
                        //adiversario deve decidir sobre o truco
                        System.out.format("Escolha uma carta %s:", jogador2.getNome());
                        exibirCartasJogador(jogador2);
                        carta2 = jogarUmaCarta(jogador2, sc.nextInt());
                        jogador2.setEhMinhaVez(false);
                        jogador1.setEhMinhaVez(true);
                    } else {
                        exibirCartasJogador(jogador1);
                        rodada.verificarSeGritouTruco(jogador1.gritarTruco(), jogador1);
                        System.out.format("Escolha uma carta %s:", jogador1.getNome());
                        exibirCartasJogador(jogador1);
                        carta1 = jogarUmaCarta(jogador1, sc.nextInt());
                        jogador1.setEhMinhaVez(false);
                        jogador2.setEhMinhaVez(true);
                    }
                    rodada.setCartasJogadas(rodada.getCartasJogadas() + 1);
                }
                verificarQuemGanhouARodada();
            }
            verificarQuemGanhouAMao();
        }
    }



    private static void verificarQuemGanhouAMao(){
        //remover as cartas do jogador
        if(jogador1.getRodadasGanhas() == 2){
            jogador1.setPontos(jogador1.getPontos() + rodada.getValendo());
            jogador1.setRodadasGanhas(0);
            rodada.setCartasJogadas(0);
            jogador1.setEhMinhaVez(true);

            jogador2.setRodadasGanhas(0);
            rodada.setCartasJogadas(0);
        }else {
            jogador2.setPontos(jogador2.getPontos() + rodada.getValendo());
            jogador2.setRodadasGanhas(0);
            rodada.setCartasJogadas(0);
            jogador2.setEhMinhaVez(true);

            jogador1.setRodadasGanhas(0);
            rodada.setCartasJogadas(0);
        }
        rodada.setValendo(1);
    }

    private static void verificarQuemGanhouARodada(){
        jogador1.setEhMinhaVez(false);
        jogador2.setEhMinhaVez(false);
        Jogador jogador = maiorCarta();
        if(jogador != null){
            jogador.setRodadasGanhas(jogador.getRodadasGanhas() + 1);
            rodada.setCartasJogadas(0);
            jogador.setEhMinhaVez(true);
        }else {
            jogador1.setRodadasGanhas(jogador1.getRodadasGanhas() + 1);
            jogador2.setRodadasGanhas(jogador2.getRodadasGanhas() + 1);
            rodada.setCartasJogadas(0);
        }
    }

    private static void limparCartasJogadores(){
        jogador1.getCartas().clear();
        jogador2.getCartas().clear();
    }

    private static void ta(Jogador jogador1, Jogador jogador2){
        if(jogador1.isEhMinhaVez()){
            exibirCartasJogador(jogador1);
            Carta carta1 = jogarUmaCarta(jogador1, sc.nextInt());
        }else {
            exibirCartasJogador(jogador2);
            Carta carta1 = jogarUmaCarta(jogador2, sc.nextInt());
        }
        //deciidir qual jogador vai jogar primeiro, exibir as cartas do primeiro jogador, jogar a carta do primeiro jogador, e fazer para o segundo jogador
    }

    private static void prepararCartasJogador(Jogador jogador, Baralho baralho){
        jogador.getCartas().add(baralho.entregarCartaAleatoria());
        jogador.getCartas().add(baralho.entregarCartaAleatoria());
        jogador.getCartas().add(baralho.entregarCartaAleatoria());
    }

    private static void exibirCartasJogador(Jogador jogador){
        System.out.println("");
        for (Carta carta : jogador.getCartas()){
            System.out.print(carta.getValor() + " " + carta.getNaipe() + "   ");
        }
        System.out.println("");
    }

    private static Carta jogarUmaCarta(Jogador jogador, int posicaoCarta){
        Carta carta = null;

            try {
                    carta = jogador.getCartas().get(posicaoCarta -1);
                    jogador.getCartas().remove(carta);
            }catch (Exception e){
                System.out.println("Posicao escolhida invalida! Tente novamente!");
                System.out.format("Escolha uma carta %s:", jogador.getNome());
                exibirCartasJogador(jogador);
                carta = jogarUmaCarta(jogador, sc.nextInt());
            }
        return carta;
    }

    private static Jogador maiorCarta(){
        if(carta1.getValor() == 10 && carta2.getValor() == 10){
            return carta1.getNaipe().valorNaipe > carta2.getNaipe().valorNaipe ?  jogador1 : jogador2;
        }else if(carta1.getValor() == carta2.getValor()){
            return null;
        }
        return carta1.getValor() > carta2.getValor() ?  jogador1: jogador2;

    }

    private static void decisoesRodada(int ganhadorRodadaAnterior, Jogador jogador1, Jogador jogador2){
        switch (ganhadorRodadaAnterior){
            case 1:
                jogador1.setRodadasGanhas(jogador1.getRodadasGanhas()+1);
                jogador1.setEhMinhaVez(true);
                jogador2.setEhMinhaVez(false);
                break;
            case 2:
                jogador2.setRodadasGanhas(jogador2.getRodadasGanhas()+1);
                jogador1.setEhMinhaVez(false);
                jogador2.setEhMinhaVez(true);
                break;
            default:
                jogador1.setRodadasGanhas(jogador1.getRodadasGanhas()+1);
                jogador2.setRodadasGanhas(jogador2.getRodadasGanhas()+1);
                break;
        }
    }

}