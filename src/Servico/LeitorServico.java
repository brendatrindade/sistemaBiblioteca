package Servico;

import DAO.LeitorDAO;
import DAO.LivroDAO;
import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;

import java.util.List;
import java.util.Map;

public class LeitorServico {
    private LeitorDAO leitorDAO;
    private LivroDAO livroDAO;

    public LeitorServico(LeitorDAO leitorDAO, LivroDAO livroDAO) {
        this.leitorDAO = leitorDAO;
        this.livroDAO = livroDAO;
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

    public void adicionaHistoricoEmprestimos(Leitor leitor, Emprestimo novoEmprestimo) {
       leitorDAO.adicionaHistoricoEmprestimos(leitor, novoEmprestimo);
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


