package Testes.DAO;

import DAO.LeitorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeitorDAOTeste {
    private LeitorDAO leitorDAO;
    private Leitor leitor;
    private Livro livro;
    private Emprestimo emprestimo;
    @Before
    public void iniciarDAO() throws Excecao {
        leitorDAO = new LeitorDAO();
        livro = new Livro("Jogos V.", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
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
        assertThrows(Excecao.class, () -> {
            leitorDAO.salvar(leitor);
            leitorDAO.cpfLeitorEstaCadastrado(leitor.getCpf());
        });
    }
}

