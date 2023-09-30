package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class UsuarioTeste {
    private Usuario usuario;

    @BeforeEach
    void testCriarUsuario() {
        usuario= new Usuario();
        assertDoesNotThrow(() -> usuario.setCpf("029.532.025-79"));
    }
    @Test
    void testCadastrarCpfValido() throws Excecao {
        usuario.setCpf("700.582.245-56");
        assertDoesNotThrow(() -> usuario);
    }

    @Test
    void testCadastrarCpfInvalido() {
        assertThrows(Excecao.class, () -> usuario.setCpf("700.582.245-01"));
    }

    @Test
    void testNomearUsuario() {
        usuario.setNome("Julia");
        assertEquals("Julia", usuario.getNome());
    }

    @Test
    void testBloquearUsuario() {
        usuario.bloquearConta();
        assertFalse(usuario.isStatusAcessoUsuario());
    }

    @Test
    void testDesbloquearUsuario() {
        usuario.desbloquearConta();
        assertTrue(usuario.isStatusAcessoUsuario());
    }


}


