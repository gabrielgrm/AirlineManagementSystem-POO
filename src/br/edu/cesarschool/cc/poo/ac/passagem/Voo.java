package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class Voo extends Registro {
    private String aeroportoOrigem;
    private String aeroportoDestino;
    private String companhiaAerea;
    private int numeroVoo;

    public Voo(String aeroportoOrigem, String aeroportoDestino, String companhiaAerea, int numeroVoo) {
        this.aeroportoDestino = aeroportoDestino;
        this.aeroportoOrigem = aeroportoOrigem;
        this.companhiaAerea = companhiaAerea;
        this.numeroVoo = numeroVoo;
    }

    public String getAeroportoDestino() {
        return aeroportoDestino;
    }

    public String getAeroportoOrigem() {
        return aeroportoOrigem;
    }

    public String getCompanhiaAerea() {
        return companhiaAerea;
    }

    public int getNumeroVoo() {
        return numeroVoo;
    }
    
    public String obterIdVoo() {
        return companhiaAerea + numeroVoo;
    }
}
