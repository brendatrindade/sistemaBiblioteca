package Servico;

import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Operacoes.Reserva;
import Model.Usuarios.Leitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ReservaServico {

    private final LivroServico livroServico;

    public ReservaServico(LivroServico livroServico) {
        this.livroServico = livroServico;
    }

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

    public int getNumeroDeLeitoresNaFila(String titulo) {
        Queue<Leitor> leitoresNaFila = livroServico.getReservasPorTitulo(titulo);
        return leitoresNaFila.size();
    }

}

