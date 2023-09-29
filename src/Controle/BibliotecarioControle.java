package Controle;

import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;
import Servico.BibliotecarioServico;

import java.util.List;

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
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return bibliotecarioServico.cpfOperadorEstaCadastrado(cpf);
    }
    
    
}
