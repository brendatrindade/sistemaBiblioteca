package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BibliotecarioTeste {
    private Bibliotecario bibliotecario;

    @Before
    public void testCriarBibliotecario() throws Excecao {
        bibliotecario = new Bibliotecario("Lisa", "933.347.370-04", "senha123");
    }
    @Test
    public void testDefinirCargo() {
        bibliotecario.setCargo("Bibliotecario");
        assertEquals("Bibliotecario", bibliotecario.getCargo());
    }
    @Test
    public void testCadastrarCpfInvalido() {
        assertThrows(Excecao.class, () -> bibliotecario.setCpf("790.102.245-01"));
    }
    @Test
    public void testDefinirSenha() {
        bibliotecario.setSenha("minh@Senh@1");
        assertEquals("minh@Senh@1", bibliotecario.getSenha());
    }
}

