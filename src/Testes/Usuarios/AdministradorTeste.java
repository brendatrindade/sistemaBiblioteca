package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class AdministradorTeste {
    private Leitor leitor;
    private Bibliotecario bibliotecario;
    private Administrador administrador;

    @BeforeEach
    void testCriarAdministrador() throws Excecao {
        administrador = new Administrador("Maria", "361.215.045-60", "senha123");
        assertDoesNotThrow(() -> administrador);
    }

    @Test
    void testBloquearLeitor() {
        administrador.bloquearLeitor(leitor);
        assertFalse(leitor.isStatusAcessoUsuario());
    }

    @Test
    void testDesbloquearLeitor() {
        administrador.desbloquearLeitor(leitor);
        assertTrue(leitor.isStatusAcessoUsuario());
    }
    @Test
    void testBloquearBibliotecario() {
        administrador.bloquearBibliotecario(bibliotecario);
        assertFalse(bibliotecario.isStatusAcessoUsuario());
    }

    @Test
    void testDesbloquearBibliotecario() {
        administrador.desbloquerBibliotecario(bibliotecario);
        assertTrue(bibliotecario.isStatusAcessoUsuario());
    }

    @BeforeEach
    void testCriaInstancias() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "786.424.865-97", endereco, "74999823548");
        bibliotecario = new Bibliotecario("Pedro", "153.853.595-56", "senha456");
    }


}



