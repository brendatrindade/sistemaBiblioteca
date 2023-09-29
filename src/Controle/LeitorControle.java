package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.LeitorServico;

import java.util.List;

public class LeitorControle {
    private LeitorServico leitorServico;

    public LeitorControle(LeitorServico leitorServico) {
        this.leitorServico = leitorServico;
    }
    public void criarLeitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        try {
            Leitor leitor = leitorServico.criarLeitor(nome, cpf, endereco, telefone);
            System.out.println(leitor.getNome() + " - Cadastro efetuado com sucesso!");
        } catch (Excecao e) {
            System.out.println(e.getMessage());
        }
    }
    public void deletarLeitor(Leitor leitor){
        leitorServico.deletarLeitor(leitor);
    }
    public void deletarTodosLeitores(){
        leitorServico.deletarTodos();
    }
    public Leitor buscarLeitorPorId(String id){
        return leitorServico.buscarPorId(id);
    }
    public List<Leitor> getLeitores(){
        return leitorServico.getListaLeitores();
    }
    public void adicionarHistoricoEmprestimos(Emprestimo novoEmprestimo){
        leitorServico.adicionaHistoricoEmprestimos(novoEmprestimo);
    }
    public List<Emprestimo> getHistoricoEmprestimos(){
        return leitorServico.getHistoricoEmprestimos();
    }
    public List<Emprestimo> getEmprestimosAtivos(){
        return leitorServico.getEmprestimosAtivos();
    }
    public int qtdEmprestimosAtivos(){
        return leitorServico.qtdEmprestimosAtivos();
    }
    public boolean cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        return leitorServico.cpfLeitorEstaCadastrado(cpf);
    }

}

