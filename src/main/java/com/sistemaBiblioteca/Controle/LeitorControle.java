/**
 * LeitorControle
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
import com.sistemaBiblioteca.Model.Operacoes.Reserva;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import com.sistemaBiblioteca.Servico.LeitorServico;
import com.sistemaBiblioteca.Servico.ReservaServico;

import java.util.List;
import java.util.Map;

public class LeitorControle {
    private LeitorServico leitorServico;

    public LeitorControle(LeitorServico leitorServico) {
        this.leitorServico = leitorServico;
    }

    public void criarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        try {
            Leitor leitor = leitorServico.criarLeitor(nome, cpf, endereco, telefone);
            System.out.println(leitor.getNome() + " - Cadastro efetuado com sucesso!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Solicita ao sistema uma reserva para um livro.
     * @param titulo String - título do livro a ser reservado.
     * @return Booleano - indica se a reserva foi concluída (true) ou não (false).
     */
    public boolean solicitarReserva(ReservaServico reservaServico, Leitor leitor, String titulo) throws Exception {
        Reserva reserva = reservaServico.criarReserva(leitor, titulo);
        return reserva.isReservaConcluida();
    }
    public boolean solicitarRenovacaoEmprestimo(String titulo, Leitor leitor){
        return leitorServico.solicitarRenovacaoEmprestimo(titulo, leitor);
    }
    public void deletarLeitor(Leitor leitor){
        leitorServico.deletarLeitor(leitor);
    }
    public void deletarTodosLeitores(){
        leitorServico.deletarTodos();
    }
    public Leitor buscarLeitorPorId(String id) throws Exception {
        return leitorServico.buscarPorId(id);
    }
    public List<Leitor> getLeitores(){
        return leitorServico.getListaLeitores();
    }

    public void adicionarHistoricoEmprestimos(Leitor leitor, Emprestimo novoEmprestimo){
        leitorServico.adicionaHistoricoEmprestimos(leitor, novoEmprestimo);
    }
    public List<Emprestimo> getHistoricoEmprestimos(Leitor leitor){
        return leitorServico.getHistoricoEmprestimos(leitor);
    }
    public List<Emprestimo> getEmprestimosAtivos(Leitor leitor){
        return leitorServico.getEmprestimosAtivos(leitor);
    }
    public int qtdEmprestimosAtivos(Leitor leitor){
        return leitorServico.qtdEmprestimosAtivos(leitor);
    }

    public Map<String, List<Livro>> pesquisarLivros(String texto) throws Exception {
        return leitorServico.pesquisarLivros(texto);
    }
    public boolean cpfLeitorEstaCadastrado(String cpf) throws Exception {
        return leitorServico.cpfLeitorEstaCadastrado(cpf);
    }

}

