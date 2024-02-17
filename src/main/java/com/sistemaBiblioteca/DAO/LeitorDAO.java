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
package com.sistemaBiblioteca.DAO;

import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;

import java.io.Serializable;
import java.util.*;

/**
 * Classe LeitorDAO: implementa DAOgenerico para o objeto Leitor.
 * Ela contém métodos para gerenciamento dos leitores cadastrados na biblioteca.
 */
public class LeitorDAO implements DAOgenerico<Leitor>, Serializable {
    private List<Leitor> leitores;
    private Map<Leitor,List<Emprestimo> > historicoEmprestimos;


    public LeitorDAO() throws Exception {
        this.leitores = new ArrayList<>();
        this.leitores = Persistencia.lerLeitor();
        this.historicoEmprestimos = new HashMap<>();
        this.historicoEmprestimos = Persistencia.lerHistoricoEmprestimos();
    }

    /**
     * Criar e registrar um novo leitor no sistema.
     * @param leitor Objeto contendo os atributos necessários.
     * @return Objeto leitor registrado.
     */
    public Leitor criarLeitor(Leitor leitor) throws Exception {
        if (!leitor.validaCPF(leitor.getCpf())) {
            throw new Exception("CPF inválido!");
        }
        if (cpfLeitorEstaCadastrado(leitor.getCpf())) {
            throw new Exception("CPF já está cadastrado!");
        }
        // Retirar Exceptions apos criar tela de cadastro
        salvar(leitor);
        salvarLeitoresArquivo();
        return leitor;
    }

    /**
     * Salva a lista de leitores em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarLeitoresArquivo() throws Exception {
        Persistencia.salvarLeitor(this.leitores);
    }
    /**
     * Deleta um leitor do arquivo.
     * @param leitor - leitor a ser deletado.
     * @throws Exception se ocorrer um erro no processo de deletar do arquivo.
     */
    public void deletarLeitorArquivo(Leitor leitor) throws Exception {
        if (this.leitores.contains(leitor)){
            if( this.leitores.remove(leitor) ){
                salvarLeitoresArquivo();
            } else {
                throw new Exception("Erro ao deletar leitor.");
            }
        } else {
            throw new Exception("leitor não encontrado.");
        }
    }
    /**
     * Deleta todos os leitores do arquivo
     */
    public void deletarTodosLeitoresArquivo() throws Exception {
        deletarTodos();
        salvarLeitoresArquivo();
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
        Persistencia.salvarHistoricoEmprestimos(this.historicoEmprestimos);
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
        this.leitores.add(c);
    }
    /**
     * Deleta um leitor da lista de leitores.
     * @param leitor - leitor a ser deletado.
     */
    public void deletar(Leitor leitor) {
        this.leitores.remove(leitor);
    }
    /**
     * Deleta todos os leitores da lista de leitores.
     */
    @Override
    public void deletarTodos() {
        this.leitores = new ArrayList<>();
    }
    /**
     * Deleta todos o historico de emprestimo e salva no arquivo
     */
    public void deletarTodoHistoricoEmprestimo() throws Exception {
        this.historicoEmprestimos = new HashMap<>();
        salvarHistoricoEmprestimos();
    }
    /**
     * Este método busca um leitor pelo id - CPF.
     * @param id - CPF do leitor a ser buscado.
     * @return O leitor encontrado ou null se o CPF não possuir cadastro.
     */
    @Override
    public Leitor buscarPorId(String id) {
        if(!this.leitores.isEmpty()){
            for(Leitor leitor : this.leitores){
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
        return this.leitores;
    }
    /**
     * Adiciona um empréstimo ao histórico de empréstimos de um leitor.
     * @param leitor - leitor que fez o empréstimo.
     * @param novoEmprestimo - empréstimo a ser adicionado ao seu histórico.
     */
    public void adicionaHistoricoEmprestimos(Leitor leitor, Emprestimo novoEmprestimo) {
        List<Emprestimo> emprestimosDoLeitor = this.historicoEmprestimos.get(leitor);

        if (emprestimosDoLeitor == null) {
            emprestimosDoLeitor = new ArrayList<>();
        }
        emprestimosDoLeitor.add(novoEmprestimo);
        this.historicoEmprestimos.put(leitor, emprestimosDoLeitor);
    }
    /**
     * Retorna o histórico de empréstimos realizados por um leitor.
     * @param leitor - leitor cujo histórico de empréstimos será retornado.
     * @return Lista de empréstimos feitos pelo leitor.
     */
    public List<Emprestimo> getHistoricoEmprestimos(Leitor leitor) {
        return this.historicoEmprestimos.get(leitor);
    }
    /**
     * Retorna os empréstimos ativos de um leitor.
     * @param leitor - leitor cujos empréstimos ativos serão retornados.
     * @return Lista de empréstimos ativos feitos pelo leitor.
     */
    public List<Emprestimo> getEmprestimosAtivos(Leitor leitor) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        List<Emprestimo> emprestimosDoLeitor = this.historicoEmprestimos.get(leitor);
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
    public boolean cpfLeitorEstaCadastrado(String cpf) {
        if (!this.leitores.isEmpty()) {
            for (Leitor l : this.leitores) {
                if (l.getCpf().equals(cpf)){
                    return true;
                }
            }
        }
        return false;
    }
    public void adicionaHistoricoEmprestimosArq(Leitor leitor, Emprestimo novoEmprestimo) {
        List<Leitor> leitoresHE = new ArrayList<>(this.historicoEmprestimos.keySet());
        boolean leitorEncontrado = false;
        int i = 0;
        while ( (!leitorEncontrado) && (i < leitoresHE.size()) ) {
            Leitor leitorObj = leitoresHE.get(i);

            if ( leitorObj.getCpf().equals(leitor.getCpf()) ) {
                //o leitor que esta solicitando o emprestimo ja esta no mapa
                List<Emprestimo> emprestimosDoLeitor = this.historicoEmprestimos.get(leitorObj);
                // carrega a lista de emprestimo do leito
                emprestimosDoLeitor.add(novoEmprestimo);
                // adiciona o novo emprestimo a lista
                this.historicoEmprestimos.put(leitorObj, emprestimosDoLeitor);
                // adiciona a lista ao mapa
                leitorEncontrado = true;
            }
            i++;
        }
        if(!leitorEncontrado){
            List<Emprestimo> primeiroEmprestimo = new ArrayList<>();
            primeiroEmprestimo.add(novoEmprestimo);
            this.historicoEmprestimos.put(leitor, primeiroEmprestimo);
        }
    }
    public List<Emprestimo> getHistoricoEmprestimosArq(Leitor leitor) {
        List<Leitor> leitoresHE = new ArrayList<>(this.historicoEmprestimos.keySet());
        for (Leitor leitorObj : leitoresHE ) {
            if ( leitorObj.getCpf().equals(leitor.getCpf()) ) {
                return this.historicoEmprestimos.get(leitorObj);
            }
        }
        return null;
    }
    public List<Emprestimo> getEmprestimosAtivosArq(Leitor leitor) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        List<Emprestimo> emprestimosDoLeitor = getHistoricoEmprestimosArq(leitor);
        if (emprestimosDoLeitor != null) {
            for (Emprestimo emprestimo : emprestimosDoLeitor) {
                if (!emprestimo.isstatusEmprestimoFinalizado())
                    emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }
    public int qtdEmprestimosAtivosArq(Leitor leitor) {
        return getEmprestimosAtivosArq(leitor).size();
    }

}
