package Testes.Operacoes;

import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Operacoes.Reserva;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.LivroServico;
import Servico.ReservaServico;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ReservaTeste {
    private ReservaServico reservaServico;
    private LivroDAO livroDAO;
    private Reserva reserva;
    private Leitor leitor;
    private Livro livro;

    @Test
    public void testReservarLivro() throws Excecao {
        reserva = reservaServico.criarReserva(leitor, "Vorazes");
        boolean reservado = reserva.isReservaConcluida();
        assertTrue(reservado);
    }

    @Test
    public void testReservarLivroInexistente() {
        assertThrows(Excecao.class, () -> reservaServico.criarReserva(leitor, "Esse Livro Nao Existe"));
    }

    @Test
    public void cancelarReserva() throws Excecao {
        reserva = reservaServico.criarReserva(leitor, "Vorazes");
        boolean cancelado = reservaServico.cancelarReserva(leitor, "Vorazes");
        assertTrue(cancelado);
    }

    @Before
    public void testCriaInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        this.leitor = new Leitor("Brenda", "786.424.865-97", endereco, "74999823548");
        this.livroDAO = new LivroDAO();
        this.livro = new Livro("Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        this.livro.setDisponibilidade(false);
        livroDAO.salvar(livro);
        LivroServico livroServico = new LivroServico(livroDAO);
        this.reservaServico = new ReservaServico(livroServico);
    }
}


