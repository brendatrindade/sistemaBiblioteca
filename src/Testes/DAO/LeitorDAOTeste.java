package Testes.DAO;
import DAO.LeitorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeitorDAOTeste {
    private LeitorDAO leitorDAO;
    private Leitor leitor;
    private Livro livro;
    private Emprestimo emprestimo;
    @BeforeEach
    void iniciarDAO() throws Excecao {
        leitorDAO = new LeitorDAO();
        livro = new Livro("Jogos Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        emprestimo = new Emprestimo(livro, leitor);
    }

    @Test
    void salvarLeitor() {
        leitorDAO.salvar(leitor);
        assertTrue(leitorDAO.getListaLeitores().contains(leitor));
    }

    @Test
    void deletarLeitor() {
        leitorDAO.salvar(leitor);
        leitorDAO.deletar(leitor);
        assertFalse(leitorDAO.getListaLeitores().contains(leitor));
    }

    @Test
    void deletarTodosOsLeitores() {
        leitorDAO.salvar(leitor);
        leitorDAO.deletarTodos();
        assertTrue(leitorDAO.getListaLeitores().isEmpty());
    }

    @Test
    void buscarLeitorPorId() {
        leitorDAO.salvar(leitor);
        assertEquals(leitor.getCpf(), leitorDAO.buscarPorId(leitor.getCpf()).getCpf());
    }

    @Test
    void adicionarLeitorAoHistoricoEmprestimos() {
        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
        assertTrue(leitorDAO.getHistoricoEmprestimos(leitor).contains(emprestimo));
    }

    @Test
    void VerificarEmprestimosAtivos() {
        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
        assertTrue(leitorDAO.getEmprestimosAtivos(leitor).contains(emprestimo));
    }

    @Test
    void NumeroDeEmprestimosAtivos() {
        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
        assertEquals(1, leitorDAO.qtdEmprestimosAtivos(leitor));
    }

    @Test
    void VerificarSeCpfEstaCadastrado() {
        assertThrows(Excecao.class, () -> {
            leitorDAO.salvar(leitor);
            leitorDAO.cpfLeitorEstaCadastrado(leitor.getCpf());
        });
    }
}

