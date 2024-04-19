package br.edu.cesarschool.cc.poo.ac.utils;

public class ValidadorCPF {
    private ValidadorCPF(){
    }
    public static boolean isCpfValido(String cpf) {
        int total = 0;
        int primeiroDigito = 0;
        int segundoDigito = 0;
        if (cpf != null && !cpf.isEmpty()) {
            if (cpf.length() == 11) {
                int d1 = Integer.parseInt(cpf.substring(9,10));
                int d2 = Integer.parseInt(cpf.substring(10,11));
                for(int i = 0; i < cpf.length()-2; i++) {
                    total += (((int)cpf.charAt(i) - 48) * (10 - i));
                }
                if (total % 11 >= 2) {
                    primeiroDigito = 11 - (total % 11);
                }
                total = 0;
                for(int i = 0; i < cpf.length()-1; i++) {
                    total += (((int)cpf.charAt(i) - 48) * (11 - i));
                }
                if (total % 11 >= 2) {
                    segundoDigito = 11 - (total % 11);
                }
                if(primeiroDigito == d1 && segundoDigito == d2) {
                    return true; // se entrar aq
                }
                return false;
            }return false;
        }return false;
    }
}
