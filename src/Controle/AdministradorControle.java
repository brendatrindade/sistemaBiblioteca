package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.AdministradorServico;
import Servico.BibliotecarioServico;

import java.util.List;

public class AdministradorControle extends BibliotecarioControle {
    private AdministradorServico administradorServico;

    public AdministradorControle(AdministradorServico administradorServico, BibliotecarioServico bibliotecarioServico) {
        super(bibliotecarioServico);
        this.administradorServico = administradorServico;
    }
    public void criarAdministrador(String nome, String cpf, String senha) throws Excecao {
        try {
            Administrador administrador = administradorServico.criarAdministrador(nome, cpf, senha);
            System.out.println(administrador.getNome() + " - Cadastro efetuado com sucesso!");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }

    public void salvarAdministrador(Administrador administrador){
        administradorServico.salvarAdministrador(administrador);
    }
    public void deletarAdministrador(Administrador administrador){
        administradorServico.deletarAdministrador(administrador);
    }
    public void deletarTodosAdministradores(){
        administradorServico.deletarTodosAdministradores();
    }
    public Administrador buscarAdministradorPorId(String cpf){
        return administradorServico.buscarAdministradorPorId(cpf);
    }
    public List<Administrador> getAdministradores(){
        return administradorServico.getAdministradores();
    }
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return administradorServico.cpfOperadorEstaCadastrado(cpf);
    }

    public void cadastrarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        administradorServico.cadastrarLeitor(nome, cpf, endereco, telefone);
    }
    public void removerLeitor(Leitor leitor) {
        administradorServico.removerLeitor(leitor);
    }

    public void cadastrarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        administradorServico.registrarNovoBibliotecario(nome, cpf, senha);
    }
    public void removerBibliotecario(Bibliotecario bibliotecario) {
        administradorServico.removerBibliotecario(bibliotecario);
    }
    public void removerLivro(Livro livro) {
        administradorServico.removerLivro(livro);
    }


}
