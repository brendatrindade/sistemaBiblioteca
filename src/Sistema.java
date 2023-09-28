import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Model.Operacoes.Emprestimo;

public class Sistema {
    public static void main(String[] args) throws Exception {

        Leitor leitor1 = new Leitor("Joana", "05999254507", new Endereco("Rua poste", "12", "Fsa", "BA "), "74 9901-3265");
        Leitor leitor2 = new Leitor("Joana", "05999254507", new Endereco("Rua poste", "12", "Fsa", "BA "), "74 9901-3265");

        Livro livro1 = new Livro("Joana Aventureira", "Aquarela", "123", "romance", "2000", "edita tudo");
        Livro livro2 = new Livro("Joana Aventureira", "Aquarela", "124", "romance", "2000", "edita tudo");

        /*
        Emprestimo emprestimo1 = new Emprestimo(livro1, leitor1);

        System.out.println(leitor1+"-"+leitor2);
        System.out.println(livro1+"-"+livro2);
        System.out.println("\n" + leitor1.getEmprestimosAtivos());
        */

        Bibliotecario bibliotecario1 = new Bibliotecario("Brenda", "05999254507", "123");
        Administrador administrador1 = new Administrador("Ana", "05999351545", "123");

        bibliotecario1.emprestarLivro(livro1, leitor1);
        administrador1.emprestarLivro(livro2, leitor2);


        bibliotecario1.devolverLivro(livro1, leitor1);
        bibliotecario1.devolverLivro(livro1, leitor1);

        bibliotecario1.bloquearConta();

        bibliotecario1.devolverLivro(livro1, leitor1);

        System.out.println(leitor1.getEmprestimosAtivos());

        //System.out.println(leitor1.getHistoricoEmprestimos());


        //System.out.println(bibliotecario1.getBibliotecarios());
        //System.out.println(administrador1.getAdministradores());

    }
}