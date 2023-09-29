package Servico;

import DAO.BibliotecarioDAO;
import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;

import java.util.List;

public class BibliotecarioServico {
    private BibliotecarioDAO bibliotecarioDAO;
    private LeitorServico leitorServico;
    private LivroServico livroServico;

    public BibliotecarioServico(BibliotecarioDAO bibliotecarioDAO, LeitorServico leitorServico, LivroServico livroServico) {
        this.bibliotecarioDAO = bibliotecarioDAO;
        this.leitorServico = leitorServico;
        this.livroServico = livroServico;
    }

    public Bibliotecario criarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        if (cpfOperadorEstaCadastrado(cpf)) {
        }
        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, senha);
        salvarBibliotecario(bibliotecario);
        return bibliotecario;
    }

    public void salvarBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioDAO.salvar(bibliotecario);
    }
    public void deletarBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioDAO.deletar(bibliotecario);
    }
    public void deletarTodosBibliotecarioes(){
        bibliotecarioDAO.deletarTodos();
    }
    public Bibliotecario buscarBibliotecarioPorId(String cpf){
        return bibliotecarioDAO.buscarPorId(cpf);
    }
    public List<Bibliotecario> getBibliotecarios(){
        return bibliotecarioDAO.getBibliotecarios();
    }
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return bibliotecarioDAO.cpfOperadorEstaCadastrado(cpf);
    }


}
