/**
 * BibliotecarioDAO
 *
 * @author Brenda Araújo Trindade Oliveira
 * @version 1.0
 * @since 02/10/2023
 *
 * Direitos autorais (c) 2023 Brenda Araújo Trindade Oliveira. Todos os direitos reservados.
 * Este software é confidencial e proprietário de Brenda Araújo Trindade Oliveira.
 * Este software é protegido sob direitos autorais.
 *
 */
package DAO;
import Excecoes.Excecao;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe BibliotecarioDAO: implementa DAOgenerico para os Operadores da biblioteca.
 * Generalização dos operadores.
 * Contém métodos gerenciamento dos bibliotecarios e operadores.
 */
public class BibliotecarioDAO implements DAOgenerico<Bibliotecario> {
    private static List<Bibliotecario> operadores = new ArrayList<>();
    private static List<Bibliotecario> bibliotecarios = new ArrayList<>();
    /**
     * Retorna todos os operadores.
     * @return Lista contendo todos os operadores da biblioteca.
     */
    public static List<Bibliotecario> getOperadores(){
        return operadores;
    }
    /**
     * Salva um novo bibliotecário na lista de bibliotecários.
     * @param c - bibliotecário a ser salvo.
     */
    @Override
    public void salvar(Bibliotecario c) {
        bibliotecarios.add(c);
    }
    /**
     * Deleta um bibliotecário da lista de bibliotecários.
     * @param c - bibliotecário a ser deletado.
     */
    @Override
    public void deletar(Bibliotecario c) {
        bibliotecarios.remove(c);
    }
    /**
     * Deleta todos os bibliotecários da lista de bibliotecários.
     */
    @Override
    public void deletarTodos() {
        bibliotecarios = new ArrayList<>();
    }
    /**
     * Busca um bibliotecário pelo id - CPF.
     * @param id - CPF do bibliotecário a ser buscado.
     * @return Bibliotecário encontrado ou null se o cpf não possuir cadastro.
     */
    @Override
    public Bibliotecario buscarPorId(String id) {
        if(bibliotecarios != null){
            for(Bibliotecario bibliotecario : bibliotecarios){
                if(bibliotecario.getCpf().equals(id))
                    return bibliotecario;
            }
        }
        return null;
    }
    /**
     * Retorna todos os Bibliotecarios
     */
    public List<Bibliotecario> getBibliotecarios(){
        for (Bibliotecario bibliotecario : operadores) {
            if (!(bibliotecario instanceof Administrador)){
                bibliotecarios.add(bibliotecario);
            }
        }

        return bibliotecarios;
    }
    /**
     * Verifica se o CPF de um operador já está cadastrado.
     * @param cpf - CPF a ser verificado.
     * @return true se o CPF já estiver cadastrado, false caso contrário.
     * @throws Excecao Se o CPF já estiver cadastrado, a exceção é lançada.
     */
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        if (bibliotecarios != null){
            for (Bibliotecario operador : bibliotecarios) {
                if (operador.getCpf().equals(cpf))
                    throw new Excecao(operador.getNome() + ", o CPF informado ja esta cadastrado como operador do sistema.");
            }
        } return false;
    }
}
