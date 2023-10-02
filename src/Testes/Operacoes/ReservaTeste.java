package Testes.Operacoes;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Operacoes.Reserva;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.LivroServico;
import Servico.ReservaServico;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTeste {
    private ReservaServico reservaServico;
    private Reserva reserva;
    private Leitor leitor;
    private Livro livro;

    @After
    void testReservarLivro() throws Excecao {
        reserva = reservaServico.criarReserva(leitor, "Jogos Vorazes");
        boolean reservado = reserva.isReservaConcluida();
        assertTrue(reservado);
    }

    @Test
    void testReservarLivroInexistente() {
        assertThrows(Excecao.class, () -> reservaServico.criarReserva(leitor, "Esse Livro Nao Existe"));
    }

    @Test
    void cancelarReserva() throws Excecao {
        reserva = reservaServico.criarReserva(leitor, "Jogos Vorazes");
        boolean cancelado = reservaServico.cancelarReserva(leitor, "Jogos Vorazes");
        assertTrue(cancelado);
    }

    @BeforeEach
    void testCriaInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "786.424.865-97", endereco, "74999823548");
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        livro.setDisponibilidade(false);
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.salvar(livro);
        LivroServico livroServico = new LivroServico(livroDAO);
        reservaServico = new ReservaServico(livroServico);
    }
}


