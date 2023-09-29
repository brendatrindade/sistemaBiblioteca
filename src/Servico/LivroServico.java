package Servico;

import DAO.LivroDAO;

import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;

import java.util.List;
import java.util.Queue;

public class LivroServico {
    private LivroDAO livroDAO;

    public LivroServico(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public Livro criarLivro(String titulo, String autor,String isbn,String categoria,String anoPublicacao, String editora) {
        Livro livro = new Livro(titulo, autor, isbn, categoria, anoPublicacao, editora);
        salvarLivro(livro);
        return livro;
    }
    public void salvarLivro(Livro livro) {
        livroDAO.salvar(livro);
    }
    public void deletarLivro(Livro livro) {
        livroDAO.deletar(livro);
    }
    public void deletarTodosLivros() {
        livroDAO.deletarTodos();
    }
    public List<Livro> getAcervo(){
        return livroDAO.getAcervo();
    }

    public void atualizarTituloLivro(Livro livro, String novoTitulo) {
        livroDAO.atualizarTituloLivro(livro, novoTitulo);
    }
    public void atualizarAutorLivro(Livro livro, String novoAutor) {
        livroDAO.atualizarAutorLivro(livro, novoAutor);
    }
    public void atualizarCategoriaLivro(Livro livro, String novaCategoria) {
        livroDAO.atualizarCategoriaLivro(livro, novaCategoria);
    }
    public void atualizarIsbnLivro(Livro livro, String isbn) {
        livroDAO.atualizarIsbnLivro(livro, isbn);
    }
    public void atualizarEditoraLivro(Livro livro, String editora) {
        livroDAO.atualizarEditoraLivro(livro, editora);
    }
    public void atualizarAnoPublicacaoLivro(Livro livro, String anoPublicacao) {
        livroDAO.atualizarAnoPublicacaoLivro(livro, anoPublicacao);
    }

    public List<Livro> buscarLivroPorTitulo(String titulo){
        return livroDAO.buscarLivroPorTitulo(titulo);
    }
    public List<Livro> buscarLivroPorAutor(String autor) {
        return livroDAO.buscarLivroPorAutor(autor);
    }
    public List<Livro> buscarLivroPorIsbn(String isbn) {
        return livroDAO.buscarLivroPorIsbn(isbn);
    }
    public List<Livro> buscarLivroPorCategoria(String categoria) {
        return livroDAO.buscarLivroPorCategoria(categoria);
    }
    public List<Livro> buscarLivroPorAnoPublicacao(String anoPubli) {
        return livroDAO.buscarLivroPorAnoPublicacao(anoPubli);
    }
    public List<Livro> buscarLivroPorEditora(String editora) {
        return livroDAO.buscarLivroPorEditora(editora);
    }
    public boolean possuiLivro(Livro livro) {
        return livroDAO.possuiLivro(livro);
    }
    public Queue<Leitor> getReservasPorTitulo(String titulo) {
        return livroDAO.getReservasPorTitulo(titulo);
    }
    public void setLeitoresReservasPorTitulo(String titulo, Queue<Leitor> leitoresNaFila) {
        livroDAO.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
    }
    public Leitor verificaPrimeiroDaFila(String titulo){
        return livroDAO.verificaPrimeiroDaFila(titulo);
    }
    public List<String> nomesNaFila(String titulo){
        return livroDAO.nomesNaFila(titulo);
    }
    public int qtdLeitoresNaFila(String titulo){
        return livroDAO.qtdLeitoresNaFila(titulo);
    }
    public void removePrimeiroDafila(String titulo) {
        livroDAO.removePrimeiroDafila(titulo);
    }

}
