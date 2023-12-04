/**
 * Relatorio
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
package Model.Operacoes;

import java.io.Serializable;
import java.util.List;

public class Relatorio implements Serializable {
    private long numLivrosEmprestados;
    private long numLivrosAtrasados;
    private long numLivrosReservados;
    private List<Livro> livrosMaisPopulares;

    //Em construção

}
