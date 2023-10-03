/**
 * Reserva
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

import Model.Usuarios.Leitor;
/**
 * Classe Reserva: Representa a reserva de um livro por realizada por um leitor.
 */
public class Reserva {
    private final String titulo;
    private final Leitor leitor;
    private int tamanhoFila;
    private boolean reservaConcluida = false;
    /**
     * Construtor da classe Reserva.
     * @param leitor objeto Leitor - leitor que fez a reserva.
     * @param titulo String - título do livro reservado.
     */
    public Reserva(Leitor leitor, String titulo) {
        this.titulo = titulo;
        this.leitor = leitor;
        this.reservaConcluida = true;
    }
    /**
     * Define o tamanho da fila de reservas para o livro.
     * @param tamanhoDaFila int - novo tamanho da fila de reservas.
     */
    public void setTamanhoFila(int tamanhoDaFila) {
        this.tamanhoFila = tamanhoDaFila;
    }
    /**
     * Retorna o status da reserva.
     * @return boolean - indica se a reserva solicitada foi concluída (true) ou não (false).
     */
    public boolean isReservaConcluida() {
        return reservaConcluida;
    }
    /**
     * Retorna o título do livro reservado.
     * @return String - título do livro reservado.
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Retorna o leitor que fez a reserva.
     * @return objeto Leitor - leitor que fez a reserva.
     */
    public Leitor getLeitor() {
        return leitor;
    }
    /**
     * Retorna o tamanho da fila de reservas para determinado livro.
     * @return int - tamanho da fila de reservas do livro.
     */
    public int getTamanhoFila() {
        return tamanhoFila;
    }
    /**
     * Retorna uma representação em string da reserva.
     * @return String - representa a reserva.
     */
    public String toString() {
        if (reservaConcluida){
            return ("\nLivro: " + titulo + " - Leitores na fila: " + tamanhoFila);
        }
        return ("Reserva não realizada");
    }
}
