/**
 * BibliotecarioControle
 *
 * @author Brenda Araújo Trindade Oliveira
 * @version 1.0
 * @since 02/10/2023
 *
 * Direitos autorais (c) 2023 Brenda Araújo Trindade Oliveira. Todos os direitos reservados.
 * Este software é confidencial e proprietário de Brenda Araújo Trindade Oliveira.
 * Este software é protegido sob direitos autorais.
 *
 */
package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Operacoes.Localizacao;
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
    public Emprestimo registrarEmprestimo(Livro livro, Leitor leitor) throws Excecao {
        Emprestimo novoEmprestimo = bibliotecarioServico.registrarEmprestimo(livro, leitor);
        return novoEmprestimo;
    }

    public void registrarNovoLivro(String titulo, String autor,String isbn,String categoria, String anoPublicacao, String editora, Localizacao localizacao){
        bibliotecarioServico.registrarNovoLivro(titulo, autor, isbn, categoria, anoPublicacao, editora, localizacao);
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
