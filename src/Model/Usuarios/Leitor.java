package Model.Usuarios;
import Model.Operacoes.Emprestimo;
import DAO.LeitorDAO;
import Excecoes.Excecao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Leitor extends Usuario {
    //Herda de Usuário
    //Pesquisa livro
    //Reserva livro -
    //
    private long periodoBloqueioTotal;
    private Endereco endereco;
    private String telefone;
    private LeitorDAO leitorDAO;

    public Leitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        this.leitorDAO = new LeitorDAO();
        try {
            cpfLeitorEstaCadastrado(cpf);
            super.setCpf(cpf);
            super.setNome(nome);
            this.telefone = telefone;
            this.endereco = endereco;
            leitorDAO.adiciona(this);
            System.out.println(nome + " - Cadastro efetuado com sucesso!");
        }
        catch (Excecao excecao) {
            System.out.println(excecao.getMessage());
        }
    }

    //Se o Leitor devolver um livro em atraso o metodo multar será chamado para suspender seu acesso
    public void multar(long periodoBloqueio) {
        this.periodoBloqueioTotal += periodoBloqueio; //Se o Leitor atrasar mais de uma devolução, o periodo de suspensão será a soma de dias de todos os seus atrasos
        LocalDateTime dataBloqueio = LocalDateTime.now();
        LocalDateTime dataDesbloqueio = dataBloqueio.plusDays(periodoBloqueioTotal);
        if(LocalDateTime.now().isBefore(dataDesbloqueio)) //Se a data atual for anterior a data de desbloqueio => bloquear Leitor
            this.bloquearConta();
        else //Se a data atual for igual ou posterior a data de desbloqueio => desbloquear Leitor
            this.desbloquearConta();
    }

    //Getters e Setters
    public Endereco getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }

    public void adicionarLeitor(){
        leitorDAO.adiciona(this);
    }
    public void removerLeitor(){
        leitorDAO.remove(this);
    }

    public void setHistoricoEmprestimos(Emprestimo meuEmprestimo) {
        leitorDAO.adicionaHistoricoEmprestimos(meuEmprestimo);
    }
    public List<Emprestimo> getHistoricoEmprestimos() {
        return leitorDAO.getHistoricoEmprestimos();
    }

    public List<Emprestimo> getEmprestimosAtivos() {
        List<Emprestimo> ativos = new ArrayList<>();
        if (leitorDAO.getHistoricoEmprestimos() != null) {
            for (Emprestimo emprestimo : leitorDAO.getHistoricoEmprestimos()) {
                if (!emprestimo.isstatusEmprestimoFinalizado())
                    ativos.add(emprestimo);
            }
        }
        return ativos;
    }

    /*
    public boolean solicitarReserva( String titulo) {
        Boolean seTiverDisponivel = acervo.reservarLivro(titulo, this);
        return seTiverDisponivel;
    } */


    //Verificar se o CPF já possui cadastro como Leitor
    public void cpfLeitorEstaCadastrado(String cpf) throws Excecao {
        if (leitorDAO.get() != null) {
            for (Leitor leitor : leitorDAO.get()) {
                if (leitor.getCpf().equals(cpf)){
                    throw new Excecao(leitor.getNome() + ", o CPF informado ja possui cadastro. \n");}
            }
        }
    }

    public String toString() {
        return ("---------------------------------------------------------------------------------------------\n" +
                "Leitor(a): " + super.getNome() + " - CPF: " + super.getCpf() + "\nTelefone: " + telefone + "\n" + endereco + " .\n");
    }


}
