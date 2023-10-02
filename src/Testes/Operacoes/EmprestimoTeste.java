package Testes.Operacoes;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class EmprestimoTeste {

    private Emprestimo emprestimo;
    private Livro livro;
    private Leitor leitor;

    @Before
    public void testCriarEmprestimo() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        livro = new Livro("J. Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");
        emprestimo = new Emprestimo(livro, leitor);

    }
    @Test
    public void verificarDadosDoEmprestimo(){

        assertEquals(livro, emprestimo.getLivro());
        assertEquals(leitor ,emprestimo.getLeitor());
        assertFalse(emprestimo.isstatusEmprestimoFinalizado());
    }
    @Test
    public void testRegistrarDevolucao() {
        emprestimo.registrarDevolucao();
        assertTrue(emprestimo.isstatusEmprestimoFinalizado());
        assertTrue(livro.isDisponibilidade());
    }

}

