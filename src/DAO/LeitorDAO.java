package DAO;

import Excecoes.Excecao;
import Model.Usuarios.Leitor;
import Model.Operacoes.Emprestimo;
import java.util.ArrayList;
import java.util.List;


public class LeitorDAO implements DAOgenerico<Leitor> {
    private static List<Leitor> leitores = new ArrayList<>();
    private List<Emprestimo> historicoEmprestimos = new ArrayList<>();


    @Override
    public void salvar(Leitor c) {
        leitores.add(c);
    }

    /**
     * Deleta um Leitor
     * @param leitor
     */
    public void deletar(Leitor leitor) {
        leitores.remove(leitor);
    }

    @Override
    public void deletarTodos() {
        leitores = new ArrayList<>();
    }

    @Override
    public Leitor buscarPorId(String id) {
        String cpf = id;
        for(Leitor leitor : leitores){
            if(leitor.getCpf() == cpf)
                return leitor;
        }
        return null;
    }

    /**
     * Retorna todos os Leitores
     */
    public List<Leitor> getListaLeitores() {
        return leitores;
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

    public int qtdEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        if (getHistoricoEmprestimos() != null) {
            for (Emprestimo emprestimo : getHistoricoEmprestimos()) {
                if (!emprestimo.isstatusEmprestimoFinalizado())
                    ativos.add(emprestimo);
            }
        }
        return ativos.size(); //numero de emprestimos ativos
    }

    //Verificar se o CPF já possui cadastro como Leitor
    public boolean cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        if (leitores != null) {
            for (Leitor leitor : leitores) {
                if (leitor.getCpf().equals(cpf)){
                    throw new Excecao(leitor.getNome() + ", o CPF informado ja possui cadastro. \n");}
            }
        } return false;
    }

}
