package br.edu.cesarschool.cc.poo.ac.utils;

public class ValidadorCPF {
    private ValidadorCPF() {

    }
    public static boolean isCpfValido(String cpf) {
        return !StringUtils.isVaziaOuNula(cpf) && isDvFormatoValido(cpf);
    }
    private static boolean isDvFormatoValido(String cpf) {
        if (cpf.length() != 11) return false;
        int i = 10;
        int soma = 0;
        int digitoVerificador = 0;
        for (char c: cpf.toCharArray()) {
            if (!Character.isDigit(c)) return false;
            if (i >= 2) {
                soma += Character.getNumericValue(c) * i;
            }
            if (i == 1) {
                digitoVerificador = Character.getNumericValue(c);
            }
            i--;
        }
        int resto = (soma * 10) % 11;
        return resto == digitoVerificador;
    }
}