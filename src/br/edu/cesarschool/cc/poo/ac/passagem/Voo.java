package br.edu.cesarschool.cc.poo.ac.passagem;
import br.edu.cesarschool.cc.poo.ac.utils.Registro;

public class Voo extends Registro {
    private String aeroportoOrigem;
    private String aeroDestino;
    private String companhiaAerea;
    private int numeroVoo;

    public Voo(String aeroDestino, String aeroportoOrigem, String companhiaAerea, int numeroVoo) {
        this.aeroDestino = aeroDestino;
        this.aeroportoOrigem = aeroportoOrigem;
        this.companhiaAerea = companhiaAerea;
        this.numeroVoo = numeroVoo;
    }

    public String getAeroDestino() {
        return aeroDestino;
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
