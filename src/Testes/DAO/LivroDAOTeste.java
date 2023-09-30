package Testes.DAO;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class LivroDAOTeste {
    private LivroDAO livroDAO;
    private Livro livro;
    private Leitor leitor;
    private Leitor leitor2;

    @BeforeEach
    void iniciarDAO() throws Excecao {
        livroDAO = new LivroDAO();
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        leitor2 = new Leitor("Rafaela", "624.673.930-03", endereco, "75999459548");
    }

    @Test
    void salvarLivro() {
        livroDAO.salvar(livro);
        assertTrue(livroDAO.getAcervo().contains(livro));
    }
    @Before
    public void salvarLivroDuplicado() {
        livroDAO.salvar(livro);
        livroDAO.salvar(livro);
        livroDAO.salvar(livro);
        assertEquals(1, livroDAO.getAcervo().size());
    }
    @Test
    void deletarLivro() {
        livroDAO.salvar(livro);
        livroDAO.deletar(livro);
        assertFalse(livroDAO.getAcervo().contains(livro));
    }

    @Test
    void deletarTodosOsLivro() {
        livroDAO.salvar(livro);
        livroDAO.deletarTodos();
        assertTrue(livroDAO.getAcervo().isEmpty());
    }

    @Test
    void atualizarTituloLivro() {
        livroDAO.atualizarTituloLivro(livro, "Novo Titulo");
        assertEquals("Novo Titulo", livro.getTitulo());
    }


    @Test
    void buscarLivroPorTitulo() {
        livroDAO.salvar(livro);
        List<Livro> livros = livroDAO.buscarLivroPorTitulo(livro.getTitulo());
        assertTrue(livros.contains(livro));
    }

    @Test
    void pesquisarLivros() {
        livroDAO.salvar(livro);
        Map<String, List<Livro>> resultados = livroDAO.pesquisarLivros(livro.getTitulo());
        assertTrue(resultados.get("\nTitulos: ").contains(livro));
    }

    @Test
    void AcervoPossuiLivro() {
        livroDAO.salvar(livro);
        assertTrue(livroDAO.possuiLivro(livro));
    }

    @Test
    void getReservasPorTitulo() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);

        assertEquals(leitores, livroDAO.getReservasPorTitulo(livro.getTitulo()));
    }

    @Test
    void verificaPrimeiroDaFila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        assertEquals(leitor, livroDAO.verificaPrimeiroDaFila(livro.getTitulo()));
    }

    @Test
    void nomesNaFila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        assertTrue(livroDAO.nomesNaFila(livro.getTitulo()).contains(leitor.getNome()));
        assertTrue(livroDAO.nomesNaFila(livro.getTitulo()).contains(leitor2.getNome()));
    }

    @Test
    void qtdLeitoresNaFila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        assertEquals(2, livroDAO.qtdLeitoresNaFila(livro.getTitulo()));
    }

    @Test
    void removePrimeiroDafila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        livroDAO.removePrimeiroDafila(livro.getTitulo());
        assertFalse(livroDAO.nomesNaFila(livro.getTitulo()).contains(leitor.getNome()));
        assertEquals(1, livroDAO.getReservasPorTitulo(livro.getTitulo()).size());
    }

}


