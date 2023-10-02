package Servico;

import DAO.AdministradorDAO;
import DAO.BibliotecarioDAO;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;

import java.util.List;


public class AdministradorServico extends BibliotecarioServico {
    private AdministradorDAO administradorDAO;
    private LeitorServico leitorServico;
    private LivroServico livroServico;

    public AdministradorServico(AdministradorDAO administradorDAO, BibliotecarioDAO bibliotecarioDAO, LeitorServico leitorServico, LivroServico livroServico, EmprestimoServico emprestimoServico) {
        super(bibliotecarioDAO, leitorServico, livroServico, emprestimoServico);
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
    public void registrarNovoBibliotecario(String nome, String cpf, String senha) throws Excecao {
        BibliotecarioServico bibliotecarioServico = getBibliotecarioServico();
        bibliotecarioServico.criarBibliotecario(nome, cpf, senha);
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

    public void cadastrarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        leitorServico.criarLeitor(nome, cpf, endereco, telefone);
    }
    public void removerLeitor(Leitor leitor) {
        leitorServico.deletarLeitor(leitor);
    }
    public void removerBibliotecario(Bibliotecario bibliotecario) {
        administradorDAO.deletar(bibliotecario);
    }
    public void removerLivro(Livro livro) {
        livroServico.deletarLivro(livro);
    }

}
