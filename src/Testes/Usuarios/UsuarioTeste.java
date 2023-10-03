package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Usuario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class UsuarioTeste {
    private Usuario usuario;

    @Before
    public void testCriarUsuario() {
            usuario= new Usuario();
    }

    @Test
    public void testCadastrarCpfValido() throws Excecao {
        usuario.setCpf("700.582.245-56");
    }

    @Test
    public void testCadastrarCpfInvalido() {
        assertThrows(Excecao.class, () -> usuario.setCpf("700.582.245-01"));
    }

    @Test
    public void testNomearUsuario() {
        usuario.setNome("Julia");
        assertEquals("Julia", usuario.getNome());
    }

    @Test
    public void testBloquearUsuario() {
        usuario.bloquearConta();
        assertFalse(usuario.isStatusAcessoUsuario());
    }

    @Test
    public void testDesbloquearUsuario() {
        usuario.desbloquearConta();
        assertTrue(usuario.isStatusAcessoUsuario());
    }

}


