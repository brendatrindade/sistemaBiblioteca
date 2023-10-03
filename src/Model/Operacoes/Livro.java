/**
 * Livro
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
package Model.Operacoes;
/**
 * Classe Livro: Representa um livro no sistema de biblioteca.
 */
public class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private String anoPublicacao;
    private String editora;
    private Localizacao localizacao;
    private boolean disponibilidade;
    private boolean livroCadastrado = false;
    /**
     * Construtor da classe Livro.
     * @param titulo String - título do livro.
     * @param autor String - autor do livro.
     * @param isbn String - ISBN do livro.
     * @param categoria String - categoria do livro.
     * @param anoPublicacao String - ano de publicação do livro.
     * @param editora String - editora do livro.
     */
    public Livro(String titulo, String autor,String isbn,String categoria,String anoPublicacao, String editora, Localizacao localizacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.categoria = categoria;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.localizacao = localizacao;
        this.disponibilidade = true;
        this.livroCadastrado = true;
    }
    /**
     * Retorna o autor do livro.
     * @return String - autor do livro.
     */
    public String getAutor() {
        return autor;
    }
    /**
     * Retorna o título do livro.
     * @return String - título do livro.
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Retorna a editora do livro.
     * @return String - editora do livro.
     */

    public String getEditora() {
        return editora;
    }
    /**
     * Retorna o ISBN do livro.
     * @return String - ISBN do livro.
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * Retorna o ano de publicação do livro.
     * @return String - ano de publicação do livro.
     */
    public String getAnoPublicacao() {
        return anoPublicacao;
    }
    /**
     * Retorna a categoria do livro.
     * @return String - categoria do livro.
     */
    public String getCategoria() {
        return categoria;
    }
    /**
     * Retorna a localização do livro na biblioteca.
     *
     * @return String - Localização do livro.
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }
    /**
     * Define o autor do livro.
     * @param autor String - novo autor do livro.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * Define o título do livro.
     * @param titulo String - novo título do livro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Define a categoria do livro.
     * @param categoria String - nova categoria do livro.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     * Define o ano de publicação do livro.
     * @param anoPublicacao String - novo ano de publicação do livro.
     */
    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    /**
     * Define a editora do livro.
     * @param editora String - nova editora do livro.
     */
    public void setEditora(String editora) {
        this.editora = editora;
    }
    /**
     * Define o ISBN do livro.
     * @param isbn String - novo ISBN do livro.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    /**
     * Define a localização do livro na biblioteca.
     * @param prateleira String - nova prateleira do livro.
     * @param posicao String - nova posicao do livro.
     */
    public void setLocalizacao(String prateleira, String posicao) {
        localizacao.setPrateleira(prateleira);
        localizacao.setPosicao(posicao);
    }
    /**
     * Define a disponibilidade do livro.
     * @param disponibilidade boolean - nova disponibilidade do livro (true para disponível, false para indisponível).
     */
    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    /**
     * Retorna a disponibilidade do livro.
     * @return boolean - indica se o livro está disponível (true) ou não (false).
     */
    public boolean isDisponibilidade(){
        return disponibilidade;
    }
    /**
     * Retorna uma representação em string do livro.
     * @return String - representa o livro.
     */
    public String toString() {
        String disp = "";
        if (disponibilidade)
            disp = "Disponivel";
        else
            disp = "Indisponivel";
        if (livroCadastrado) {

            return ("\n"
                    + "Livro: " + titulo + " - Autoria: " + autor + " - ISBN: " + isbn + "\nCategoria: " + categoria
                    + " - Ano de publicacao: " + anoPublicacao + " - Editora: " + editora
                    + localizacao + " - Status: " + disp);
        }
        return ("Livro não cadastrado");
    }
}