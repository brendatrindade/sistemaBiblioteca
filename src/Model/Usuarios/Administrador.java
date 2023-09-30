package Model.Usuarios;

import Excecoes.Excecao;

public class Administrador extends Bibliotecario{
    private boolean cadastroRealizado;

    public Administrador(String nome, String cpf, String senha) throws Excecao {
        super(nome, cpf, senha);
        super.setCargo("Administrador");
        this.cadastroRealizado = true;

    }

    //gerenciar Leitores
    public void bloquearLeitor(Leitor leitor) {
        leitor.bloquearConta();
    }
    public void desbloquearLeitor(Leitor leitor) {
        leitor.desbloquearConta();
    }


    //gerenciar Bibliotecarios
    public void bloquearBibliotecario(Bibliotecario bibliotecario) {
        bibliotecario.bloquearConta();
    }
    public void desbloquerBibliotecario(Bibliotecario bibliotecario){
        bibliotecario.desbloquearConta();
    }


    public String toString() {
        if (cadastroRealizado) {
            return ("\nAdministrador(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
        }
        return ("Administrador(a) não cadastrado");
    }

}
