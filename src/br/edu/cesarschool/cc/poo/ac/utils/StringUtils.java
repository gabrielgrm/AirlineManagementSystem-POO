package br.edu.cesarschool.cc.poo.ac.utils;

public class StringUtils {
    private StringUtils(){
    }
    public static boolean isVaziaOuNula(String valor){
        if(valor == null || valor.isEmpty()){
            return true;
        } else{
            return false;
        }
    }
}
