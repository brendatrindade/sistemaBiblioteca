package Servico;

import DAO.BibliotecarioDAO;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Operacoes.Localizacao;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Leitor;

import java.util.List;
import java.util.Map;

public class BibliotecarioServico {
    private BibliotecarioDAO bibliotecarioDAO;
    private EmprestimoServico emprestimoServico;
    private LeitorServico leitorServico;
    private LivroServico livroServico;

    public BibliotecarioServico (BibliotecarioDAO bibliotecarioDAO, LeitorServico leitorServico, LivroServico livroServico, EmprestimoServico emprestimoServico) {
        this.bibliotecarioDAO = bibliotecarioDAO;
        this.leitorServico = leitorServico;
        this.livroServico = livroServico;
        this.emprestimoServico = emprestimoServico;
    }

    public BibliotecarioServico getBibliotecarioServico(){
        return this;
    }

    public Bibliotecario criarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        if (cpfOperadorEstaCadastrado(cpf)) {
        }
        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, senha);
        salvarBibliotecario(bibliotecario);
        return bibliotecario;
    }
    public void registrarNovoLivro(String titulo, String autor,String isbn,String categoria, String anoPublicacao, String editora, Localizacao localizacao){
        livroServico.criarLivro(titulo, autor, isbn, categoria, anoPublicacao, editora, localizacao);
    }

    public void salvarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarioDAO.salvar(bibliotecario);
    }

    public void deletarBibliotecario(Bibliotecario bibliotecario) {
        bibliotecarioDAO.deletar(bibliotecario);
    }

    public void deletarTodosBibliotecarioes() {
        bibliotecarioDAO.deletarTodos();
    }

    public Bibliotecario buscarBibliotecarioPorId(String cpf) {
        return bibliotecarioDAO.buscarPorId(cpf);
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarioDAO.getBibliotecarios();
    }

    public Emprestimo registrarEmprestimo(Livro livro, Leitor leitor) throws Excecao {
        Emprestimo novoEmprestimo = emprestimoServico.criarEmprestimo(livro, leitor);
        return novoEmprestimo;
    }

    public void devolverLivro(Livro livro, Leitor leitor) {
        for (Emprestimo emprestimo : leitorServico.getEmprestimosAtivos(leitor)) {
            if (emprestimo.getLivro().equals(livro)) {
                emprestimo.registrarDevolucao();
                return;
            }
        }
        System.out.println("Livro com devolução pendende não localizado");
    }

    public void devolverLivroPorTitulo(String titulo, Leitor leitor) {
        for (Emprestimo emprestimo : leitorServico.getEmprestimosAtivos(leitor)) {
            if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(titulo)) {
                emprestimo.registrarDevolucao();
                return;
            }
        }
        System.out.println("Livro com devolução pendende não localizado");
    }

    public Map<String, List<Livro>> pesquisarLivros(String texto) {
        return leitorServico.pesquisarLivros(texto);
    }

    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return bibliotecarioDAO.cpfOperadorEstaCadastrado(cpf);
    }


}
