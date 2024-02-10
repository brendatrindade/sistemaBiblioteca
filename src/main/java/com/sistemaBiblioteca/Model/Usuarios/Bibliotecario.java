/**
 * Bibliotecario
 *
 * @author Brenda Araújo Trindade Oliveira
 * @version 1.0
 * @since 02/10/2023
 *
 * Direitos autorais (c) 2023 Brenda Araújo Trindade Oliveira. Todos os direitos reservados.
 * Este software é confidencial e proprietário de Brenda Araújo Trindade Oliveira.
 * Este software é protegido sob direitos autorais.
 *
 */
package com.sistemaBiblioteca.Model.Usuarios;
import com.sistemaBiblioteca.Excecoes.Excecao;

/**
 * Classe Bibliotecario: Especialização de um usuário do sistema que representa um bibliotecário e
 * Generalização de um operador do sistema de biblioteca.
 * Esta classe herda de Usuario.
 * Pesquisa, registra, empresta e devolve livros.
 */
public class Bibliotecario extends Usuario {
    private String cargo = "Bibliotecario";
    private String senha;
    private boolean cadastroRealizado = false;
    /**
     * Construtor da classe Bibliotecario.
     * @param nome String - nome do bibliotecário.
     * @param cpf String - CPF do bibliotecário.
     * @param senha String - senha do bibliotecário.
     * @throws Excecao Se o CPF fornecido for inválido.
     */
    public Bibliotecario(String nome, String cpf, String senha) throws Excecao {
        super.setNome(nome);
        super.setCpf(cpf);
        super.desbloquearConta();
        this.senha = senha;
        this.cadastroRealizado = true;
    }
    /**
     * Define o cargo do operador.
     * @param cargo String - novo cargo do operador.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    /**
     * Retorna o cargo do operador.
     * @return String - cargo do operador.
     */
    public String getCargo() {
        return cargo;
    }
    /**
     * Define a senha do operador.
     * @param senha String - nova senha do operador.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    /**
     * Retorna a senha do operador.
     * @return String - senha do operador.
     */
    public String getSenha() {
        return senha;
    }
    /**
     * Retorna a representação em String do objeto Bibliotecario.
     * @return String - representa o bibliotecário.
     */
    public String toString() {
        if (cadastroRealizado){
            return ("\nBibliotecario(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
        }
        return ("Bibliotecario(a) não cadastrado");
    }
}
