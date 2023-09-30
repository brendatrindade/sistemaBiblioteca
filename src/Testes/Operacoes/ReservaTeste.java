package Testes.Operacoes;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Operacoes.Reserva;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTeste {
    private Reserva reserva;
    private Leitor leitor;
    private Livro livro;

    @After
    void testReservarLivro() {
         reserva = new Reserva(leitor, "Jogos Vorazes");
         Boolean reservado = reserva.isReservaConcluida();
         assertTrue(reservado);
    }

    @Test
    void testReservarLivroInexistente() {
        reserva = new Reserva(leitor, "Esse Livro Nao Existe");
        Boolean reservado = reserva.isReservaConcluida();
        assertFalse(reservado);
    }

    @BeforeEach
    void testCriaInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "786.424.865-97", endereco, "74999823548");
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.salvar(livro);
        livro.setDisponibilidade(false);
    }

}


