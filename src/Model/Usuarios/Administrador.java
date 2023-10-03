/**
 * Administrador
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
/**
 * SubClasse Administrador: Especialização de um usuário operador do sistema de biblioteca que representa um administrador.
 * Esta classe herda de Bibliotecario.
 * Acesso a todas as funcionalidades.
 */
public class Administrador extends Bibliotecario{
    private boolean cadastroRealizado = false;
    /**
     * Construtor da classe Administrador.
     * @param nome String - nome do administrador.
     * @param cpf String - CPF do administrador.
     * @param senha String - senha do administrador.
     * @throws Excecao Se o CPF fornecido for inválido.
     */
    public Administrador(String nome, String cpf, String senha) throws Excecao {
        super(nome, cpf, senha);
        super.setCargo("Administrador");
        this.cadastroRealizado = true;

    }
    /**
     * Bloqueia a conta de um leitor.
     * @param leitor objeto Leitor - leitor a ser bloqueado.
     */
    public void bloquearLeitor(Leitor leitor) {
        leitor.bloquearConta();
    }
    /**
     * Desbloqueia a conta de um leitor.
     * @param leitor objeto Leitor - leitor a ser desbloqueado.
     */
    public void desbloquearLeitor(Leitor leitor) {
        leitor.desbloquearConta();
    }
    /**
     * Bloqueia a conta de um bibliotecário.
     * @param bibliotecario objeto Bibliotecario - bibliotecário a ser bloqueado.
     */
    public void bloquearBibliotecario(Bibliotecario bibliotecario) {
        bibliotecario.bloquearConta();
    }
    /**
     * Desbloqueia a conta de um bibliotecário.
     * @param bibliotecario objeto Bibliotecario - bibliotecário a ser desbloqueado.
     */
    public void desbloquerBibliotecario(Bibliotecario bibliotecario){
        bibliotecario.desbloquearConta();
    }
    /**
     * Retorna a representação em String do objeto Administrador.
     * @return String - representa o administrador.
     */
    public String toString() {
        if (cadastroRealizado) {
            return ("\nAdministrador(a): " + getNome() + " - CPF: " + getCpf() + " .\n");
        }
        return ("Administrador(a) não cadastrado");
    }
}
