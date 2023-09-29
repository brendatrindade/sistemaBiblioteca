package Controle;

import Excecoes.Excecao;
import Model.Operacoes.Emprestimo;
import Model.Operacoes.Livro;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import Servico.LeitorServico;

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

    public Map<String, List<Livro>> pesquisarLivros(String texto) {
        return leitorServico.pesquisarLivros(texto);
    }
    public boolean cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        return leitorServico.cpfLeitorEstaCadastrado(cpf);
    }

}

