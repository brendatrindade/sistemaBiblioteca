package Testes.DAO;

import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Operacoes.Reserva;
import com.sistemaBiblioteca.Model.Usuarios.Administrador;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class IntegracaoTeste {
    private Leitor leitor;
    private Bibliotecario bibliotecario;
    private Administrador administrador;
    private Livro livro;
    private Emprestimo emprestimo;
    private Reserva reserva;

    @Before
    public void testCriarInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        bibliotecario = new Bibliotecario("Pedro", "153.853.595-56", "senha456");
        administrador = new Administrador("Maria", "361.215.045-60", "senha123");
        Localizacao localizacao = new Localizacao("K", "67");
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores", localizacao);
    }

    @Test
    public void testCriarEmprestimo() {
        emprestimo = new Emprestimo(livro, leitor);

        assertEquals(leitor, emprestimo.getLeitor());
        assertEquals(livro, emprestimo.getLivro());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertFalse(emprestimo.isstatusEmprestimoFinalizado());
    }
    @Test
    public void testFinalizarEmprestimo() {
        emprestimo = new Emprestimo(livro, leitor);
        emprestimo.registrarDevolucao();

        assertEquals(LocalDate.now(), emprestimo.getDataRealizadaDev());
        assertFalse(emprestimo.emAtraso());
        assertTrue(emprestimo.isstatusEmprestimoFinalizado());
    }

    @Test
    public void testBloquearBibliotecario() {
        bibliotecario.desbloquearConta();
        administrador.bloquearBibliotecario(bibliotecario);

        assertFalse(bibliotecario.isStatusAcessoUsuario());
    }
    @Test
    public void testDesbloquearBibliotecario() {
        bibliotecario.bloquearConta();
        administrador.desbloquerBibliotecario(bibliotecario);

        assertTrue(bibliotecario.isStatusAcessoUsuario());
    }

    @Test
    public void testBloquearLeitor() {
        leitor.desbloquearConta();
        administrador.bloquearLeitor(leitor);

        assertFalse(leitor.isStatusAcessoUsuario());
    }
    @Test
    public void testDesbloquearLeitor() {
        leitor.bloquearConta();
        administrador.desbloquearLeitor(leitor);

        assertTrue(leitor.isStatusAcessoUsuario());
    }

}


