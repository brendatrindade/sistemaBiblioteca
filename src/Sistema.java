
//Minha bagunça, desconsiderar :)

import Controle.*;
import DAO.*;
import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Operacoes.Localizacao;
import Model.Operacoes.Reserva;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.*;

public class Sistema {
    public static void main(String[] args) throws Excecao {
        LeitorDAO leitorDAO = new LeitorDAO();
        LivroDAO livroDAO = new LivroDAO();
        BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
        AdministradorDAO administradorDAO = new AdministradorDAO();

        LeitorServico leitorServico = new LeitorServico(leitorDAO, livroDAO);
        LivroServico livroServico = new LivroServico(livroDAO);
        BibliotecarioServico bibliotecarioServico = new BibliotecarioServico(bibliotecarioDAO,leitorServico, livroServico);
        AdministradorServico administradorServico = new AdministradorServico(administradorDAO, bibliotecarioDAO, leitorServico,livroServico);
        EmprestimoServico emprestimoServico = new EmprestimoServico(livroServico, leitorServico);

        LeitorControle leitorControle = new LeitorControle(leitorServico);
        BibliotecarioControle bibliotecarioControle = new BibliotecarioControle(bibliotecarioServico);
        LivroControle livroControle = new LivroControle(livroServico);
        AdministradorControle administradorControle = new AdministradorControle(administradorServico, bibliotecarioServico);
        EmprestimoControle emprestimoControle = new EmprestimoControle(emprestimoServico);

        /*
        Leitor leitor1 = new Leitor("Joana", "05999254507aaa", new Endereco("Rua poste", "12", "Fsa", "BA "), "74 9901-3265");
        Leitor leitor2 = new Leitor("Larissa", "05999254507", new Endereco("Rua poste", "12", "Fsa", "BA "), "74 9901-3265");

        Livro livro1 = new Livro("Joana Aquariana", "Aquarela", "123", "romance", "2000", "edita tudo");
        Livro livro2 = new Livro("Joana Aquactor", "Aquarela", "124", "romance", "2000", "edita tudo");
        */

        livroControle.criarLivro("Joana Aquariana", "Aquarela", "123", "romance", "2000", "edita tudo", new Localizacao("J", "27"));
        bibliotecarioControle.registrarNovoLivro("Joana Aquactor", "Aquarela", "12221", "romance", "2000", "edita tudo", new Localizacao("J", "29"));

        administradorControle.criarBibliotecario("Brenda", "05999254507", "123");
        administradorControle.criarAdministrador("Brenda", "05999351545", "123");

        System.out.println(leitorControle.pesquisarLivros("\n aqua \t "));

        //System.out.println( administradorControle.getAdministradores());

        //Emprestimo emprestimo1 = new Emprestimo(livro1, leitor1);

        /*
        System.out.println(leitor1+"-"+leitor2);

        System.out.println(livro1+"-"+livro2);
        System.out.println("\n" + leitor1.getEmprestimosAtivos());


        Bibliotecario bibliotecario1 = new Bibliotecario("Brenda", "05999254507", "123");
        Administrador administrador1 = new Administrador("Ana", "05999351545", "123");

        bibliotecario1.emprestarLivro(livro1, leitor1);
        administrador1.emprestarLivro(livro2, leitor2);

        Reserva reserva1 = new Reserva(leitor1, "Joana Aventureira");

        System.out.println("\n" + reserva1 + "\n");

        bibliotecario1.emprestarLivro(livro1, leitor1);

        bibliotecario1.emprestarLivro(livro1, leitor2);

        bibliotecario1.emprestarLivro(livro1, leitor1);

        */



        //bibliotecario1.devolverLivro(livro1, leitor1);

        //bibliotecario1.devolverLivro(livro1, leitor1);

        //System.out.println(leitor1.getEmprestimosAtivos());

        //System.out.println(leitor1.getHistoricoEmprestimos());


        //System.out.println(bibliotecario1.getBibliotecarios());
        //System.out.println(administrador1.getAdministradores());

    }

}