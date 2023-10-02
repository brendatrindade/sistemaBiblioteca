package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class AdministradorTeste {
    private Leitor leitor;
    private Bibliotecario bibliotecario;
    private Administrador administrador;
/*
    @Before
    void testCriarAdministrador() throws Excecao {
        administrador = new Administrador("Maria", "361.215.045-60", "senha123");
        assertDoesNotThrow(() -> administrador);
    }*/
    @Before
    public void testCriarAdministrador() throws Excecao {
            administrador = new Administrador("Maria", "361.215.045-60", "senha123");
    }
    @Test
    public void testBloquearLeitor() {
        administrador.bloquearLeitor(leitor);
        assertFalse(leitor.isStatusAcessoUsuario());
    }

    @Test
    public void testDesbloquearLeitor() {
        administrador.desbloquearLeitor(leitor);
        assertTrue(leitor.isStatusAcessoUsuario());
    }
    @Test
    public void testBloquearBibliotecario() {
        administrador.bloquearBibliotecario(bibliotecario);
        assertFalse(bibliotecario.isStatusAcessoUsuario());
    }

    @Test
    public void testDesbloquearBibliotecario() {
        administrador.desbloquerBibliotecario(bibliotecario);
        assertTrue(bibliotecario.isStatusAcessoUsuario());
    }

    @Before
    public void testCriaInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "786.424.865-97", endereco, "74999823548");
        bibliotecario = new Bibliotecario("Pedro", "153.853.595-56", "senha456");
    }

}



