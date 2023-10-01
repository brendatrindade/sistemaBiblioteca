package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Reserva;
import Model.Usuarios.Leitor;
import Servico.LivroServico;
import Servico.ReservaServico;

public class ReservaControle {
    private ReservaServico reservaServico;

    public ReservaControle(LivroServico livroServico) {
        this.reservaServico = new ReservaServico(livroServico);
    }

    public Reserva criarReserva(Leitor leitor, String titulo) throws Excecao {
        return reservaServico.criarReserva(leitor, titulo);
    }

    public void cancelarReserva(Leitor leitor, String titulo) throws Excecao{
        reservaServico.cancelarReserva(leitor, titulo);
    }

    public int getNumeroDeLeitoresNaFila(String titulo) {
        return reservaServico.getNumeroDeLeitoresNaFila(titulo);
    }

}

