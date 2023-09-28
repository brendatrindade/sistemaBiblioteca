package Model.Operacoes;

import DAO.LivroDAO;
import Model.Usuarios.Leitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Reserva {
    private final LivroDAO livroDAO;
    private final String titulo;
    private boolean reservaConcluida;

    public Reserva(Leitor leitor, String titulo) {
        this.livroDAO = new LivroDAO();
        this.titulo = titulo;
        reservarLivro(titulo, leitor);
    }

    public void reservarLivro(String titulo, Leitor leitor) {
        List<Livro> livrosPorTitulo = livroDAO.buscarLivroPorTitulo(titulo);

        if (livrosPorTitulo.isEmpty()) {
            System.out.println("Titulo não localizado, não foi possível concluir a reserva");
            reservaConcluida = false;
            return;
        }
        for (Livro livro : livrosPorTitulo) {
            if (livro.isDisponibilidade()) {
                System.out.println("O livro " + livro.getTitulo() + " ISBN: " + livro.getIsbn() + " está disponivel para emprestimo.");
                reservaConcluida = false;
                return;
            }
        }
        titulo = titulo.toLowerCase();
        Queue<Leitor> leitoresNaFila = livroDAO.getReservasPorTitulo(titulo);
        if (leitoresNaFila == null) {
            leitoresNaFila = new LinkedList<>();
        }
        leitoresNaFila.add(leitor);
        livroDAO.setLeitoresReservasPorTitulo(titulo, leitoresNaFila);
        reservaConcluida = true;
        System.out.println(leitor.getNome() + ", reserva foi realizada com sucesso!");
    }

    public String toString() {
        if (reservaConcluida){
            return ("---------------------------------------------------------------------------------------------\n"
                    + "Livro: " + titulo + " - Leitores na fila: " + livroDAO.nomesNaFila(titulo) );
        }
        return ("Reserva não realizada");
    }

}
