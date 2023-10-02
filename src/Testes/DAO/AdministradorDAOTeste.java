package Testes.DAO;

import DAO.AdministradorDAO;
import Excecoes.Excecao;
import Model.Usuarios.Administrador;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministradorDAOTeste {

    private AdministradorDAO administradorDAO;
    private Administrador administrador;
    @Before
    public void iniciarDAO() throws Excecao {
        administradorDAO = new AdministradorDAO();
        administrador = new Administrador("Maria", "361.215.045-60", "senha123");
    }

    @Test
    public void salvarAdiministrador() {
        administradorDAO.salvarAdiministrador(administrador);
        assertTrue(administradorDAO.getAdministradores().contains(administrador));
    }

    @Test
    public void deletarAdministrador() {
        administradorDAO.salvarAdiministrador(administrador);
        administradorDAO.deletarAdministrador(administrador);
        assertFalse(administradorDAO.getAdministradores().contains(administrador));
    }

    @Test
    public void deletarTodosAdministradores() {
        administradorDAO.salvarAdiministrador(administrador);
        administradorDAO.deletarTodosAdministradores();
        assertTrue(administradorDAO.getAdministradores().isEmpty());
    }

    @Test
    public void buscarAdministradorPorId() {
        administradorDAO.salvarAdiministrador(administrador);
        assertEquals(administrador.getCpf(), administradorDAO.buscarAdministradorPorId(administrador.getCpf()).getCpf());
    }
}

