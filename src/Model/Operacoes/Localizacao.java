/**
 * Localizacao
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
 * Classe Localizacao: Representa a localização de um livro na biblioteca.
 */
public class Localizacao {
    private String prateleira;
    private String posicao;

    /**
     * Construtor da classe Localizacao.
     * @param prateleira - String prateleira onde o livro está localizado.
     * @param posicao - String posição do livro na prateleira.
     */
    public Localizacao(String prateleira, String posicao) {
        this.prateleira = prateleira;
        this.posicao = posicao;
    }

    // Getters e Setters
    public String getPrateleira() {
        return prateleira;
    }
    public void setPrateleira(String prateleira) {
        this.prateleira = prateleira;
    }
    public String getPosicao() {
        return posicao;
    }
    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    /**
     * Retorna uma representação em string da localização de um livro.
     * @return String - representa a localização.
     */
    public String toString() {
        return ("\nLocalizacao: Prateleira " + prateleira + " - Posicao " + posicao + ".");
    }
}

