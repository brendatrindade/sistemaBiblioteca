package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BibliotecarioTeste {
    private Bibliotecario bibliotecario;

    @BeforeEach
    void testCriarBibliotecario() throws Excecao {
        bibliotecario = new Bibliotecario("Lisa", "933.347.370-04", "senha123");
        assertDoesNotThrow(() -> bibliotecario);
    }
    @Test
    void testDefinirCargo() {
        bibliotecario.setCargo("Bibliotecario");
        assertEquals("Bibliotecario", bibliotecario.getCargo());
    }
    @Test
    void testCadastrarCpfInvalido() {
        assertThrows(Excecao.class, () -> bibliotecario.setCpf("790.102.245-01"));
    }
    @Test
    void testDefinirSenha() {
        bibliotecario.setSenha("minh@Senh@1");
        assertEquals("minh@Senh@1", bibliotecario.getSenha());
    }

}

