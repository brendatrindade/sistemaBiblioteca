/**
 * DAOgenerico
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
package DAO;
/**
 * Interface DAOgenerico: interface para operações básicas de DAO.
 * @param <Classe> O tipo de objeto que a interface DAO manipula.
 */
public interface DAOgenerico <Classe> {
    /**
     * Salva um objeto.
     * @param c - objeto a ser salvo.
     */
    void salvar(Classe c);
    /**
     * Deleta um objeto.
     * @param c - objeto a ser deletado.
     */
    void deletar(Classe c);
    /**
     * Deleta todos os objetos.
     */
    void deletarTodos();
    /**
     * Busca um objeto pela sua identificação.
     * @param id - identificação do objeto a ser buscado.
     * @return Objeto encontrado ou null se o objeto não for encontrado.
     */
    Classe buscarPorId(String id);
}
