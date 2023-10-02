package Servico;

import DAO.LeitorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;

import java.util.List;


public class EmprestimoServico {
    private LivroServico livroServico;
    private LeitorDAO leitorDAO;

    public EmprestimoServico(LivroServico livroServico, LeitorDAO leitorDAO) {
        this.livroServico = livroServico;
        this.leitorDAO = leitorDAO;
    }

    public Emprestimo criarEmprestimo(Livro livro, Leitor leitor) throws Excecao {
        if (leitor.isStatusAcessoUsuario()) {
            if (livroServico.verificaPrimeiroDaFila(livro.getTitulo()) == leitor || livroServico.verificaPrimeiroDaFila(livro.getTitulo()) == null) {
                if (leitorDAO.qtdEmprestimosAtivos(leitor) < Emprestimo.limiteEmprestimosPorLeitor) {
                    if (livro.isDisponibilidade()) {
                        livroServico.removePrimeiroDafila(livro.getTitulo());
                        Emprestimo emprestimo = new Emprestimo(livro, leitor);
                        leitorDAO.adicionaHistoricoEmprestimos(leitor, emprestimo);
                        return emprestimo;
                    } else throw new Excecao("Livro:" + livro.getTitulo() + " indiponível para emprestimo no momento");

                } else throw new Excecao(leitor.getNome() + ", seu numero máximo de emprestimos ativos já foi atingido");

            } else throw new Excecao("Pessoas na fila: " + livroServico.qtdLeitoresNaFila(livro.getTitulo()) + ". " + leitor.getNome() + ", reserve o livro e aguarde para pegar emprestado quando disponivel.");

        } else throw new Excecao("Ops! " + leitor.getNome() + " nao pode receber emprestimos no momento.");
    }

    public boolean renovarEmprestimo(String titulo, Leitor leitor) {
        List<Emprestimo> emprestimosAtivosLeitor = leitorDAO.getEmprestimosAtivos(leitor);
        if (emprestimosAtivosLeitor != null) {
            for (Emprestimo emprestimo : emprestimosAtivosLeitor) {
                if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(titulo)) {
                    if (leitor.isStatusAcessoUsuario()) {
                        return emprestimo.solicitarRenovacao(emprestimo);
                    }
                }
            }
        }
        return false;
    }

}
