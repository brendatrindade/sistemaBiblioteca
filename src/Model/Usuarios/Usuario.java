package Model.Usuarios;

import Excecoes.Excecao;

public class Usuario {
    private String cpf; // id do usuário
    private String nome;
    private boolean statusAcessoUsuario; // true = ativo, false = bloqueado

    public Usuario() {

    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) throws Excecao {
        if (validaCPF(cpf)) {
            this.cpf = cpf.replaceAll("[^0-9]", "");
        }
        else {
            throw new Excecao("CPF inválido");
        }
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public boolean isStatusAcessoUsuario(){
        return statusAcessoUsuario;
    }

    // Método para bloquear a conta do leitor
    public void bloquearConta() {
        this.statusAcessoUsuario = false;
    }

    // Método para desbloquear a conta do leitor
    public void desbloquearConta() {
        this.statusAcessoUsuario = true;
    }

    // Método para validar CPF
    private boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", ""); //todos caracteres não correspondentes a um numero serao removidos
        if (cpf.length() != 11)
            return false;
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);

        if (primeiroDigito >= 10)
            primeiroDigito = 0;

        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito)
            return false;

        soma = 0;

        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }

        int segundoDigito = 11 - (soma % 11);

        if (segundoDigito >= 10)
            segundoDigito = 0;

        return (Character.getNumericValue(cpf.charAt(10)) == segundoDigito);
    }

}
