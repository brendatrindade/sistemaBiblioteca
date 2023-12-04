/**
 * AdministradorDAO
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

import Model.Operacoes.Livro;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.ArrayList;
import java.util.List;
/**
 * SubClasse AdministradorDAO: Estende BibliotecarioDAO
 * Especialização do objeto operador enquanto Administrador.
 * Contém métodos para gerenciar administradores da biblioteca.
 */
public class AdministradorDAO extends BibliotecarioDAO{
    private static List<Bibliotecario> operadores = getOperadores();
    private static List<Administrador> administradores = new ArrayList<>();

    public void salvarAdministradorArquivo() throws Exception {
        Persistencia.salvarAdministrador(administradores);
    }
    public List<Administrador> lerAdministradorArquivo() throws Exception {
        List<Administrador> administradoresArquivo = Persistencia.lerAdministrador();
        return administradoresArquivo;
    }

    /**
     * Salva um novo administrador na lista de administradores.
     * @param administrador - administrador a ser salvo.
     */
    public void salvarAdiministrador(Administrador administrador) {
        administradores.add(administrador);
    }
    /**
     * Deleta um administrador da lista de administradores.
     * @param administrador - administrador a ser deletado.
     */
    public void deletarAdministrador(Administrador administrador) {
        administradores.remove(administrador);
    }
    /**
     * Deleta todos os administradores da lista de administradores.
     */
    public void deletarTodosAdministradores() {
        administradores = new ArrayList<>();
    }
    /**
     * Busca um administrador pelo id - CPF.
     * @param id - CPF do administrador a ser buscado.
     * @return Administrador encontrado ou null se o cpf não possuir cadastro.
     */
    public Administrador buscarAdministradorPorId(String id) {
        if(administradores != null){
            for(Administrador administrador : administradores){
                if(administrador.getCpf().equals(id))
                    return administrador;
            }
        }
        return null;
    }
    /**
     * Retorna todos os administradores.
     * @return Lista contendo todos os administradores.
     */
    public List<Administrador> getAdministradores(){
        for (Bibliotecario operador : operadores) {
            if (operador instanceof Administrador) {
                Administrador administrador = (Administrador) operador;
                administradores.add(administrador);
            }
        }
        return administradores;
    }
}
