package Servico;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;


public class EmprestimoServico {
    private LivroServico livroServico;
    private LeitorServico leitorServico;

    public EmprestimoServico(LivroServico livroServico, LeitorServico leitorServico) {
        this.livroServico = livroServico;
        this.leitorServico = leitorServico;
    }

    public Emprestimo criarEmprestimo(Livro livro, Leitor leitor) throws Excecao {
        if (leitor.isStatusAcessoUsuario()) {
            if (livroServico.verificaPrimeiroDaFila(livro.getTitulo()) == leitor || livroServico.verificaPrimeiroDaFila(livro.getTitulo()) == null) {
                if (leitorServico.qtdEmprestimosAtivos() < Emprestimo.limiteEmprestimosPorLeitor) {
                    if (livro.isDisponibilidade()) {
                        livroServico.removePrimeiroDafila(livro.getTitulo());
                        Emprestimo emprestimo = new Emprestimo(livro, leitor);
                        leitorServico.adicionaHistoricoEmprestimos(emprestimo);
                        return emprestimo;
                    } else throw new Excecao("Livro:" + livro.getTitulo() + " indiponível para emprestimo no momento");

                } else throw new Excecao(leitor.getNome() + ", seu numero máximo de emprestimos ativos já foi atingido");

            } else throw new Excecao("Pessoas na fila: " + livroServico.qtdLeitoresNaFila(livro.getTitulo()) + ". " + leitor.getNome() + ", reserve o livro e aguarde para pegar emprestado quando disponivel.");

        } else throw new Excecao("Ops! " + leitor.getNome() + " nao pode receber emprestimos no momento.");
    }


}
