package Model.Usuarios;

import Excecoes.Excecao;
import Model.Operacoes.Livro;

import java.util.List;


public class Administrador extends Bibliotecario{
    private boolean cadastroRealizado;

    public Administrador(String nome, String cpf, String senha) throws Excecao {
        super(nome, cpf, senha);
        super.setCargo("Administrador");
        this.cadastroRealizado = true;

    }

    //gerenciar Leitores
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



    public String toString() {
        if (cadastroRealizado) {
            return ("\nAdministrador(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
        }
        return ("Administrador(a) não cadastrado");
    }

}

/*

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
    public void cadastrarBibliotecario(String nome, String cpf, String senha) throws Excecao {
        Bibliotecario bibliotecario = new Bibliotecario(nome, cpf, senha);
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

 */