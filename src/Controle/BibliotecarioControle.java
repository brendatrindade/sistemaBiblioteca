package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Leitor;
import Servico.BibliotecarioServico;

import java.util.List;
import java.util.Map;

public class BibliotecarioControle {
    private BibliotecarioServico bibliotecarioServico;

    public BibliotecarioControle(BibliotecarioServico bibliotecarioServico) {
        this.bibliotecarioServico = bibliotecarioServico;
    }

    public void criarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        try {
            Bibliotecario bibliotecario = bibliotecarioServico.criarBibliotecario(nome, cpf, senha);
            System.out.println(bibliotecario.getNome() + " - Cadastro efetuado com sucesso!");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }

    public void registrarNovoLivro(String titulo, String autor,String isbn,String categoria,String anoPublicacao, String editora){
        bibliotecarioServico.registrarNovoLivro(titulo, autor, isbn, categoria, anoPublicacao, editora);
    }

    public void salvarBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioServico.salvarBibliotecario(bibliotecario);
    }
    public void deletarBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioServico.deletarBibliotecario(bibliotecario);
    }
    public void deletarTodosBibliotecarioes(){
        bibliotecarioServico.deletarTodosBibliotecarioes();
    }
    public Bibliotecario buscarBibliotecarioPorId(String cpf){
        return bibliotecarioServico.buscarBibliotecarioPorId(cpf);
    }
    public List<Bibliotecario> getBibliotecarios(){
        return bibliotecarioServico.getBibliotecarios();
    }

    public void devolverLivro(Livro livro, Leitor leitor){
        bibliotecarioServico.devolverLivro(livro, leitor);
    }

    public void devolverLivroPorTitulo(String titulo, Leitor leitor) {
        bibliotecarioServico.devolverLivroPorTitulo(titulo, leitor);
    }
    public Map<String, List<Livro>> pesquisarLivros(String texto) {
        return bibliotecarioServico.pesquisarLivros(texto);
    }

    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return bibliotecarioServico.cpfOperadorEstaCadastrado(cpf);
    }

}
