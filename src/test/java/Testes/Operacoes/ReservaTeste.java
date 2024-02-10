package Testes.Operacoes;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.DAO.LivroDAO;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Operacoes.Reserva;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import com.sistemaBiblioteca.Servico.LivroServico;
import com.sistemaBiblioteca.Servico.ReservaServico;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ReservaTeste {
    private ReservaServico reservaServico;
    private LivroDAO livroDAO = DAO.getLivroDAO();
    private Reserva reserva;
    private Leitor leitor;
    private Livro livro;

    public ReservaTeste() throws Exception {
    }

    @Test
    public void testReservarLivro() throws Exception {
        reserva = reservaServico.criarReserva(leitor, "Vorazes");
        boolean reservado = reserva.isReservaConcluida();
        assertTrue(reservado);
    }

    @Test
    public void testReservarLivroInexistente() {
        assertThrows(Excecao.class, () -> reservaServico.criarReserva(leitor, "Esse Livro Nao Existe"));
    }

    @Test
    public void cancelarReserva() throws Exception {
        reserva = reservaServico.criarReserva(leitor, "Vorazes");
        boolean cancelado = reservaServico.cancelarReserva(leitor, "Vorazes");
        assertTrue(cancelado);
    }

    @Before
    public void testCriaInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        this.leitor = new Leitor("Brenda", "786.424.865-97", endereco, "74999823548");
        Localizacao localizacao = new Localizacao("w", "59");
        this.livro = new Livro("Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores", localizacao);
        this.livro.setDisponibilidade(false);
        livroDAO.salvar(livro);
        LivroServico livroServico = new LivroServico(livroDAO);
        this.reservaServico = new ReservaServico(livroServico);
    }
}


