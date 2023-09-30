package Testes.Operacoes;

import Model.Operacoes.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LivroTeste {
    private Livro livro;
    @BeforeEach
    void testCriarLivro() {
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        assertDoesNotThrow(() -> livro);
    }

    @Test
    void testAlterarTitulo() {
        livro.setTitulo("Em chamas");
        assertEquals("Em chamas", livro.getTitulo());
    }

    @Test
    void testAlterarAutor() {
        livro.setAutor("Kettines Martin");
        assertEquals("Kettines Martin", livro.getAutor());
    }

    @Test
    void testAlterarIsbn() {
        livro.setIsbn("9788578277109");
        assertEquals("9788578277109", livro.getIsbn());
    }

    @Test
    void testAlterarCategoria() {
        livro.setCategoria("Fantasia");
        assertEquals("Fantasia", livro.getCategoria());
    }

    @Test
    void testAlterarAnoPublicacao() {
        livro.setAnoPublicacao("2010");
        assertEquals("2010", livro.getAnoPublicacao());
    }

    @Test
    void testAlterarEditora() {
        livro.setEditora("WMF Martins Fontes");
        assertEquals("WMF Martins Fontes", livro.getEditora());
    }

    @Test
    void testAlterarDisponibilidade() {
        livro.setDisponibilidade(false);
        assertFalse(livro.isDisponibilidade());
    }


}

