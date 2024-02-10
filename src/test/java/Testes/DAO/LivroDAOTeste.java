package Testes.DAO;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.DAO.LivroDAO;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.junit.Assert.*;


public class LivroDAOTeste {
    private LivroDAO livroDAO = DAO.getLivroDAO();
    private Livro livro;
    private Leitor leitor;
    private Leitor leitor2;

    public LivroDAOTeste() throws Exception {
    }

    @Before
    public void iniciarDAO() throws Excecao {
        Localizacao localizacao = new Localizacao("F", "81");
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores", localizacao);
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        leitor2 = new Leitor("Rafaela", "624.673.930-03", endereco, "75999459548");
    }

    @Test
    public void salvarLivroDuplicado() {
        livroDAO.deletarTodos();
        livroDAO.salvar(livro);
        livroDAO.salvar(livro);
        livroDAO.salvar(livro);
        livroDAO.salvar(livro);
        assertEquals(1, livroDAO.getAcervo().size());
    }
    @Test
    public void salvarLivro() {
        livroDAO.salvar(livro);
        assertTrue(livroDAO.getAcervo().contains(livro));
    }
    @Test
    public void deletarLivro() {
        livroDAO.salvar(livro);
        livroDAO.deletar(livro);

        assertFalse(livroDAO.getAcervo().contains(livro));
    }

    @Test
    public void deletarTodosOsLivro() {
        livroDAO.salvar(livro);
        livroDAO.deletarTodos();
        assertTrue(livroDAO.getAcervo().isEmpty());
    }

    @Test
    public void atualizarTituloLivro() {
        livroDAO.atualizarTituloLivro(livro, "Novo Titulo");
        assertEquals("Novo Titulo", livro.getTitulo());
    }


    @Test
    public void buscarLivroPorTitulo() {
        livroDAO.salvar(livro);
        List<Livro> livros = livroDAO.buscarLivroPorTitulo(livro.getTitulo());
        assertTrue(livros.contains(livro));
    }

    @Test
    public void pesquisarLivros() {
        livroDAO.salvar(livro);
        Map<String, List<Livro>> resultados = livroDAO.pesquisarLivros(livro.getTitulo());
        assertTrue(resultados.get("Titulos").contains(livro));
    }

    @Test
    public void AcervoPossuiLivro() {
        livroDAO.salvar(livro);
        assertTrue(livroDAO.possuiLivro(livro));
    }

    @Test
    public void getReservasPorTitulo() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);

        assertEquals(leitores, livroDAO.getReservasPorTitulo(livro.getTitulo()));
    }

    @Test
    public void verificaPrimeiroDaFila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        assertEquals(leitor, livroDAO.verificaPrimeiroDaFila(livro.getTitulo()));
    }

    @Test
    public void nomesNaFila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        assertTrue(livroDAO.nomesNaFila(livro.getTitulo()).contains(leitor.getNome()));
        assertTrue(livroDAO.nomesNaFila(livro.getTitulo()).contains(leitor2.getNome()));
    }

    @Test
    public void qtdLeitoresNaFila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        assertEquals(2, livroDAO.qtdLeitoresNaFila(livro.getTitulo()));
    }

    @Test
    public void removePrimeiroDafila() {
        Queue<Leitor> leitores = new LinkedList<>();
        leitores.add(leitor);
        leitores.add(leitor2);
        livroDAO.setLeitoresReservasPorTitulo(livro.getTitulo(), leitores);
        livroDAO.removePrimeiroDafila(livro.getTitulo());
        assertFalse(livroDAO.nomesNaFila(livro.getTitulo()).contains(leitor.getNome()));
        assertEquals(1, livroDAO.getReservasPorTitulo(livro.getTitulo()).size());
    }

}


