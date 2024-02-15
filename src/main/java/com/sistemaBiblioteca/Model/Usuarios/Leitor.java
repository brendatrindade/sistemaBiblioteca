/**
 * Leitor
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
 * SubClasse Leitor: Especialização de um usuário do sistema de biblioteca que representa um leitor.
 * Esta classe herda de Usuario.
 * Pesquisa, reserva e solicita empréstimos de livros.
 */
public class Leitor extends Usuario {
    private Endereco endereco;
    private String telefone;
    private boolean cadastroRealizado = false;
    /**
     * Construtor da classe Leitor.
     * @param nome String - nome do leitor.
     * @param cpf String - CPF do leitor.
     * @param endereco objeto Endereco - endereço do leitor.
     * @param telefone String - telefone do leitor.
     * @throws Excecao Se o CPF fornecido for inválido.
     */
    public Leitor(String nome, String cpf, Endereco endereco, String telefone) throws Excecao {
        super.setCpf(cpf);
        super.setNome(nome);
        super.desbloquearConta();
        this.telefone = telefone;
        this.endereco = endereco;
        this.cadastroRealizado = true;
    }
    /**
     * Retorna o endereço do leitor.
     * @return objeto Endereco - endereço do leitor.
     */
    public Endereco getEndereco() {
        return endereco;
    }
    /**
     * Retorna o telefone do leitor.
     * @return String - telefone do leitor.
     */
    public String getTelefone() {
        return telefone;
    }
    /**
     * Altera o endereço do leitor.
     * @param rua String - nova rua do endereço.
     * @param numero String - novo número da rua.
     * @param cidade String - nova cidade do endereço.
     * @param estado String - novo estado do endereço.
     */
    public void alterarEndereco(String rua, String numero, String cidade, String estado) {
        this.endereco.setRua(rua);
        this.endereco.setNumero(numero);
        this.endereco.setCidade(cidade);
        this.endereco.setEstado(estado);
    }
    /**
     * Retorna a representação em String do objeto Leitor.
     * @return String - representa o leitor.
     */
    public String toString() {
        if (cadastroRealizado) {
            return ("\nLeitor(a): " + super.getNome() + " - CPF: " + super.getCpf() + " - Telefone: " + telefone +
                    "\n" + endereco + "\n");
        }
        return ("Leitor não cadastrado.");
    }

}
