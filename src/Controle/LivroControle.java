/**
 * LivroControle
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
package Controle;

import Model.Operacoes.Livro;
import Model.Operacoes.Localizacao;
import Model.Usuarios.Leitor;
import Servico.LivroServico;

import java.util.List;
import java.util.Queue;

public class LivroControle {
    private LivroServico livroServico;

    public LivroControle(LivroServico livroServico) {
        this.livroServico = livroServico;
    }
    public void criarLivro(String titulo, String autor,String isbn,String categoria, String anoPublicacao, String editora, Localizacao localizacao){
        Livro livro = livroServico.criarLivro(titulo, autor, isbn, categoria, anoPublicacao, editora, localizacao);
    }
    public void deleteLivro(Livro livro) {
        livroServico.deletarLivro(livro);
    }
    public void deletarTodosLivros() {
        livroServico.deletarTodosLivros();
    }
    public List<Livro> getAcervo(){
        return livroServico.getAcervo();
    }
    public void atualizarTituloLivro(Livro livro, String novoTitulo) {
        livroServico.atualizarTituloLivro(livro, novoTitulo);
    }
    public void atualizarAutorLivro(Livro livro, String novoAutor) {
        livroServico.atualizarAutorLivro(livro, novoAutor);
    }
    public void atualizarCategoriaLivro(Livro livro, String novaCategoria) {
        livroServico.atualizarCategoriaLivro(livro, novaCategoria);
    }
    public void atualizarIsbnLivro(Livro livro, String isbn) {
        livroServico.atualizarIsbnLivro(livro, isbn);
    }
    public void atualizarEditoraLivro(Livro livro, String editora) {
        livroServico.atualizarEditoraLivro(livro, editora);
    }
    public void atualizarAnoPublicacaoLivro(Livro livro, String anoPublicacao) {
        livroServico.atualizarAnoPublicacaoLivro(livro, anoPublicacao);
    }
    public List<Livro> buscarLivroPorTitulo(String titulo){
        return livroServico.buscarLivroPorTitulo(titulo);
    }
    public List<Livro> buscarLivroPorAutor(String autor) {
        return livroServico.buscarLivroPorAutor(autor);
    }
    public List<Livro> buscarLivroPorIsbn(String isbn) {
        return livroServico.buscarLivroPorIsbn(isbn);
    }
    public List<Livro> buscarLivroPorCategoria(String categoria) {
        return livroServico.buscarLivroPorCategoria(categoria);
    }
    public List<Livro> buscarLivroPorAnoPublicacao(String anoPubli) {
        return livroServico.buscarLivroPorAnoPublicacao(anoPubli);
    }
    public List<Livro> buscarLivroPorEditora(String editora) {
        return livroServico.buscarLivroPorEditora(editora);
    }
    public boolean possuiLivro(Livro livro) {
        return livroServico.possuiLivro(livro);
    }
    public Queue<Leitor> getReservasPorTitulo(String titulo) {
        return livroServico.getReservasPorTitulo(titulo);
    }
    public void setLeitoresReservasPorTitulo(String titulo, Queue<Leitor> leitoresNaFila) {
        livroServico.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
    }
    public Leitor verificaPrimeiroDaFila(String titulo){
        return livroServico.verificaPrimeiroDaFila(titulo);
    }
    public List<String> nomesNaFila(String titulo){
        return livroServico.nomesNaFila(titulo);
    }
    public int qtdLeitoresNaFila(String titulo){
        return livroServico.qtdLeitoresNaFila(titulo);
    }
    public void removePrimeiroDafila(String titulo) {
        livroServico.removePrimeiroDafila(titulo);
    }
}
