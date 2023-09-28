package Model.Usuarios;

import DAO.BibliotecarioDAO;
import Excecoes.Excecao;
import Model.Operacoes.*;

import java.util.ArrayList;
import java.util.List;

public class Bibliotecario extends Usuario {

    // Herda de Usuario
    // Responsabilidades: Acesso às funcionalidades
    // #1 (Registro de Livros)
    // #2 (Pesquisa de Livros) ----> Usuario
    // #3 (Empréstimo e Devolução)

    private String cargo = "Bibliotecario";
    private String senha;
    private BibliotecarioDAO bibliotecarioDAO;

    public Bibliotecario(String nome, String cpf, String senha) throws Excecao {
        this.bibliotecarioDAO = new BibliotecarioDAO();
        try{
            cpfOperadorEstaCadastrado(cpf);
            super.setNome(nome);
            super.setCpf(cpf);
            this.senha = senha;
            bibliotecarioDAO.adiciona(this);
            System.out.println( nome + " - Cadastro efetuado com sucesso!");
        }
        catch (Excecao excecao) {
            System.out.println(excecao.getMessage());
        }
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarioDAO.getBibliotecarios();
    }

    //#2 Emprestimo e Devolução
    public void emprestarLivro(Livro livro, Leitor leitor) throws Excecao {
        if(this.isStatusAcessoUsuario()) {
            Emprestimo emprestandoLivro = new Emprestimo(livro, leitor);
        }
        else System.out.println("Nao foi possivel realizar o emprestimo");
    }
    public void devolverLivro(Livro livro, Leitor leitor){
        if(this.isStatusAcessoUsuario()) {
            for (Emprestimo emprestimo : leitor.getEmprestimosAtivos()){
                if ( emprestimo.getLivro() == livro){
                    emprestimo.registrarDevolucao();
                    return;
                }
            }
            System.out.println("Livro com devolução pendende não localizado");
            return;
        }
        System.out.println("Ops! " + getNome() + " não pode realizar operações no momento.");
    }

    public void devolverLivroPorISBN(String isbn, Leitor leitor){
        if(this.isStatusAcessoUsuario()) {
            for (Emprestimo emprestimo : leitor.getEmprestimosAtivos()){
                if(emprestimo.getLivro().getIsbn().equalsIgnoreCase(isbn)) {
                    emprestimo.registrarDevolucao();
                    System.out.println("Devolução concluída com sucesso!");
                }
                else
                    System.out.println("Livro não encontrado");
            }
        }
    }

    //#1 Registro de Livros

    // Adicionar um livro ao catálogo
    public void registrarLivro(String titulo, String autor,String isbn,  String categoria, String anoPublicacao, String editora) throws Excecao {
        if (super.isStatusAcessoUsuario()){
            Livro novoLivro = new Livro(titulo, autor, isbn, categoria, anoPublicacao, editora);
            novoLivro.adicionaAoAcervo();
        }
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }
    //Atualizar Informações do livro
    public void atualizarTituloLivro( Livro livro, String novoTitulo) {
        if (super.isStatusAcessoUsuario())
            livro.atualizarTitulo(novoTitulo);
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }
    public void atualizarAutorLivro(Livro livro, String novoAutor) {
        if (super.isStatusAcessoUsuario())
            livro.atualizarAutor(novoAutor);
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }
    public void atualizarISBNLivro( Livro livro, String isbn) {
        if (super.isStatusAcessoUsuario())
            livro.atualizarIsbn(isbn);
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }
    public void atualizarCategoriaLivro(Livro livro, String novaCategoria) {
        if (super.isStatusAcessoUsuario())
            livro.atualizarCategoria(novaCategoria);
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }
    public void atualizarAnoPublicacaoLivro(Livro livro, String novoAno) {
        if (super.isStatusAcessoUsuario())
            livro.atualizarAnoPublicacao(novoAno);
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }

    //Verificação para controle de cadastro
    public void cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        if (bibliotecarioDAO.get() != null){
            for (Bibliotecario operador : bibliotecarioDAO.get()) {
                if (operador.getCpf().equals(cpf))
                    throw new Excecao(operador.getNome() + ", o CPF informado ja esta cadastrado como operador do sistema.");
            }
        }
    }

    public String toString() {
        return ("---------------------------------------------------------------------------------------------\n" +
                "Bibliotecario(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
    }
}
