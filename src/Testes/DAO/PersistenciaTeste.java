package Testes.DAO;

import DAO.*;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Operacoes.Localizacao;
import Model.Operacoes.Reserva;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.LivroServico;
import Servico.ReservaServico;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PersistenciaTeste {
    private LeitorDAO leitorDAO;
    private LivroDAO livroDAO;
    private Leitor leitor;
    private Livro livro;
    private Emprestimo emprestimo;
    private BibliotecarioDAO bibliotecarioDAO;
    private Bibliotecario bibliotecario;
    private AdministradorDAO administradorDAO;
    private Administrador administrador;
    private ReservaServico reservaServico;
    private Reserva reserva;
    @Before
    public void iniciarTestes() throws Exception {
        Persistencia.criarCache();
        leitorDAO = new LeitorDAO();
        livroDAO = new LivroDAO();
        Localizacao localizacao = new Localizacao("O", "46");
        livro = new Livro("Jogos V.", "Suzanne Collins", "9788579800245", "Ficcao Cientifica", "2012", "Rocco Jovens Leitores", localizacao);
        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda A.", "78642486597", endereco, "74999823548");
        emprestimo = new Emprestimo(livro, leitor);
        bibliotecarioDAO = new BibliotecarioDAO();
        bibliotecario = new Bibliotecario("Lisa J.", "123.456.789-09", "senha123");
        administradorDAO = new AdministradorDAO();
        administrador = new Administrador("Maria L.", "361.215.045-60", "senha123");
        LivroServico livroServico = new LivroServico(livroDAO);
        this.reservaServico = new ReservaServico(livroServico);
    }

    @Test
    public void testSalvarLeitorArquivo() throws Exception {
        leitorDAO.salvar(leitor);
        List<Leitor> listaLeitores = leitorDAO.getListaLeitores();
        Persistencia.salvarLeitor(listaLeitores);

        assertEquals(listaLeitores.size(), Persistencia.lerLeitor().size());
        assertNotNull(Persistencia.lerLeitor());
    }
    @Test
    public void testLerLeitorArquivo() throws Exception {
        leitorDAO.salvar(leitor);
        List<Leitor> listaLeitores = leitorDAO.getListaLeitores();
        Persistencia.salvarLeitor(listaLeitores);
        List<Leitor> listaArquivada = Persistencia.lerLeitor();

        for (int n = 0; n < listaLeitores.size(); n++) {
            assertEquals(listaLeitores.get(n).getNome(), listaArquivada.get(n).getNome());
            assertEquals(listaLeitores.get(n).getCpf(), listaArquivada.get(n).getCpf());
        }
        assertEquals(listaLeitores.size(), listaArquivada.size());
        assertNotNull(listaArquivada);
    }

    @Test
    public void testSalvarLivroArquivo() throws Exception {
        livroDAO.salvar(livro);
        List<Livro> acervo = livroDAO.getAcervo();
        Persistencia.salvarLivro(acervo);

        assertEquals(acervo.size(), Persistencia.lerLivro().size());
        assertEquals(acervo.get(0).getTitulo(), Persistencia.lerLivro().get(0).getTitulo());
    }

    @Test
    public void testLerLivroArquivo() throws Exception {
        livroDAO.salvar(livro);
        List<Livro> acervo = livroDAO.getAcervo();
        Persistencia.salvarLivro(acervo);

        List<Livro> listaArquivada = Persistencia.lerLivro();

        assertEquals(acervo.size(), listaArquivada.size());
        assertEquals(acervo.get(0).getTitulo(), listaArquivada.get(0).getTitulo());
        assertNotNull(listaArquivada);
    }

    @Test
    public void testSalvarBibliotecarioArquivo() throws Exception {
        bibliotecarioDAO.salvar(bibliotecario);
        List<Bibliotecario> listaBibliotecarios = bibliotecarioDAO.getBibliotecarios();
        Persistencia.salvarBibliotecario(listaBibliotecarios);

        assertEquals(listaBibliotecarios.size(), Persistencia.lerBibliotecario().size());
    }

    @Test
    public void testLerBibliotecarioArquivo() throws Exception {
        bibliotecarioDAO.salvar(bibliotecario);
        List<Bibliotecario> listaBibliotecarios = bibliotecarioDAO.getBibliotecarios();
        Persistencia.salvarBibliotecario(listaBibliotecarios);
        List<Bibliotecario> listaArquivada = Persistencia.lerBibliotecario();

        for (int n = 0; n < listaBibliotecarios.size(); n++) {
            assertEquals(listaBibliotecarios.get(n).getNome(), listaArquivada.get(n).getNome());
            assertEquals(listaBibliotecarios.get(n).getCpf(), listaArquivada.get(n).getCpf());
        }
        assertEquals(listaBibliotecarios.size(), listaArquivada.size());
        assertNotNull(listaArquivada);
    }

    @Test
    public void testSalvarAdministradorArquivo() throws Exception {
        administradorDAO.salvar(administrador);
        List<Administrador> listaAdministradores = administradorDAO.getAdministradores();
        Persistencia.salvarAdministrador(listaAdministradores);

        assertEquals(listaAdministradores.size(), Persistencia.lerAdministrador().size());
    }

    @Test
    public void testLerAdministradorArquivo() throws Exception {
        administradorDAO.salvar(administrador);
        List<Administrador> listaAdministradores = administradorDAO.getAdministradores();
        Persistencia.salvarAdministrador(listaAdministradores);
        List<Administrador> listaArquivada = Persistencia.lerAdministrador();

        for (int n = 0; n < listaAdministradores.size(); n++) {
            assertEquals(listaAdministradores.get(n).getNome(), listaArquivada.get(n).getNome());
            assertEquals(listaAdministradores.get(n).getCpf(), listaArquivada.get(n).getCpf());
        }
        assertEquals(listaAdministradores.size(), listaArquivada.size());
        assertNotNull(listaArquivada);
    }

    @Test
    public void testSalvarReservasPorTituloArquivo() throws Exception {
        livroDAO.salvar(livro);
        reserva = reservaServico.criarReserva(leitor, "Jogos V.");
        livroDAO.salvarReservasPorTituloArquivo();

        assertNotNull(Persistencia.lerReservasPorTitulo());
    }

    @Test
    public void testLerReservasPorTituloArquivo() throws Exception {
        livroDAO.salvar(livro);
        reserva = reservaServico.criarReserva(leitor, "Jogos V.");
        livroDAO.salvarReservasPorTituloArquivo();
        Queue<Leitor> reservasPorTitulo = livroDAO.getReservasPorTitulo("Jogos V.");
        Map<String, Queue<Leitor>> mapaArquivado = Persistencia.lerReservasPorTitulo();

        assertEquals(reservasPorTitulo.size(), mapaArquivado.size());
    }

}


