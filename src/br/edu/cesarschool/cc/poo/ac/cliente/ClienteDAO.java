package br.edu.cesarschool.cc.poo.ac.cliente;
import java.io.Serializable;
import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
public class ClienteDAO {
    private CadastroObjetos cadastro = new CadastroObjetos(Cliente.class);
    private String obterIdUnico(Cliente cliente) {
        return cliente.getCpf();
    }
    public Cliente buscar(String cpf) {
        return (Cliente)cadastro.buscar(cpf);
    }
    /*
     * Objetos são incluídos em arquivos físicos, gravados
     * no diretório Cliente (o nome da classe de entidade), que fica
     * no mesmo nível do diretório src, dentro do projeto JAVA.
     * Cada objeto é incluído em um arquivo diferente, cujo nome
     * é o id único do objeto em questão, e tem a extensão dat.
     */
    public static boolean incluir(Cliente cliente) {
        String idUnico = obterIdUnico(cliente);
        Cliente cli = buscar(idUnico);
        if (cli == null) {
            cadastro.incluir((Serializable) cliente, idUnico);
            return true;
        }
        return false;
    }
    public boolean alterar(Cliente cliente) {
        String idUnico = obterIdUnico(cliente);
        Cliente cli = buscar(idUnico);
        if (cli != null) {
            cadastro.alterar((Serializable) cliente, idUnico);
            return true;
        }
        return false;
    }

    public boolean excluir(String cpf) {
        Cliente cli = buscar(cpf);
        if (cli != null) {
            cadastro.excluir(cpf);
            return true;
        }
        return false;
    }
}