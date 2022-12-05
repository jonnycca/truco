package br.com.truco;

import java.util.Scanner;

public class Rodada {

    private int cartasJogadas;
    private Integer valendo = 1;

    public int getCartasJogadas() {
        return cartasJogadas;
    }

    public void setCartasJogadas(int cartasJogadas) {
        this.cartasJogadas = cartasJogadas;
    }

    public Integer getValendo() {
        return valendo;
    }

    public void setValendo(Integer valendo) {
        this.valendo = valendo;
    }


    public boolean aceitarTruco(boolean trucou, Jogador jogador, Jogador jogadorProximo){
        boolean aceitouTruco = false;
        if(trucou){
            aceitouTruco = aceitarTruco(jogador, jogadorProximo);
        }
        return aceitouTruco;
    }

    private boolean aceitarTruco(Jogador jogador, Jogador jogadorProximo){
        jogadorProximo.exibirCartasJogador();
        System.out.format("Jogador %s esta trucando, deseja aceitar? s : n", jogador.getNome());
        Scanner sc = new Scanner(System.in);
        String aceitar = sc.nextLine();
        boolean aceitouTruco = decisaoTruco(aceitar);
        if(aceitouTruco){
            if(this.valendo.equals(1)){
                this.valendo = 3;
            }else {
                this.valendo = valendo + 3;
            }
        }
        return aceitouTruco;
    }

    private boolean decisaoTruco(String aceitar){
        return aceitar.equals("s");
    }

    public void verificarQuemSeraOMaoDaRodada(Jogador jogador1, Jogador jogador2){
        if(jogador1.isMaoRodada()){
            jogador1.setMaoRodada(false);
            jogador2.setMaoRodada(true);
            jogador2.setEhMinhaVez(true);
        }else {
            jogador2.setMaoRodada(false);
            jogador1.setMaoRodada(true);
            jogador1.setEhMinhaVez(true);
        }
    }
}
