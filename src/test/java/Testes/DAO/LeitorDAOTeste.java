package Testes.DAO;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.DAO.LeitorDAO;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeitorDAOTeste {
    private LeitorDAO leitorDAO = DAO.getLeitorDAO();
    private Leitor leitor;
    private Livro livro;
    private Emprestimo emprestimo;

    public LeitorDAOTeste() throws Exception {
    }

    @Before
    public void iniciarDAO() throws Excecao {
        Localizacao localizacao = new Localizacao("N", "46");
        livro = new Livro("Jogos V.", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores", localizacao);
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        emprestimo = new Emprestimo(livro, leitor);
    }

    @Test
    public void salvarLeitor() {
        leitorDAO.salvar(leitor);
        assertTrue(leitorDAO.getListaLeitores().contains(leitor));
    }

    @Test
    public void deletarLeitor() {
        leitorDAO.salvar(leitor);
        leitorDAO.deletar(leitor);
        assertFalse(leitorDAO.getListaLeitores().contains(leitor));
    }

    @Test
    public void deletarTodosOsLeitores() {
        leitorDAO.salvar(leitor);
        leitorDAO.deletarTodos();
        assertTrue(leitorDAO.getListaLeitores().isEmpty());
    }

    @Test
    public void buscarLeitorPorId() {
        leitorDAO.salvar(leitor);
        assertEquals(leitor.getCpf(), leitorDAO.buscarPorId(leitor.getCpf()).getCpf());
    }

    @Test
    public void adicionarLeitorAoHistoricoEmprestimos() {
        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
        assertTrue(leitorDAO.getHistoricoEmprestimos(leitor).contains(emprestimo));
    }

    @Test
    public void VerificarEmprestimosAtivos() {
        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
        assertTrue(leitorDAO.getEmprestimosAtivos(leitor).contains(emprestimo));
    }

    @Test
    public void NumeroDeEmprestimosAtivos() {
        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
        assertEquals(1, leitorDAO.qtdEmprestimosAtivos(leitor));
    }

    @Test
    public void VerificarSeCpfEstaCadastrado() {
        leitorDAO.salvar(leitor);
        assertTrue(leitorDAO.cpfLeitorEstaCadastrado(leitor.getCpf()));
    }
}

