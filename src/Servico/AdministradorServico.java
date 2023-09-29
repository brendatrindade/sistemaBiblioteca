package Servico;

import DAO.AdministradorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Leitor;

import java.util.List;


public class AdministradorServico {
    private AdministradorDAO administradorDAO;
    private LeitorServico leitorServico;
    private LivroServico livroServico;

    public AdministradorServico(AdministradorDAO administradorDAO, LeitorServico leitorServico, LivroServico livroServico) {
        this.administradorDAO = administradorDAO;
        this.leitorServico = leitorServico;
        this.livroServico = livroServico;
    }
    public Administrador criarAdministrador(String nome, String cpf, String senha) throws Excecao {
        if (cpfOperadorEstaCadastrado(cpf)) {
        }
        Administrador administrador = new Administrador(nome, cpf, senha);
        salvarAdministrador(administrador);
        return administrador;
    }

    public void salvarAdministrador(Administrador administrador){
        administradorDAO.salvarAdiministrador(administrador);
    }
    public void deletarAdministrador(Administrador administrador){
        administradorDAO.deletarAdministrador(administrador);
    }
    public void deletarTodosAdministradores(){
        administradorDAO.deletarTodosAdministradores();
    }
    public Administrador buscarAdministradorPorId(String cpf){
        return administradorDAO.buscarAdministradorPorId(cpf);
    }
    public List<Administrador> getAdministradores(){
        return administradorDAO.getAdministradores();
    }
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        return administradorDAO.cpfOperadorEstaCadastrado(cpf);
    }


    public void adicionarLeitor(Leitor leitor) {
        leitorServico.salvarLeitor(leitor);
    }
    public void removerLeitor(Leitor leitor) {
        leitorServico.deletarLeitor(leitor);
    }
    public void cadastrarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, senha);
    }
    public void removerBibliotecario(Bibliotecario bibliotecario) {
        administradorDAO.deletar(bibliotecario);
    }
    public void removerLivro(Livro livro) {
        livroServico.deletarLivro(livro);
    }

}
