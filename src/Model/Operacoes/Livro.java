package Model.Operacoes;

public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private String anoPublicacao;
    private String editora;
    private boolean disponibilidade;
    private boolean livroCadastrado = false;

    public Livro(String titulo, String autor,String isbn,String categoria,String anoPublicacao, String editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.disponibilidade = true;
        this.livroCadastrado = true;
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

    public String toString() {
        if (livroCadastrado) {
            return ("\n"
                    + "Livro: " + titulo + " - Autoria: " + autor + " - ISBN: " + isbn + "\nCategoria: " + categoria
                    + " - Ano de publicacao: " + anoPublicacao + " - Editora: " + editora + " ." +
                    "\n");
        }
        return ("Livro não cadastrado");
    }

}