package DAO;

import Excecoes.Excecao;
import Model.Usuarios.Bibliotecario;
import Model.Usuarios.Leitor;
import Model.Operacoes.Emprestimo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LeitorDAO implements DAOgenerico<Leitor> {
    private static List<Leitor> leitores = new ArrayList<>();
    private static Map<Leitor,List<Emprestimo> > historicoEmprestimos = new HashMap<>();

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
        if(leitores != null){
            for(Leitor leitor : leitores){
                if(leitor.getCpf().equals(id))
                    return leitor;
            }
        }
        return null;
    }

    /**
     * Retorna todos os Leitores
     */
    public List<Leitor> getListaLeitores() {
        return leitores;
    }

    public void adicionaHistoricoEmprestimos(Leitor leitor, Emprestimo novoEmprestimo) {
        List<Emprestimo> emprestimosDoLeitor = historicoEmprestimos.get(leitor);

        if (emprestimosDoLeitor == null) {
            emprestimosDoLeitor = new ArrayList<>();
            emprestimosDoLeitor.add(novoEmprestimo);
            historicoEmprestimos.put(leitor, emprestimosDoLeitor);
        }
        emprestimosDoLeitor.add(novoEmprestimo);

    }
    public List<Emprestimo> getHistoricoEmprestimos(Leitor leitor) {
        return historicoEmprestimos.get(leitor);
    }

    public List<Emprestimo> getEmprestimosAtivos(Leitor leitor) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        List<Emprestimo> emprestimosDoLeitor = historicoEmprestimos.get(leitor);
        if (emprestimosDoLeitor == null) {
            for (Emprestimo emprestimo : emprestimosDoLeitor) {
                if (!emprestimo.isstatusEmprestimoFinalizado())
                    emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos;
    }

    public int qtdEmprestimosAtivos(Leitor leitor) {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        List<Emprestimo> emprestimosDoLeitor = historicoEmprestimos.get(leitor);
        if (emprestimosDoLeitor == null) {
            for (Emprestimo emprestimo : emprestimosDoLeitor) {
                if (!emprestimo.isstatusEmprestimoFinalizado())
                    emprestimosAtivos.add(emprestimo);
            }
        }
        return emprestimosAtivos.size(); //numero de emprestimos ativos
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
