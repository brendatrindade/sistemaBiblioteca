package Testes.Operacoes;

import Model.Operacoes.Livro;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LivroTeste {
    private Livro livro;
    @Before
    public void testCriarLivro() {
        livro = new Livro("Juegos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
    }

    @Test
    public void testAlterarTitulo() {
        livro.setTitulo("Em chamas");
        assertEquals("Em chamas", livro.getTitulo());
    }

    @Test
    public void testAlterarAutor() {
        livro.setAutor("Kettines Martin");
        assertEquals("Kettines Martin", livro.getAutor());
    }

    @Test
    public void testAlterarIsbn() {
        livro.setIsbn("9788578277109");
        assertEquals("9788578277109", livro.getIsbn());
    }

    @Test
    public void testAlterarCategoria() {
        livro.setCategoria("Fantasia");
        assertEquals("Fantasia", livro.getCategoria());
    }

    @Test
    public void testAlterarAnoPublicacao() {
        livro.setAnoPublicacao("2010");
        assertEquals("2010", livro.getAnoPublicacao());
    }

    @Test
    public void testAlterarEditora() {
        livro.setEditora("WMF Martins Fontes");
        assertEquals("WMF Martins Fontes", livro.getEditora());
    }

    @Test
    public void testAlterarDisponibilidade() {
        livro.setDisponibilidade(false);
        assertFalse(livro.isDisponibilidade());
    }

}

