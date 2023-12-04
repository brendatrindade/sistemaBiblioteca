/**
 * LeitorDAO
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
package DAO;

import Excecoes.Excecao;
import Model.Usuarios.Leitor;
import Model.Operacoes.Emprestimo;

import java.io.Serializable;
import java.util.*;

/**
 * Classe LeitorDAO: implementa DAOgenerico para o objeto Leitor.
 * Ela contém métodos para gerenciamento dos leitores cadastrados na biblioteca.
 */
public class LeitorDAO implements DAOgenerico<Leitor>, Serializable {
    private static List<Leitor> leitores = new ArrayList<>();
    private static Map<Leitor,List<Emprestimo> > historicoEmprestimos = new HashMap<>();
    /**
     * Salva a lista de leitores em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarLeitoresArquivo() throws Exception {
        Persistencia.salvarLeitor(leitores);
    }
    /**
     * Lê a lista de leitores de um arquivo.
     * @return - lista de leitores lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public List<Leitor> lerLeitoresArquivo() throws Exception {
        List<Leitor> leitoresArquivo = Persistencia.lerLeitor();
        return leitoresArquivo;
    }
    /**
     * Salva o histórico de empréstimos em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarHistoricoEmprestimos() throws Exception{
        Persistencia.salvarHistoricoEmprestimos(historicoEmprestimos);
    }
    /**
     * Lê o histórico de empréstimos de um arquivo.
     * @return - mapa do histórico de empréstimos lido do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public Map<Leitor,List<Emprestimo> > LerHistoricoEmprestimos() throws Exception{
        Map<Leitor,List<Emprestimo> > historicoEmprestimosArquivo = Persistencia.lerHistoricoEmprestimos();
        return historicoEmprestimosArquivo;
    }
    /**
     * Salva um novo leitor na lista de leitores.
     * @param c - leitor a ser salvo.
     */
    @Override
    public void salvar(Leitor c) {
        leitores.add(c);
    }
    /**
     * Deleta um leitor da lista de leitores.
     * @param leitor - leitor a ser deletado.
     */
    public void deletar(Leitor leitor) {
        leitores.remove(leitor);
    }
    /**
     * Deleta todos os leitores da lista de leitores.
     */
    @Override
    public void deletarTodos() {
        leitores = new ArrayList<>();
    }
    /**
     * Este método busca um leitor pelo id - CPF.
     * @param id - CPF do leitor a ser buscado.
     * @return O leitor encontrado ou null se o CPF não possuir cadastro.
     */
    @Override
    public Leitor buscarPorId(String id) {
        if(leitores != null){
            for(Leitor leitor : leitores){
                if(leitor.getCpf().equals(id))
                    return leitor;
            }
        }
        return null;
    }
    /**
     * Retorna todos os Leitores cadastrados.
     * @return Lista com todos os leitores.
     */
    public List<Leitor> getListaLeitores() {
        return leitores;
    }
    /**
     * Adiciona um empréstimo ao histórico de empréstimos de um leitor.
     * @param leitor - leitor que fez o empréstimo.
     * @param novoEmprestimo - empréstimo a ser adicionado ao seu histórico.
     */
    public void adicionaHistoricoEmprestimos(Leitor leitor, Emprestimo novoEmprestimo) {
        List<Emprestimo> emprestimosDoLeitor = historicoEmprestimos.get(leitor);

        if (emprestimosDoLeitor == null) {
            emprestimosDoLeitor = new ArrayList<>();
        }
        emprestimosDoLeitor.add(novoEmprestimo);
        historicoEmprestimos.put(leitor, emprestimosDoLeitor);
    }
    /**
     * Retorna o histórico de empréstimos realizados por um leitor.
     * @param leitor - leitor cujo histórico de empréstimos será retornado.
     * @return Lista de empréstimos feitos pelo leitor.
     */
    public List<Emprestimo> getHistoricoEmprestimos(Leitor leitor) {
        return historicoEmprestimos.get(leitor);
    }
    /**
     * Retorna os empréstimos ativos de um leitor.
     * @param leitor - leitor cujos empréstimos ativos serão retornados.
     * @return Lista de empréstimos ativos feitos pelo leitor.
     */
    public List<Emprestimo> getEmprestimosAtivos(Leitor leitor) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        List<Emprestimo> emprestimosDoLeitor = historicoEmprestimos.get(leitor);
        if (emprestimosDoLeitor != null) {
            for (Emprestimo emprestimo : emprestimosDoLeitor) {
                if (!emprestimo.isstatusEmprestimoFinalizado())
                    emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }
    /**
     * Retorna a quantidade de empréstimos ativos de um leitor.
     * @param leitor - leitor cuja quantidade de empréstimos ativos será retornada.
     * @return Número de empréstimos ativos do leitor.
     */
    public int qtdEmprestimosAtivos(Leitor leitor) {
        return getEmprestimosAtivos(leitor).size(); //numero de emprestimos ativos
    }
    /**
     * Verifica se o CPF de um leitor já está cadastrado.
     * @param cpf - CPF a ser verificado.
     * @return true se o CPF já estiver cadastrado, false caso contrário.
     * @throws Excecao Se o CPF já estiver cadastrado, a exceção é lançada.
     */
    public boolean cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        if (leitores != null) {
            for (Leitor leitor : leitores) {
                if (leitor.getCpf().equals(cpf)){
                    throw new Excecao(leitor.getNome() + ", o CPF informado ja possui cadastro. \n");}
            }
        } return false;
    }
}
