/**
 * EmprestimoServico
 *
 * @author Brenda Araújo Trindade Oliveira
 * @version 1.0
 * @since 02/10/2023
 *
 * Direitos autorais (c) 2023 Brenda Araújo Trindade Oliveira. Todos os direitos reservados.
 * Este software é confidencial e proprietário de Brenda Araújo Trindade Oliveira.
 * Este software é protegido sob direitos autorais.
 *
 */
package Servico;

import DAO.LeitorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;

import java.util.List;
/**
 * Classe EmprestimoServico: fornece serviços para gerenciar empréstimos na biblioteca.
 */
public class EmprestimoServico {
    private final LivroServico livroServico;
    private final LeitorDAO leitorDAO;
    /**
     * Construtor para a classe EmprestimoServico.
     * @param livroServico - serviço de livro.
     * @param leitorDAO - DAO utilizado para operações de leitor.
     */
    public EmprestimoServico(LivroServico livroServico, LeitorDAO leitorDAO) {
        this.livroServico = livroServico;
        this.leitorDAO = leitorDAO;
    }
    /**
     * Cria um novo empréstimo.
     * @param livro - livro a ser emprestado.
     * @param leitor - leitor que está recebendo o livro emprestado.
     * @return O novo empréstimo criado.
     * @throws Excecao Se o leitor não puder pegar livros emprestados no momento, se seu numero máximo de emprestimos ativos já foi atingido,
     * se o livro não estiver disponível e ele não for o primeiro leitor na fila de reservas para o titulo.
     */
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
    /**
     * Renova um empréstimo ativo.
     * @param titulo - título do livro a ser renovado.
     * @param leitor - leitor que está renovando o empréstimo.
     * @return true se o empréstimo foi renovado com sucesso, falso caso contrário.
     */
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
