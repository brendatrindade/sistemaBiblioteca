/**
 * Usuario
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
package Model.Usuarios;
import Excecoes.Excecao;
import java.io.Serializable;

/**
 * Superclasse Classe Usuario: Generalização de um usuário no sistema de biblioteca.
 */
public class Usuario implements Serializable {
    private String cpf; // --> id do usuário
    private String nome;
    private boolean statusAcessoUsuario; // --> true = Acesso ativo | false = Acesso bloqueado
    /**
     * O construtor da classe Usuario é padrão.
     */
    public Usuario() {

    }
    /**
     * Retorna o CPF do usuário.
     * @return String que representa o CPF (Id) do usuário.
     */
    public String getCpf() {
        return cpf;
    }
    /**
     * Define o CPF do usuário.
     * @param cpf String - CPF do usuário.
     * @throws Excecao Se o CPF fornecido for inválido.
     */
    public void setCpf(String cpf) throws Excecao {
        if (validaCPF(cpf)) {
            this.cpf = cpf.replaceAll("[^0-9]", "");
        }
        else {
            throw new Excecao("CPF inválido");
        }
    }
    /**
     * Retorna o nome do usuário.
     * @return String - nome do usuário.
     */
    public String getNome() {
        return nome;
    }
    /**
     * Define o nome do usuário.
     * @param nome String - nome do usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Retorna o status do acesso do usuário.
     * @return Booleano - indica se o usuário possui acesso
     * true = ativo
     * false = bloqueado.
     */
    public boolean isStatusAcessoUsuario(){
        return statusAcessoUsuario;
    }
    /**
     * Bloqueia a conta do usuário.
     */
    public void bloquearConta() {
        this.statusAcessoUsuario = false;
    }
    /**
     * Desbloqueia a conta do usuário.
     */
    public void desbloquearConta() {
        this.statusAcessoUsuario = true;
    }

    /**
     * Valida um CPF fornecido.
     * @param cpf String - CPF a ser validado.
     * @return Booleano - indica se o CPF é válido (true) ou inválido (false).
     */
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
