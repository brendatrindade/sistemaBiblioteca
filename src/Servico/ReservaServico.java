package Servico;

import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Operacoes.Reserva;
import Model.Usuarios.Leitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 * Classe ReservaServico: fornece serviços para gerenciar as reservas dos livros da biblioteca.
 */
public class ReservaServico {
    private final LivroServico livroServico;
    /**
     * Construtor da classe ReservaServico.
     * @param livroServico - Serviço de livro utilizado para operações de livros.
     */
    public ReservaServico(LivroServico livroServico) {
        this.livroServico = livroServico;
    }
    /**
     * Cria uma nova reserva.
     * @param leitor - Leitor que está fazendo a reserva.
     * @param titulo - String titulo do livro a ser reservado.
     * @return Reserva realizada.
     * @throws Excecao se o título não for localizado, se o livro estiver disponível para empréstimo ou se o leitor não puder reservar livros no momento.
     */
    public Reserva criarReserva(Leitor leitor, String titulo) throws Excecao {
        List<Livro> livrosPorTitulo = livroServico.buscarLivroPorTitulo(titulo);
        if (livrosPorTitulo.isEmpty()) {
            throw new Excecao("Ops! Titulo nao localizado.");
        }
        for (Livro livro : livrosPorTitulo) {
            if (livro.isDisponibilidade()) {
                throw new Excecao("Ops! O livro " + livro.getTitulo() + " esta disponivel para emprestimo.");
            }
        }
        if (leitor.isStatusAcessoUsuario()) {
            Queue<Leitor> leitoresNaFila = livroServico.getReservasPorTitulo(titulo);
            if (leitoresNaFila == null) {
                leitoresNaFila = new LinkedList<>();
            }
            leitoresNaFila.add(leitor);
            livroServico.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
            Reserva novaReserva = new Reserva(leitor, titulo);
            novaReserva.setTamanhoFila(leitoresNaFila.size());
            return novaReserva;
        }
        else throw new Excecao("Ops! " + leitor.getNome() + " nao pode reservar livros no momento.");
    }
    /**
     * Cancela uma reserva.
     * @param leitor - Leitor que fez a reserva.
     * @param titulo - String título do livro reservado.
     * @return true se o cancelamento for bem sucedido, false caso contrário.
     * @throws Excecao se o leitor não estiver na fila de reserva do livro.
     */
    public boolean cancelarReserva(Leitor leitor, String titulo) throws Excecao{
        Queue<Leitor> leitoresNaFila = livroServico.getReservasPorTitulo(titulo);
        if (leitoresNaFila != null) {
            for (Leitor leitorNaFila : leitoresNaFila) {
                if (leitorNaFila == leitor) {
                    leitoresNaFila.remove(leitor);
                    livroServico.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
                    return true;
                }
            }
        }
        else throw new Excecao("Ops! " + leitor.getNome() + " nao esta na fila de reserva do Livro " + titulo + ".");
        return false;
    }
    /**
     * Obtem o número de leitores na fila de reserva de um titulo de livro.
     * @param titulo - String título do livro.
     * @return Número de leitores na fila de reserva do livro.
     */
    public int getNumeroDeLeitoresNaFila(String titulo) {
        Queue<Leitor> leitoresNaFila = livroServico.getReservasPorTitulo(titulo);
        return leitoresNaFila.size();
    }
}

