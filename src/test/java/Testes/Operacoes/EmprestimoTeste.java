package Testes.Operacoes;

import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
public class EmprestimoTeste {

    private Emprestimo emprestimo;
    private Livro livro;
    private Leitor leitor;

    @Before
    public void testCriarEmprestimo() throws Excecao {
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        Localizacao localizacao = new Localizacao("C", "24");
        livro = new Livro("J. Vorazes", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores", localizacao);
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
    @Test
    public void testRenovarEmprestimo() throws Exception {
        LocalDate dataEsperadaAnterior = emprestimo.getDataEsperadaDev();
        boolean renovado = emprestimo.solicitarRenovacao(emprestimo);
        LocalDate dataEsperadaPosRenovacao = emprestimo.getDataEsperadaDev();

        assertTrue(renovado);
        assertEquals(LocalDate.now(), emprestimo.getDataRenovacao1());
        assertEquals(1, emprestimo.getNumeroDeRenovacoes());
        assertEquals(dataEsperadaAnterior.plusDays(3), dataEsperadaPosRenovacao);
    }

    @Test
    public void testRenovarSegundaVezEmprestimo() throws Exception {
        boolean renovado = emprestimo.solicitarRenovacao(emprestimo);
        LocalDate dataEsperadaAnterior = emprestimo.getDataEsperadaDev();
        boolean renovado2 = emprestimo.solicitarRenovacao(emprestimo);

        LocalDate dataEsperadaPosRenovacao = emprestimo.getDataEsperadaDev();

        assertTrue(renovado);
        assertTrue(renovado2);
        assertEquals(LocalDate.now(), emprestimo.getDataRenovacao1());
        assertEquals(LocalDate.now(), emprestimo.getDataRenovacao2());
        assertEquals(2, emprestimo.getNumeroDeRenovacoes());
        assertEquals(dataEsperadaAnterior.plusDays(3), dataEsperadaPosRenovacao);
    }

    @Test
    public void testRenovarTerceiraVezEmprestimo() throws Exception {
        boolean renovado = emprestimo.solicitarRenovacao(emprestimo);
        LocalDate dataEsperadaAnterior = emprestimo.getDataEsperadaDev();
        boolean renovado2 = emprestimo.solicitarRenovacao(emprestimo);
        boolean renovado3 = emprestimo.solicitarRenovacao(emprestimo);
        LocalDate dataEsperadaPosRenovacao = emprestimo.getDataEsperadaDev();

        assertTrue(renovado);
        assertTrue(renovado2);
        assertFalse(renovado3);
        assertEquals(LocalDate.now(), emprestimo.getDataRenovacao1());
        assertEquals(LocalDate.now(), emprestimo.getDataRenovacao2());
        assertEquals(2, emprestimo.getNumeroDeRenovacoes());
        assertEquals(dataEsperadaAnterior.plusDays(3), dataEsperadaPosRenovacao);
    }

}

