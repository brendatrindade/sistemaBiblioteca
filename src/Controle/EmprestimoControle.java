package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;
import Servico.EmprestimoServico;

public class EmprestimoControle {
    private EmprestimoServico emprestimoServico;

    public EmprestimoControle(EmprestimoServico emprestimoServico) {
        this.emprestimoServico = emprestimoServico;
    }

    public void criarEmprestimo(Livro livro, Leitor leitor) throws Excecao {
        try {
            Emprestimo emprestimo = emprestimoServico.criarEmprestimo(livro, leitor);
            System.out.println("Empréstimo do livro: " + livro.getTitulo() + " realizado com sucesso!");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }



}
