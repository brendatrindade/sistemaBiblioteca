package Model.Operacoes;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Usuarios.Leitor;

import java.util.List;
import java.util.Queue;


public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private String anoPublicacao;
    private String editora;
    private boolean disponibilidade;
    private final LivroDAO livroDAO;
    private boolean livroCadastrado = false;

    public Livro(String titulo, String autor,String isbn,String categoria,String anoPublicacao, String editora) {
        this.livroDAO = new LivroDAO();
        try {
            livroEstaCadastrado(isbn);
            this.titulo = titulo;
            this.autor = autor;
            this.isbn = isbn;
            this.categoria = categoria;
            this.anoPublicacao = anoPublicacao;
            this.editora = editora;
            this.disponibilidade = true;
            this.livroCadastrado = true;
            livroDAO.adiciona(this);
            System.out.println( titulo + " - registro efetuado com sucesso!");
        }
        catch (Excecao excecao) {
            System.out.println(excecao.getMessage());
        }
    }

    public String getAutor() {
        return autor;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getEditora() {
        return editora;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getAnoPublicacao() {
        return anoPublicacao;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    public boolean isDisponibilidade(){
        return disponibilidade;
    }
    public void livroEstaCadastrado(String isbn) throws Excecao {
        if (livroDAO.get() != null) {
            for (Livro livro : livroDAO.get()) {
                if (livro.getIsbn().equalsIgnoreCase(isbn)){
                    throw new Excecao(livro.getTitulo() +" - o exemplar ISBN: " +livro.getIsbn()+ " ja possui cadastro.\n");}
            }
        }
    }
    public void adicionaAoAcervo(){
        livroDAO.adiciona(this);
    }
    public void removeDoAcervo(){
        livroDAO.remove(this);
    }
    public void atualizarTitulo(String novoTitulo){
        livroDAO.atualizarTituloLivro(this, novoTitulo);
    }
    public void atualizarAutor(String novoAutor) {
        livroDAO.atualizarAutorLivro(this, novoAutor);
    }
    public void atualizarCategoria(String novaCategoria) {
        livroDAO.atualizarCategoriaLivro(this, novaCategoria);
    }
    public void atualizarIsbn(String isbn) {
        livroDAO.atualizarIsbnLivro(this, isbn);
    }
    public void atualizarEditora(String editora) {
        livroDAO.atualizarEditoraLivro(this, editora);
    }
    public void atualizarAnoPublicacao(String anoPublicacao) {
        livroDAO.atualizarAnoPublicacaoLivro(this, anoPublicacao);
    }
    public Queue<Leitor> reservasLivroPorTitulo(String titulo){
        return livroDAO.getReservasPorTitulo(titulo);
    }
    public List<Livro> buscarLivroPorTitulo(String titulo){
        return livroDAO.buscarLivroPorTitulo(titulo);
    }
    public void adicionarLeitorNaFilaDeReserva(Queue<Leitor> leitoresNaFila){
        livroDAO.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
    }

    public Leitor verificaPrimeiroDaFila(String titulo){
        return livroDAO.verificaPrimeiroDaFila(titulo);
    }

    public int qtdLeitoresNaFila(String titulo){
        return livroDAO.qtdLeitoresNaFila(titulo);
    }

    public void removeLeitorDaFila(String titulo){
        livroDAO.removePrimeiroDafila(titulo);
    }


    public String toString() {
        if (livroCadastrado) {
            return ("---------------------------------------------------------------------------------------------\n"
                    + "Livro: " + titulo + " - Autoria: " + autor + " - ISBN: " + isbn + "\nCategoria: " + categoria
                    + " - Ano de publicacao: " + anoPublicacao + " - Editora: " + editora + " ." +
                    "\n---------------------------------------------------------------------------------------------");
        }
        return ("Livro não cadastrado");
    }

}
