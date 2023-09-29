package Model.Usuarios;

import Excecoes.Excecao;

import Model.Operacoes.Reserva;

import java.time.LocalDateTime;



public class Leitor extends Usuario {
    //Herda de Usuário
    //Pesquisa livro
    //Reserva livro -
    //
    private long periodoBloqueioTotal;
    private Endereco endereco;
    private String telefone;
    private boolean cadastroRealizado = false;

    public Leitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        super.setCpf(cpf);
        super.setNome(nome);
        super.desbloquearConta();
        this.telefone = telefone;
        this.endereco = endereco;
        this.cadastroRealizado = true;
        System.out.println(nome + " - Cadastro efetuado com sucesso!");
    }

    //Getters e Setters
    public Endereco getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }

    public void solicitarReserva(String titulo) throws Excecao {
        Reserva reserva = new Reserva(this, titulo);
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


    public String toString() {
        if (cadastroRealizado) {
            return ("---------------------------------------------------------------------------------------------\n" +
                    "Leitor(a): " + super.getNome() + " - CPF: " + super.getCpf() + "\nTelefone: " + telefone + "\n" + endereco + " .\n");
        }
        return ("Leitor não cadastrado.");
    }

}
