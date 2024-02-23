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

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;


public class LivroDAOTeste {
    private LivroDAO livroDAO2 = new LivroDAO();
    private Livro livro;
    private Leitor leitor;
    private Leitor leitor2;

    public LivroDAOTeste() throws Exception {
    }

    @Before
    public void iniciarDAO() throws Excecao {
        Localizacao localizacao = new Localizacao("F", "81");
        livro = new Livro("Jogos Vorazes", "Livro DAO Teste", "9788579800245", "Livro DAO Teste", "2012", "Rocco Jovens Leitores", localizacao);
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        leitor2 = new Leitor("Rafaela", "624.673.930-03", endereco, "75999459548");
    }

    @Test
    public void salvarLivroDuplicado() {
        livroDAO2.deletarTodos();
        livroDAO2.salvar(livro);
        livroDAO2.salvar(livro);
        livroDAO2.salvar(livro);
        livroDAO2.salvar(livro);
        assertEquals(1, livroDAO2.getAcervo().size());
    }

    @Test
    public void salvarLivro() {
        livroDAO2.salvar(livro);
        assertTrue(livroDAO2.getAcervo().contains(livro));
    }
    @Test
    public void deletarLivro() {
        livroDAO2.salvar(livro);
        livroDAO2.deletar(livro);
        assertFalse(livroDAO2.getAcervo().contains(livro));
    }

    @Test
    public void deletarTodosOsLivro() {
        livroDAO2.salvar(livro);
        livroDAO2.deletarTodos();
        assertTrue(livroDAO2.getAcervo().isEmpty());
    }

    @Test
    public void atualizarTituloLivro() {
        livroDAO2.atualizarTituloLivro(livro, "Novo Titulo");
        assertEquals("Novo Titulo", livro.getTitulo());
    }


    @Test
    public void buscarLivroPorTitulo() throws Exception {
        List<Livro> livros = livroDAO2.buscarLivroPorTitulo(livro.getTitulo());
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                assertEquals(l.getTitulo(), livro.getTitulo());
            }
        }
    }

    @Test
    public void pesquisarLivros() throws Exception {
        livroDAO2.salvar(livro);
        Map<String, List<Livro>> resultados = livroDAO2.pesquisarLivros(livro.getAutor());

        if (!resultados.isEmpty()) {
            for (Livro l : resultados.get("Autoria")) {
                if (l.getTitulo().equalsIgnoreCase(livro.getAutor())) {
                    assertEquals(l.getAutor(), livro.getAutor());
                }
            }
        }

    }

    @Test
    public void AcervoPossuiLivro() {
        livroDAO2.salvar(livro);
        assertTrue(livroDAO2.acervoPossuiLivro(livro));
    }


}


