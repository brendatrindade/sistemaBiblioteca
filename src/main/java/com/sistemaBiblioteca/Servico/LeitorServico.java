/**
 * LeitorServico
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
package com.sistemaBiblioteca.Servico;

import com.sistemaBiblioteca.DAO.LeitorDAO;
import com.sistemaBiblioteca.DAO.LivroDAO;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;

import java.util.List;
import java.util.Map;
/**
 * Classe LeitorServico: fornece serviços para gerenciar leitores da biblioteca.
 */
public class LeitorServico {
    private final LeitorDAO leitorDAO;
    private final EmprestimoServico emprestimoServico;
    private final LivroDAO livroDAO;
    /**
     * Construtor para a classe LeitorServico.
     * @param leitorDAO - DAO utilizado para operações de leitor.
     * @param livroDAO - DAO utilizado para operações de livro.
     * @param emprestimoServico - Serviço de empréstimo.
     */
    public LeitorServico(LeitorDAO leitorDAO, LivroDAO livroDAO, EmprestimoServico emprestimoServico) {
        this.leitorDAO = leitorDAO;
        this.livroDAO = livroDAO;
        this.emprestimoServico = emprestimoServico;
    }
    /**
     * Cria um novo leitor.
     * @param nome - nome do leitor.
     * @param cpf - CPF do leitor.
     * @param endereco - endereço do leitor.
     * @param telefone - telefone do leitor.
     * @return O novo leitor criado.
     * @throws Excecao Se o CPF do leitor já estiver cadastrado.
     */
    public Leitor criarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        if (!cpfLeitorEstaCadastrado(cpf)) {
            Leitor leitor = new Leitor(nome, cpf, endereco, telefone);
            salvarLeitor(leitor);
            return leitor;
        }
        return null;
    }
    public void salvarLeitor(Leitor leitor) {
        leitorDAO.salvar(leitor);
    }
    public void deletarLeitor(Leitor leitor){
        leitorDAO.deletar(leitor);
    }
    public void deletarTodos() {
        leitorDAO.deletarTodos();
    }
    public Leitor buscarPorId(String id) {
        return leitorDAO.buscarPorId(id);
    }
    public List<Leitor> getListaLeitores() {
        return leitorDAO.getListaLeitores();
    }

    public void adicionaHistoricoEmprestimos(Leitor leitor, Emprestimo novoEmprestimo) {
       leitorDAO.adicionaHistoricoEmprestimos(leitor, novoEmprestimo);
    }
    public boolean solicitarRenovacaoEmprestimo(String titulo, Leitor leitor){
        return emprestimoServico.renovarEmprestimo(titulo, leitor);
    }
    public List<Emprestimo> getHistoricoEmprestimos(Leitor leitor) {
        return leitorDAO.getHistoricoEmprestimos(leitor);
    }
    public List<Emprestimo> getEmprestimosAtivos(Leitor leitor) {
        return leitorDAO.getEmprestimosAtivos(leitor);
    }
    public int qtdEmprestimosAtivos(Leitor leitor){
        return leitorDAO.qtdEmprestimosAtivos(leitor);
    }

    public Map<String, List<Livro>> pesquisarLivros(String texto) {
        return livroDAO.pesquisarLivros(texto);
    }

    public boolean cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        return leitorDAO.cpfLeitorEstaCadastrado(cpf);
    }

}


