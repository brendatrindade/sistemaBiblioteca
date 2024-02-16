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
package com.sistemaBiblioteca.Controle;

import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import com.sistemaBiblioteca.Servico.EmprestimoServico;

public class EmprestimoControle {
    private EmprestimoServico emprestimoServico;

    public EmprestimoControle(EmprestimoServico emprestimoServico) {
        this.emprestimoServico = emprestimoServico;
    }

    public void criarEmprestimo(Livro livro, Leitor leitor) throws Exception {
        try {
            Emprestimo emprestimo = emprestimoServico.criarEmprestimo(livro, leitor);
            System.out.println("Empréstimo do livro: " + livro.getTitulo() + " realizado com sucesso!");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }


}
