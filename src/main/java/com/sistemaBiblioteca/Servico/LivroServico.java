/**
 * LivroServico
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

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.DAO.LivroDAO;

import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;

import java.util.List;
import java.util.Queue;
/**
 * Classe LivroServico: fornece serviços para gerenciar os livros da biblioteca.
 */
public class LivroServico {
    private LivroDAO livroDAO;
    /**
     * Construtor da classe LivroServico.
     */
    public LivroServico() throws Exception {
        this.livroDAO = DAO.getLivroDAO();
    }
    /**
     * Cria um novo livro.
     * @param titulo - Título do livro a ser criado.
     * @param autor - Autor do livro a ser criado.
     * @param isbn - ISBN do livro a ser criado.
     * @param categoria - Categoria do livro a ser criado.
     * @param anoPublicacao - Ano de publicação do livro a ser criado.
     * @param editora - Editora do livro a ser criado.
     * @param localizacao - Localização do livro a ser criado na biblioteca.
     * @return O livro criado.
     */
    public Livro criarLivro(String titulo, String autor,String isbn,String categoria, String anoPublicacao, String editora, Localizacao localizacao) {
        Livro livro = new Livro(titulo, autor, isbn, categoria, anoPublicacao, editora, localizacao);
        salvarLivro(livro);
        return livro;
    }
    /**
     * Salva um livro no acervo da biblioteca.
     * @param livro - livro a ser salvo.
     */
    public void salvarLivro(Livro livro) {
        livroDAO.salvar(livro);
    }
    /**
     * Deleta um livro do acervo da biblioteca.
     * @param livro - livro a ser excluído.
     */
    public void deletarLivro(Livro livro) {
        livroDAO.deletar(livro);
    }
    /**
     * Deleta todos os livros do acervo da biblioteca.
     */
    public void deletarTodosLivros() {
        livroDAO.deletarTodos();
    }
    /**
     * Retorna o acervo de livros da biblioteca.
     * @return Lista de livros no acervo.
     */
    public List<Livro> getAcervo(){
        return livroDAO.getAcervo();
    }

    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoTitulo - String como o novo dado a ser alterado no livro
     */
    public void atualizarTituloLivro(Livro livro, String novoTitulo) {
        livroDAO.atualizarTituloLivro(livro, novoTitulo);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoAutor - String como o novo dado a ser alterado no livro
     */
    public void atualizarAutorLivro(Livro livro, String novoAutor) {
        livroDAO.atualizarAutorLivro(livro, novoAutor);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novaCategoria - String como o novo dado a ser alterado no livro
     */
    public void atualizarCategoriaLivro(Livro livro, String novaCategoria) {
        livroDAO.atualizarCategoriaLivro(livro, novaCategoria);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoIsbn - String como o novo dado a ser alterado no livro
     */
    public void atualizarIsbnLivro(Livro livro, String novoIsbn) {
        livroDAO.atualizarIsbnLivro(livro, novoIsbn);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novaEditora - String como o novo dado a ser alterado no livro
     */
    public void atualizarEditoraLivro(Livro livro, String novaEditora) {
        livroDAO.atualizarEditoraLivro(livro, novaEditora);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoAnoPublicacao - String como o novo dado a ser alterado no livro
     */
    public void atualizarAnoPublicacaoLivro(Livro livro, String novoAnoPublicacao) {
        livroDAO.atualizarAnoPublicacaoLivro(livro, novoAnoPublicacao);
    }

    /**
     * Busca um livro do acervo.
     * @param titulo - Titulo do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorTitulo(String titulo) throws Exception {
        return livroDAO.buscarLivroPorTitulo(titulo);
    }
    /**
     * Busca um livro do acervo.
     * @param autor - Autor do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorAutor(String autor) throws Exception {
        return livroDAO.buscarLivroPorAutor(autor);
    }
    /**
     * Busca um livro do acervo.
     * @param isbn - ISBN do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorIsbn(String isbn) throws Exception {
        return livroDAO.buscarLivroPorIsbn(isbn);
    }
    /**
     * Busca um livro do acervo.
     * @param categoria - Categoria do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorCategoria(String categoria) throws Exception {
        return livroDAO.buscarLivroPorCategoria(categoria);
    }
    /**
     * Busca um livro do acervo.
     * @param anoPubli - Ano de publicação do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorAnoPublicacao(String anoPubli) throws Exception {
        return livroDAO.buscarLivroPorAnoPublicacao(anoPubli);
    }
    /**
     * Busca um livro do acervo.
     * @param editora - Editora do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorEditora(String editora) throws Exception {
        return livroDAO.buscarLivroPorEditora(editora);
    }
    /**
     * Verifica se um livro específico está no acervo.
     * @param livro - Livro a ser verificado.
     * @return true se o livro estiver no acervo, false caso contrário.
     */
    public boolean possuiLivro(Livro livro) {
        return livroDAO.acervoPossuiLivro(livro);
    }
    /**
     * Este método retorna a fila de reservas para um livro específico.
     * @param titulo O título do livro.
     * @return A fila de leitores que reservaram o livro.
     */
    public Queue<Leitor> getReservasPorTitulo(String titulo) {
        return livroDAO.getReservasPorTitulo(titulo);
    }
    /**
     * Define a fila de reservas para um titulo de livro específico.
     * @param titulo - título do livro.
     * @param leitoresNaFila A fila de leitores que reservaram o livro.
     */
    public void setLeitoresReservasPorTitulo(String titulo, Queue<Leitor> leitoresNaFila) throws Exception {
        livroDAO.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
        livroDAO.salvarReservasPorTituloArquivo();
    }
    /**
     * Verifica quem é o primeiro leitor da fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return O leitor que está no início da fila.
     */
    public Leitor verificaPrimeiroDaFila(String titulo){
        return livroDAO.verificaPrimeiroDaFila(titulo);
    }
    /**
     * Retorna os nomes dos leitores na fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return Uma lista com os nomes dos leitores na fila.
     */
    public List<String> nomesNaFila(String titulo){
        return livroDAO.nomesNaFila(titulo);
    }
    /**
     * Retorna a quantidade de leitores na fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return A quantidade de leitores na fila.
     */
    public int qtdLeitoresNaFila(String titulo){
        return livroDAO.qtdLeitoresNaFila(titulo);
    }
    /**
     * Remove o primeiro leitor da fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro reservado.
     */
    public void removePrimeiroDafila(String titulo) {
        livroDAO.removePrimeiroDafila(titulo);
    }
    /**
     * Salva a lista de reserva de um titulo de livro específico no arquivo.
     */
    public void salvarReservasPorTituloArquivo() throws Exception {
        livroDAO.salvarReservasPorTituloArquivo();
    }
}
