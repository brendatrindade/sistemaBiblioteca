package Testes.DAO;
import DAO.BibliotecarioDAO;
import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class BibliotecarioDAOTeste {

   private BibliotecarioDAO bibliotecarioDAO;
   private Bibliotecario bibliotecario;

    @BeforeEach
    void iniciarDAO() throws Excecao {
        bibliotecarioDAO = new BibliotecarioDAO();
        bibliotecario = new Bibliotecario("Lisa", "123.456.789-09", "senha123");
    }

    @Test
    void salvarBibliotecario() {
        bibliotecarioDAO.salvar(bibliotecario);
        assertTrue(bibliotecarioDAO.getBibliotecarios().contains(bibliotecario));
    }

    @Test
    void deletarBibliotecario() {
        bibliotecarioDAO.salvar(bibliotecario);
        bibliotecarioDAO.deletar(bibliotecario);
        assertFalse(bibliotecarioDAO.getBibliotecarios().contains(bibliotecario));
    }

    @Test
    void deletarTodosOsBibliotecario() {
        bibliotecarioDAO.salvar(bibliotecario);
        bibliotecarioDAO.deletarTodos();
        assertTrue(bibliotecarioDAO.getBibliotecarios().isEmpty());
    }

    @Test
    void buscarBibliotecarioPorId() {
        bibliotecarioDAO.salvar(bibliotecario);
        assertEquals(bibliotecario, bibliotecarioDAO.buscarPorId(bibliotecario.getCpf()));
    }

    @Test
    void cpfOperadorEstaCadastrado() {
        assertThrows(Excecao.class, () -> {
            bibliotecarioDAO.salvar(bibliotecario);
            bibliotecarioDAO.cpfOperadorEstaCadastrado(bibliotecario.getCpf());
        });
    }
}

