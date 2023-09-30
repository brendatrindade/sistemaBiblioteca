package Model.Usuarios;

import Excecoes.Excecao;

import Model.Operacoes.Reserva;


public class Leitor extends Usuario {
    //Herda de Usuário
    //Pesquisa livro
    //Reserva livro

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
    }

    //Getters e Setters
    public Endereco getEndereco() {
        return endereco;
    }
    public String getTelefone() {
        return telefone;
    }

    public boolean solicitarReserva(String titulo) {
        Reserva reserva = new Reserva(this, titulo);
        return reserva.isReservaConcluida();
    }

    public String toString() {
        if (cadastroRealizado) {
            return ("\nLeitor(a): " + super.getNome() + " - CPF: " + super.getCpf() +
                    "\nTelefone: " + telefone + "\n" + endereco + " .\n");
        }
        return ("Leitor não cadastrado.");
    }

}
