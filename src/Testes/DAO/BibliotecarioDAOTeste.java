package Testes.DAO;

import DAO.BibliotecarioDAO;
import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class BibliotecarioDAOTeste {

   private BibliotecarioDAO bibliotecarioDAO;
   private Bibliotecario bibliotecario;

    @Before
    public void iniciarDAO() throws Excecao {
        bibliotecarioDAO = new BibliotecarioDAO();
        bibliotecario = new Bibliotecario("Lisa", "123.456.789-09", "senha123");
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
    public void cpfOperadorEstaCadastrado() {
        assertThrows(Excecao.class, () -> {
            bibliotecarioDAO.salvar(bibliotecario);
            bibliotecarioDAO.cpfOperadorEstaCadastrado(bibliotecario.getCpf());
        });
    }
}

