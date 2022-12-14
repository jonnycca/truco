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
        jogador1.setMaoRodada(true);
        jogador2.setMaoRodada(false);

        //receber nome dos jogadores
        System.out.print("Digite o nome do primeiro jogador:");
        jogador1.setNome(sc.nextLine());
        System.out.print("Digite o nome do segundo jogador:");
        jogador2.setNome(sc.nextLine());


        while (jogador1.getPontos() < 12 && jogador2.getPontos() < 12) {
            baralho.montarBaralho();
            limparCartasJogadores();
            prepararCartasJogador(jogador1, baralho);
            prepararCartasJogador(jogador2, baralho);
            rodada.setCartasJogadas(0);//verificar se remove daqui, ja tem no metodo veriicar quem ganhou a mao

            exibirPontuacao();

            while (jogador1.getRodadasGanhas() < 2 && jogador2.getRodadasGanhas() < 2) { // verificar se der empate pq vai foder tudo
                boolean gritouTruco = false;
                boolean aceitouTruco = false;
                while (rodada.getCartasJogadas() <2 ) {
                    if (jogador2.isEhMinhaVez()) {
                        jogador2.exibirCartasJogador();
                        gritouTruco = jogador2.gritarTruco();
                        aceitouTruco = rodada.aceitarTruco(gritouTruco, jogador2, jogador1);
                        verificarSeTrucouECorreuOuAceitou(gritouTruco, aceitouTruco, jogador2);

                        //pegar um retorno que identifique se o adversario correu do truco
//                      se ele correu do truco
//                        deve encerrar a rodada que ?? esse primeiro while

                        //quando sair desse primeiro while, o metodo  verificarQuemGanhouARodada deve ser moficado e nao deve comparar as cartas para verificar quem ganhou a rodada
//                        e sim, quem correu pediu o truco que ganha os prontos da rodada
//                        System.out.format("Escolha uma carta %s:", jogador2.getNome());
//                        jogador2.exibirCartasJogador();
//                        carta2 = jogarUmaCarta(jogador2, sc.nextInt());
//                        jogador2.setEhMinhaVez(false);
//                        jogador1.setEhMinhaVez(true);
                    } else {
                        jogador1.exibirCartasJogador();
                        gritouTruco = jogador1.gritarTruco();
                        aceitouTruco = rodada.aceitarTruco(gritouTruco, jogador1, jogador2);
                        verificarSeTrucouECorreuOuAceitou(gritouTruco, aceitouTruco, jogador1);


//                        rodada.aceitarTruco(jogador1.gritarTruco(), jogador1, jogador2);
//                        System.out.format("Escolha uma carta %s:", jogador1.getNome());
//                        jogador1.exibirCartasJogador();
//                        carta1 = jogarUmaCarta(jogador1, sc.nextInt());
//                        jogador1.setEhMinhaVez(false);
//                        jogador2.setEhMinhaVez(true);
                    }
                    rodada.setCartasJogadas(rodada.getCartasJogadas() + 1);
                }
                verificarQuemGanhouARodada(gritouTruco, aceitouTruco);
            }
            verificarQuemGanhouAMao();
        }
    }

    private static void exibirPontuacao(){
        System.out.println(jogador1.getNome() + ": " + jogador1.getPontos() + "                       " + jogador2.getNome() + ": " + jogador2.getPontos());
    }

    private static void verificarSeTrucouECorreuOuAceitou(boolean trucou, Boolean aceitouTruco, Jogador jogador){
        if(trucou == false){ //verificar se nem trucou e vai jogar de boa
            jogadas(jogador);
        }else if(trucou == true && aceitouTruco.equals(true)){//verificar se trucou e aceitou, vai jogar trucado
            jogadas(jogador);
        }else if(trucou == true && aceitouTruco.equals(false)){//verificar se trucou e aceitou, vai jogar trucado
            forcarFimDaMaoEDaRodada(jogador);
        }
    }

    private static void forcarFimDaMaoEDaRodada(Jogador jogador){
        rodada.setCartasJogadas(2); // essa logica para terminar a mao
        jogador.setRodadasGanhas(2);
    }

    private static void jogadas(Jogador jogador){
        if(jogador.equals(jogador1)){
            System.out.format("Escolha uma carta %s:", jogador.getNome());
            jogador.exibirCartasJogador();
            carta1 = jogarUmaCarta(jogador1, sc.nextInt());
            jogador1.setEhMinhaVez(false);
            jogador2.setEhMinhaVez(true);
        }else {
            System.out.format("Escolha uma carta %s:", jogador.getNome());
            jogador.exibirCartasJogador();
            carta2 = jogarUmaCarta(jogador2, sc.nextInt());
            jogador2.setEhMinhaVez(false);
            jogador1.setEhMinhaVez(true);
        }
    }


    private static void verificarQuemGanhouAMao() {
        //remover as cartas do jogador
        if (jogador1.getRodadasGanhas() == 2) {
            jogador1.setPontos(jogador1.getPontos() + rodada.getValendo());
        } else {
            jogador2.setPontos(jogador2.getPontos() + rodada.getValendo());
        }
        jogador2.setRodadasGanhas(0);
        jogador1.setRodadasGanhas(0);
        rodada.setCartasJogadas(0);
        rodada.setValendo(1);
        rodada.verificarQuemSeraOMaoDaRodada(jogador1, jogador2);
    }


    private static void verificarQuemGanhouARodada(boolean gritouTruco, Boolean aceitouTruco) {
        jogador1.setEhMinhaVez(false);
        jogador2.setEhMinhaVez(false);

        //criar um if para so verificar a maior carta quando a logica do truco enquadrar
        if(gritouTruco == false){ //verificar se nem trucou e vai jogar de boa
            modificacoesQuandoDescobrirMaiorCarta();
        }else if(gritouTruco == true && aceitouTruco.equals(true)){//verificar se trucou e aceitou, vai jogar trucado
            modificacoesQuandoDescobrirMaiorCarta();
        }
//        else if(gritouTruco == true && aceitouTruco.equals(false)){//verificar se trucou e aceitou, vai jogar trucado
//
//        }
    }

    private static void modificacoesQuandoDescobrirMaiorCarta(){
        Jogador jogador = verificarMaiorCarta();
        if (jogador != null) {
            jogador.setRodadasGanhas(jogador.getRodadasGanhas() + 1);
            rodada.setCartasJogadas(0);
            jogador.setEhMinhaVez(true);
        } else {
            jogador1.setRodadasGanhas(jogador1.getRodadasGanhas() + 1);
            jogador2.setRodadasGanhas(jogador2.getRodadasGanhas() + 1);
            rodada.setCartasJogadas(0);
        }
    }

    private static void limparCartasJogadores() {
        jogador1.getCartas().clear();
        jogador2.getCartas().clear();
    }

    private static void prepararCartasJogador(Jogador jogador, Baralho baralho) {
        jogador.getCartas().add(baralho.entregarCartaAleatoria());
        jogador.getCartas().add(baralho.entregarCartaAleatoria());
        jogador.getCartas().add(baralho.entregarCartaAleatoria());
    }

    private static Carta jogarUmaCarta(Jogador jogador, int posicaoCarta) {
        Carta carta = null;
        try {
            carta = jogador.getCartas().get(posicaoCarta - 1);
            jogador.getCartas().remove(carta);
        } catch (Exception e) {
            System.out.println("Posicao escolhida invalida! Tente novamente!");
            System.out.format("Escolha uma carta %s:", jogador.getNome());
            jogador.exibirCartasJogador();
            carta = jogarUmaCarta(jogador, sc.nextInt());
        }
        return carta;
    }

    private static Jogador verificarMaiorCarta() {
        if (carta1.getValor().equals(10) && carta2.getValor().equals(10)) {
            return carta1.getNaipe().valorNaipe > carta2.getNaipe().valorNaipe ? jogador1 : jogador2;
        } else if (carta1.getValor() == carta2.getValor()) {
            return null;
        }
        return carta1.getValor() > carta2.getValor() ? jogador1 : jogador2;

    }

    private static void decisoesRodada(int ganhadorRodadaAnterior, Jogador jogador1, Jogador jogador2) {
        switch (ganhadorRodadaAnterior) {
            case 1:
                jogador1.setRodadasGanhas(jogador1.getRodadasGanhas() + 1);
                jogador1.setEhMinhaVez(true);
                jogador2.setEhMinhaVez(false);
                break;
            case 2:
                jogador2.setRodadasGanhas(jogador2.getRodadasGanhas() + 1);
                jogador1.setEhMinhaVez(false);
                jogador2.setEhMinhaVez(true);
                break;
            default:
                jogador1.setRodadasGanhas(jogador1.getRodadasGanhas() + 1);
                jogador2.setRodadasGanhas(jogador2.getRodadasGanhas() + 1);
                break;
        }
    }

}