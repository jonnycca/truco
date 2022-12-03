package br.com.truco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Baralho {

    private List<Carta> cartas = new ArrayList<>();
    public Baralho() {
        montarBaralho();
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public void montarBaralho(){
        for(int i = 10; i >= 4; i--){
            List<Naipe> naipes = Arrays.asList(Naipe.values());
            for (Naipe naipe: naipes) {
                Carta carta = new Carta();
                carta.setValor(i);
                carta.setNaipe(naipe);
                getCartas().add(carta);
            }
        }
    }

    public Carta entregarCartaAleatoria()
    {
        Random rand = new Random();
        int indice = rand.nextInt(cartas.size());
        Carta carta = cartas.get(indice);
        removerCartaEntregue(indice);
        return carta;
    }

    private void removerCartaEntregue(int indice){
        cartas.remove(indice);
    }

}
