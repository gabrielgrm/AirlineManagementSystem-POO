package br.edu.cesarschool.cc.poo.ac.passagem;


import br.edu.cesarschool.cc.poo.ac.cliente.Cliente;
import br.edu.cesarschool.cc.poo.ac.utils.StringUtils;
import br.edu.cesarschool.cc.poo.ac.utils.ValidadorCPF;

import java.util.Objects;

public class VooMediator {
    private static VooMediator instancia;
    private VooDAO vooDAO = new VooDAO();
    private final String aeroportoValido = "GRU, CGH, GIG, SDU, REC, CWB, POA, BSB, SSA, FOR, MAO, SLZ, CNF, BEL, JPA, PNZ, CAU, FEN, SET, NAT, PVH, BVB, FLN, AJU, PMW, MCZ, MCP, VIX, GYN, CGB, CGR, THE, RBR, VCP, RAO";
    private VooMediator(){}

    public static VooMediator obterInstancia(){
        if(instancia == null){
            instancia = new VooMediator();
        }
        return instancia;
    }

    public Voo buscar(String IdVoo){
        return vooDAO.buscar(IdVoo);
    }

    public String validarCiaNumero(String companhiaAerea, int numeroVoo){
        if(StringUtils.isVaziaOuNula(companhiaAerea) || companhiaAerea.length() != 2) {
            return "CIA aerea errada";
        }
        if(numeroVoo < 1000 || numeroVoo > 9999) {
            return "Numero voo errado";
        }
        return null;
    }

    public String validar(Voo voo){
        if(StringUtils.isVaziaOuNula(voo.getAeroportoOrigem()) || !aeroportoValido.contains(voo.getAeroportoOrigem())) {
            return "Aeroporto origem errado";
        }
        if(StringUtils.isVaziaOuNula(voo.getAeroportoDestino()) || !aeroportoValido.contains(voo.getAeroportoDestino())) {
            return "Aeroporto destino errado";
        }
        if(Objects.equals(voo.getAeroportoOrigem(), voo.getAeroportoDestino())){
            return "Aeroporto origem igual a aeroporto destino";
        }
        if(validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo()) != null) {
            return validarCiaNumero(voo.getCompanhiaAerea(), voo.getNumeroVoo());
        }
        return null;
    }

    public String incluir(Voo voo) {
        if (validar(voo) != null) {
            return validar(voo);
        }
        if(!vooDAO.incluir(voo)) {
            return "Voo ja existente";
        } else return null;
    }

    public String alterar(Voo voo) {
        if (validar(voo) != null) {
            return validar(voo);
        }
        if(!vooDAO.alterar(voo)) {
            return "Voo inexistente";
        } else return null;
    }

    public String excluir(String idVoo) {
        if (StringUtils.isVaziaOuNula(idVoo)) {
            return "Id voo errado";
        }
        if(!vooDAO.excluir(idVoo)) {
            return "Voo inexistente";
        } else return null;
    }
}
