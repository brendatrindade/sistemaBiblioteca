/**
 * BibliotecarioServico
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
package com.sistemaBiblioteca.Servico;

import com.sistemaBiblioteca.DAO.BibliotecarioDAO;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;

import java.util.List;
import java.util.Map;
/**
 * Classe BibliotecarioServico: fornece serviços para gerenciar bibliotecarios na biblioteca.
 */
public class BibliotecarioServico {
    private final BibliotecarioDAO bibliotecarioDAO;
    private final EmprestimoServico emprestimoServico;
    private final LeitorServico leitorServico;
    private final LivroServico livroServico;
    /**
     * Construtor para a classe BibliotecarioServico.
     * @param bibliotecarioDAO - DAO utilizado para operações de bibliotecário.
     * @param leitorServico - serviço de leitor.
     * @param livroServico - serviço de livro.
     * @param emprestimoServico - serviço de empréstimo.
     */
    public BibliotecarioServico (BibliotecarioDAO bibliotecarioDAO, LeitorServico leitorServico, LivroServico livroServico, EmprestimoServico emprestimoServico) {
        this.bibliotecarioDAO = bibliotecarioDAO;
        this.leitorServico = leitorServico;
        this.livroServico = livroServico;
        this.emprestimoServico = emprestimoServico;
    }
    /**
     * Retorna o serviço de bibliotecário.
     */
    public BibliotecarioServico getBibliotecarioServico(){
        return this;
    }
    /**
     * Cria um novo bibliotecário.
     * @param nome - nome do bibliotecário.
     * @param cpf - CPF do bibliotecário.
     * @param senha - senha do bibliotecário.
     * @return O novo bibliotecário criado.
     * @throws Excecao Se o CPF do operador já estiver cadastrado.
     */
    public Bibliotecario criarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        if (!cpfOperadorEstaCadastrado(cpf)) {
            Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, senha);
            salvarBibliotecario(bibliotecario);
            return bibliotecario;
        }
        return null;
    }
    /**
     * Registra um novo livro.
     * @param titulo - título do livro.
     * @param autor - autor do livro.
     * @param isbn - ISBN do livro.
     * @param categoria - categoria do livro.
     * @param anoPublicacao - ano de publicação do livro.
     * @param editora - editora do livro.
     * @param localizacao - localização do livro na biblioteca.
     */
    public void registrarNovoLivro(String titulo, String autor,String isbn,String categoria, String anoPublicacao, String editora, Localizacao localizacao){
        livroServico.criarLivro(titulo, autor, isbn, categoria, anoPublicacao, editora, localizacao);
    }
    /**
     * Salva um bibliotecário.
     * @param bibliotecario - bibliotecário a ser salvo.
     */
    public void salvarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarioDAO.salvar(bibliotecario);
    }
    /**
     * Deleta um bibliotecário.
     * @param bibliotecario - bibliotecário a ser deletado.
     */
    public void deletarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarioDAO.deletar(bibliotecario);
    }
    /**
     * Deleta todos os bibliotecários.
     */
    public void deletarTodosBibliotecarioes() {
        bibliotecarioDAO.deletarTodos();
    }
    /**
     * Busca um bibliotecário pelo seu CPF.
     * @param cpf - CPF do bibliotecário.
     * @return O bibliotecário encontrado.
     */
    public Bibliotecario buscarBibliotecarioPorId(String cpf) {
        return bibliotecarioDAO.buscarPorId(cpf);
    }
    /**
     * Retorna todos os bibliotecários cadastrados.
     * @return A lista de bibliotecários.
     */
    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarioDAO.getBibliotecarios();
    }
    /**
     * Registra um novo empréstimo.
     * @param livro - livro a ser emprestado.
     * @param leitor - leitor que está pegando o livro emprestado.
     * @return O novo empréstimo criado.
     * @throws Excecao Se o leitor não puder pegar livros emprestados no momento, se seu numero máximo de emprestimos ativos já foi atingido,
     * se o livro não estiver disponível e ele não for o primeiro leitor na fila de reservas para o titulo.
     */
    public Emprestimo registrarEmprestimo(Livro livro, Leitor leitor) throws Exception {
        return emprestimoServico.criarEmprestimo(livro, leitor);
    }
    /**
     * Devolve um livro.
     * @param livro - livro a ser devolvido.
     * @param leitor - leitor que está devolvendo o livro.
     */
    public void devolverLivro(Livro livro, Leitor leitor) {
        for (Emprestimo emprestimo : leitorServico.getEmprestimosAtivos(leitor)) {
            if (emprestimo.getLivro().equals(livro)) {
                emprestimo.registrarDevolucao();
                return;
            }
        }
        System.out.println("Livro com devolução pendende não localizado");
    }
    /**
     * Devolve um livro pelo título.
     * @param titulo - título do livro a ser devolvido.
     * @param leitor - leitor que está devolvendo o livro.
     */
    public void devolverLivroPorTitulo(String titulo, Leitor leitor) {
        for (Emprestimo emprestimo : leitorServico.getEmprestimosAtivos(leitor)) {
            if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(titulo)) {
                emprestimo.registrarDevolucao();
                return;
            }
        }
        System.out.println("Livro com devolução pendende não localizado");
    }
    /**
     * Pesquisa livros no acervo com base em um texto fornecido.
     * A pesquisa é realizada nos campos de título, autor, ISBN, categoria e ano de publicação.
     * @param texto - texto a ser usado na pesquisa.
     * @return Um mapa contendo listas de livros que correspondem ao texto em seus respectivos campos.
     */
    public Map<String, List<Livro>> pesquisarLivros(String texto) throws Exception {
        return leitorServico.pesquisarLivros(texto);
    }
    /**
     * Verifica se o CPF de um operador está cadastrado.
     * @param cpf - CPF do operador.
     * @return true se o CPF estiver cadastrado, false caso contrário.
     * @throws Excecao Se o CPF já estiver cadastrado.
     */
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return bibliotecarioDAO.cpfBibliotecarioEstaCadastrado(cpf);
    }
}
