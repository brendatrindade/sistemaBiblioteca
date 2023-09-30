package Testes;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Operacoes.Reserva;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class IntegracaoTeste {
    private Leitor leitor;
    private Bibliotecario bibliotecario;
    private Administrador administrador;
    private Livro livro;
    private Emprestimo emprestimo;
    private Reserva reserva;

    @BeforeEach
    void testCriarInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        bibliotecario = new Bibliotecario("Pedro", "153.853.595-56", "senha456");
        administrador = new Administrador("Maria", "361.215.045-60", "senha123");
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
    }

    @Test
    void testCriarEmprestimo() {
        emprestimo = new Emprestimo(livro, leitor);

        assertEquals(leitor, emprestimo.getLeitor());
        assertEquals(livro, emprestimo.getLivro());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertFalse(emprestimo.isstatusEmprestimoFinalizado());
    }
    @Test
    void testFinalizarEmprestimo() {
        emprestimo = new Emprestimo(livro, leitor);
        emprestimo.registrarDevolucao();

        assertEquals(LocalDate.now(), emprestimo.getDataRealizadaDev());
        assertFalse(emprestimo.emAtraso());
        assertTrue(emprestimo.isstatusEmprestimoFinalizado());
    }

    @Test
    void testBloquearBibliotecario() {
        bibliotecario.desbloquearConta();
        administrador.bloquearBibliotecario(bibliotecario);

        assertFalse(bibliotecario.isStatusAcessoUsuario());
    }
    @Test
    void testDesbloquearBibliotecario() {
        bibliotecario.bloquearConta();
        administrador.desbloquerBibliotecario(bibliotecario);

        assertTrue(bibliotecario.isStatusAcessoUsuario());
    }

    @Test
    void testBloquearLeitor() {
        leitor.desbloquearConta();
        administrador.bloquearLeitor(leitor);

        assertFalse(leitor.isStatusAcessoUsuario());
    }
    @Test
    void testDesbloquearLeitor() {
        leitor.bloquearConta();
        administrador.desbloquearLeitor(leitor);

        assertTrue(leitor.isStatusAcessoUsuario());
    }

}


