package DAO;

import Excecoes.Excecao;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Model.Operacoes.Emprestimo;
import java.util.ArrayList;
import java.util.List;
import DAO.LivroDAO;

public class LeitorDAO implements CRUD<Leitor> {
    private static List<Leitor> leitores = new ArrayList<>();
    private List<Emprestimo> historicoEmprestimos = new ArrayList<>();

    /**
     * Cria novo Leitor
     */
    public void cria(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        Leitor leitor = new Leitor(nome,cpf,endereco,telefone);
        leitores.add(leitor);
    }
    /**
     * Adiciona novo Leitor
     *
     * @param leitor
     */
    @Override
    public void adiciona(Leitor leitor) {
        leitores.add(leitor);
    }
    /**
     * Lê todos os Leitores
     */
    @Override
    public List<Leitor> get() {
        return leitores;
    }

    /**
     * Atualiza um Leitor - nome
     *
     * @param leitor
     * @param novoDado
     */
    @Override
    public void altera(Leitor leitor, String novoDado) {
        leitor.setNome(novoDado);
    }
    /**
     * Deleta um Leitor
     * @param leitor
     */
    @Override
    public void remove(Leitor leitor) {
        leitores.remove(leitor);
    }
    /**
     * Deleta todos os dados
     */
    @Override
    public void removeTodos() {
        leitores = new ArrayList<>();
    }
    public void adicionaHistoricoEmprestimos(Emprestimo novoEmprestimo) {
        historicoEmprestimos.add(novoEmprestimo);
    }
    public List<Emprestimo> getHistoricoEmprestimos() {
        return historicoEmprestimos;
    }
    public List<Emprestimo> getEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        for(Emprestimo emprestimo : historicoEmprestimos ) {
            if (! emprestimo.isstatusEmprestimoFinalizado() )
                ativos.add(emprestimo);
        }
        return ativos;
    }


}
