package Testes.DAO;

import com.sistemaBiblioteca.DAO.BibliotecarioDAO;
import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class BibliotecarioDAOTeste {

   private BibliotecarioDAO bibliotecarioDAO = DAO.getBibliotecarioDAO();
   private Bibliotecario bibliotecario;


    public BibliotecarioDAOTeste() throws Exception {
    }

    @Before
    public void iniciarDAO() throws Excecao {
        bibliotecario = new Bibliotecario("Ana", "361.215.045-60", "senha12");
    }

    @Test
    public void salvarBibliotecario() {
        bibliotecarioDAO.salvar(bibliotecario);
        assertTrue(bibliotecarioDAO.getBibliotecarios().contains(bibliotecario));
    }

    @Test
    public void deletarBibliotecario() {
        bibliotecarioDAO.salvar(bibliotecario);
        bibliotecarioDAO.deletar(bibliotecario);
        assertFalse(bibliotecarioDAO.getBibliotecarios().contains(bibliotecario));
    }

    @Test
    public void deletarTodosOsBibliotecario() {
        bibliotecarioDAO.salvar(bibliotecario);
        bibliotecarioDAO.deletarTodos();
        assertTrue(bibliotecarioDAO.getBibliotecarios().isEmpty());
    }

    @Test
    public void buscarBibliotecarioPorId() {
        bibliotecarioDAO.salvar(bibliotecario);
        assertEquals(bibliotecario, bibliotecarioDAO.buscarPorId(bibliotecario.getCpf()));
    }

    @Test
    public void cpfEstaCadastrado() {
        bibliotecarioDAO.salvar(bibliotecario);
        assertTrue(bibliotecarioDAO.cpfBibliotecarioEstaCadastrado(bibliotecario.getCpf()));
    }

    @After
    public void limparArquivo() throws Exception {
        bibliotecarioDAO.deletarTodos();
    }
}

