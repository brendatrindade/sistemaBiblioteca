package Model.Usuarios;

import Excecoes.Excecao;

public class Bibliotecario extends Usuario {

    // Herda de Usuario
    // Acesso às funcionalidades:
    // #1 (Registro de Livros)
    // #2 (Pesquisa de Livros) ----> Usuario
    // #3 (Empréstimo e Devolução)

    private String cargo = "Bibliotecario";
    private String senha;
    private boolean cadastroRealizado = false;

    public Bibliotecario(String nome, String cpf, String senha) throws Excecao {
        super.setNome(nome);
        super.setCpf(cpf);
        super.desbloquearConta();
        this.senha = senha;
        this.cadastroRealizado = true;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getCargo() {
        return cargo;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getSenha() {
        return senha;
    }

    public String toString() {
        if (cadastroRealizado){
            return ("\nBibliotecario(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
        }
        return ("Bibliotecario(a) não cadastrado");
    }
}
