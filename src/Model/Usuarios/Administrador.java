package Model.Usuarios;

import DAO.AdministradorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Livro;

import java.util.List;


public class Administrador extends Bibliotecario{
    private AdministradorDAO administradorDAO;

    public Administrador(String nome, String cpf, String senha) throws Excecao {
        super(nome, cpf, senha);
        super.setCargo("Administrador");
        this.administradorDAO = new AdministradorDAO();
    }

    //GETTERS E SETTERS
    public List<Administrador> getAdministradores() {
        return administradorDAO.getAdministradores();
    }

    //gerenciar Leitores
    public void adicionarLeitor(Leitor leitor) {
        leitor.adicionarLeitor();
    }
    public void removerLeitor(Leitor leitor) {
        leitor.removerLeitor();
    }
    public void bloquearLeitor(Leitor leitor) {
        leitor.bloquearConta();
    }
    public void desbloquearLeitor(Leitor leitor) {
        leitor.desbloquearConta();
    }


    //gerenciar Bibliotecarios
    public void bloquearBibliotecario(Bibliotecario bibliotecario) {
        bibliotecario.bloquearConta();
    }
    public void desbloquerBibliotecario(Bibliotecario bibliotecario){
        bibliotecario.desbloquearConta();
    }
    public void cadastrarBibliotecario(Bibliotecario bibliotecario) {
        administradorDAO.registraBibliotecario(bibliotecario);
    }
    public void removerBibliotecario(Bibliotecario bibliotecario) {
        administradorDAO.removeBibliotecario(bibliotecario);
    }

    //gerenciar o catálogo de livros
    public void removerLivro(Livro livro) {
        if (super.isStatusAcessoUsuario())
            livro.removeDoAcervo();
        else
            System.out.println("Ops! " + super.getNome() + " nao possui permissao para realizar esta operacao, verifique seu cadastro e tente novamente :)");
    }

    public String toString() {
        return ("---------------------------------------------------------------------------------------------\n" +
                "Administrador(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
    }



}
