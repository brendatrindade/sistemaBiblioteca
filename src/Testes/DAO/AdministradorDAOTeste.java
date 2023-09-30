package Testes.DAO;
import DAO.AdministradorDAO;
import Excecoes.Excecao;
import Model.Usuarios.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class AdministradorDAOTeste {

    private AdministradorDAO administradorDAO;
    private Administrador administrador;
    @BeforeEach
    void iniciarDAO() throws Excecao {
        administradorDAO = new AdministradorDAO();
        administrador = new Administrador("Maria", "361.215.045-60", "senha123");
    }

    @Test
    void salvarAdiministrador() {
        administradorDAO.salvarAdiministrador(administrador);
        assertTrue(administradorDAO.getAdministradores().contains(administrador));
    }

    @Test
    void deletarAdministrador() {
        administradorDAO.salvarAdiministrador(administrador);
        administradorDAO.deletarAdministrador(administrador);
        assertFalse(administradorDAO.getAdministradores().contains(administrador));
    }

    @Test
    void deletarTodosAdministradores() {
        administradorDAO.salvarAdiministrador(administrador);
        administradorDAO.deletarTodosAdministradores();
        assertTrue(administradorDAO.getAdministradores().isEmpty());
    }

    @Test
    void buscarAdministradorPorId() {
        administradorDAO.salvarAdiministrador(administrador);
        assertEquals(administrador.getCpf(), administradorDAO.buscarAdministradorPorId(administrador.getCpf()).getCpf());
    }
}

