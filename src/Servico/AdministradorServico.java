/**
 * AdministradorServico
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
package Servico;

import DAO.AdministradorDAO;
import DAO.BibliotecarioDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;

import java.util.List;
/**
 * Classe AdministradorServico: fornece serviços para gerenciar usuários da biblioteca.
 */
public class AdministradorServico extends BibliotecarioServico {
    private final AdministradorDAO administradorDAO;
    private final LeitorServico leitorServico;
    private final LivroServico livroServico;
    /**
     * Construtor para a classe AdministradorServico.
     * @param administradorDAO - DAO utilizado para operações de administrador.
     * @param bibliotecarioDAO - DAO utilizado para operações de bibliotecário.
     * @param leitorServico - serviço de leitor.
     * @param livroServico - serviço de livro.
     * @param emprestimoServico - serviço de empréstimo.
     */
    public AdministradorServico(AdministradorDAO administradorDAO, BibliotecarioDAO bibliotecarioDAO, LeitorServico leitorServico, LivroServico livroServico, EmprestimoServico emprestimoServico) {
        super(bibliotecarioDAO, leitorServico, livroServico, emprestimoServico);
        this.administradorDAO = administradorDAO;
        this.leitorServico = leitorServico;
        this.livroServico = livroServico;
    }
    /**
     * Cria um novo administrador.
     * @param nome - nome do administrador.
     * @param cpf - CPF do administrador.
     * @param senha - senha do administrador.
     * @return O novo administrador criado.
     * @throws Excecao Se o CPF do operador já estiver cadastrado.
     */
    public Administrador criarAdministrador(String nome, String cpf, String senha) throws Excecao {
        if (!cpfOperadorEstaCadastrado(cpf)) {
            Administrador administrador = new Administrador(nome, cpf, senha);
            salvarAdministrador(administrador);
            return administrador;
        }
        return null;
    }
    /**
     * Registra um novo bibliotecário.
     * @param nome - nome do bibliotecário.
     * @param cpf - CPF do bibliotecário.
     * @param senha - senha do bibliotecário.
     * @throws Excecao Se o CPF do operador já estiver cadastrado.
     */
    public void registrarNovoBibliotecario(String nome, String cpf, String senha) throws Excecao {
        BibliotecarioServico bibliotecarioServico = getBibliotecarioServico();
        bibliotecarioServico.criarBibliotecario(nome, cpf, senha);
    }
    /**
     * Salva um administrador.
     * @param administrador - administrador a ser salvo.
     */
    public void salvarAdministrador(Administrador administrador){
        administradorDAO.salvarAdiministrador(administrador);
    }
    /**
     * Deleta um administrador.
     * @param administrador - administrador a ser deletado.
     */
    public void deletarAdministrador(Administrador administrador){
        administradorDAO.deletarAdministrador(administrador);
    }
    /**
     * Deleta todos os administradores.
     */
    public void deletarTodosAdministradores(){
        administradorDAO.deletarTodosAdministradores();
    }
    /**
     * Busca um administrador pelo seu CPF.
     * @param cpf - CPF do administrador.
     * @return O administrador encontrado.
     */
    public Administrador buscarAdministradorPorId(String cpf){
        return administradorDAO.buscarAdministradorPorId(cpf);
    }
    /**
     * Retorna todos os administradores cadastrados.
     * @return A lista de administradores.
     */
    public List<Administrador> getAdministradores(){
        return administradorDAO.getAdministradores();
    }
    /**
     * Verifica se o CPF de um operador está cadastrado.
     * @param cpf - CPF do operador.
     * @return true se o CPF estiver cadastrado, false caso contrário.
     * @throws Excecao Se o CPF já estiver cadastrado.
     */
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return administradorDAO.cpfOperadorEstaCadastrado(cpf);
    }
    /**
     * Cadastra um novo leitor.
     * @param nome - nome do leitor.
     * @param cpf - CPF do leitor.
     * @param endereco - endereço do leitor.
     * @param telefone - telefone do leitor.
     * @throws Excecao Se o CPF do leitor já estiver cadastrado.
     */
    public void cadastrarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        leitorServico.criarLeitor(nome, cpf, endereco, telefone);
    }
    /**
     * Remove um leitor.
     * @param leitor - leitor a ser removido.
     */
    public void removerLeitor(Leitor leitor) {
        leitorServico.deletarLeitor(leitor);
    }
    /**
     * Remove um bibliotecário.
     * @param bibliotecario - bibliotecário a ser removido.
     */
    public void removerBibliotecario(Bibliotecario bibliotecario) {
        administradorDAO.deletar(bibliotecario);
    }
    /**
     * Remove um livro.
     * @param livro - livro a ser removido.
     */
    public void removerLivro(Livro livro) {
        livroServico.deletarLivro(livro);
    }

}
