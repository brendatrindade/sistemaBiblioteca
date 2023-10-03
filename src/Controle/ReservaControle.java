/**
 * ReservaControle
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
package Controle;

import Excecoes.Excecao;
import Model.Usuarios.Leitor;
import Servico.LivroServico;
import Servico.ReservaServico;

public class ReservaControle {
    private final ReservaServico reservaServico;

    public ReservaControle(LivroServico livroServico) {
        this.reservaServico = new ReservaServico(livroServico);
    }

    public void criarReserva(Leitor leitor, String titulo) {
        try {
            reservaServico.criarReserva(leitor, titulo);
        }
        catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }

    public void cancelarReserva(Leitor leitor, String titulo) throws Excecao{
        reservaServico.cancelarReserva(leitor, titulo);
    }

    public int getNumeroDeLeitoresNaFila(String titulo) {
        return reservaServico.getNumeroDeLeitoresNaFila(titulo);
    }
}

