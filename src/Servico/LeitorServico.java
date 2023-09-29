package Servico;

import DAO.LeitorDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;

import java.util.List;

public class LeitorServico {
    private LeitorDAO leitorDAO;

    public LeitorServico(LeitorDAO leitorDAO) {
        this.leitorDAO = leitorDAO;
    }

    public Leitor criarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        if (cpfLeitorEstaCadastrado(cpf)) {
        }
        Leitor leitor = new Leitor(nome, cpf, endereco, telefone);
        salvarLeitor(leitor);
        return leitor;
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

    public void adicionaHistoricoEmprestimos(Emprestimo novoEmprestimo) {
       leitorDAO.adicionaHistoricoEmprestimos(novoEmprestimo);
    }
    public List<Emprestimo> getHistoricoEmprestimos() {
        return leitorDAO.getHistoricoEmprestimos();
    }
    public List<Emprestimo> getEmprestimosAtivos() {
        return leitorDAO.getEmprestimosAtivos();
    }
    public int qtdEmprestimosAtivos(){
        return leitorDAO.qtdEmprestimosAtivos();
    }
    public boolean cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        return leitorDAO.cpfLeitorEstaCadastrado(cpf);
    }

}


