package Model.Operacoes;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Usuarios.Leitor;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private String anoPublicacao;
    private String editora;
    private boolean disponibilidade;
    private LivroDAO livroDAO;

    public Livro(String titulo, String autor,String isbn,String categoria,String anoPublicacao, String editora) throws Excecao{
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

    public String toString() {
        return ("---------------------------------------------------------------------------------------------\n"
                + "Livro: " + titulo + " - Autoria: " + autor + " - ISBN: " + isbn + "\nCategoria: " + categoria
                + " - Ano de publicacao: " + anoPublicacao + " - Editora: " + editora + " ."+
                "\n---------------------------------------------------------------------------------------------");
    }

}
