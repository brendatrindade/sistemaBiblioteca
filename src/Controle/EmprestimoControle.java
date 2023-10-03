/**
 * EmprestimoControle
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
