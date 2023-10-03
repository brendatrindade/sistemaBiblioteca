/**
 * Emprestimo
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

import Model.Usuarios.Leitor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 * Classe Emprestimo: Representa o empréstimo de um livro por um leitor.
 */
public class Emprestimo {
    public static int limiteEmprestimosPorLeitor = 3;
    public static int limiteRenovacoesPorEmprestimo = 2;
    private int numeroDeRenovacoes = 0;
    private LocalDate dataRenovacao1;
    private LocalDate dataRenovacao2;
    private long periodoBloqueadoTotal;
    private Livro livro;
    private Leitor leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataEsperadaDev;
    private LocalDate dataRealizadaDev;
    private LocalDate dataAtual;
    private boolean statusEmprestimoFinalizado; // empréstimo concluído = true, pendente = false
    private boolean emprestimoRealizado = false;
    /**
     * Construtor da classe Emprestimo.
     * @param livro objeto Livro - livro a ser emprestado.
     * @param leitor objeto Leitor - leitor que está recebendo o empréstimo.
     */
    public Emprestimo(Livro livro, Leitor leitor) {
        livro.setDisponibilidade(false);
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo = LocalDate.now(); // Atribui a data atual
        this.dataEsperadaDev = this.dataEmprestimo.plusDays(7); // Determina um prazo de 7
        this.dataRealizadaDev = null; // Aguardando devolução
        this.statusEmprestimoFinalizado = false; // O empréstimo inicializa por padrão como pendente
        this.emprestimoRealizado = true;
    }
    /**
     * Retorna o livro associado ao empréstimo.
     * @return Livro - livro emprestado.
     */
    public Livro getLivro() {
        return livro;
    }
    /**
     * Retorna o leitor associado ao empréstimo.
     * @return Leitor - leitor que recebeu o empréstimo.
     */
    public Leitor getLeitor() {
        return leitor;
    }
    /**
     * Retorna a data em que o empréstimo foi realizado.
     * @return LocalDate - a data do empréstimo.
     */
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    /**
     * Retorna a data limite esperada para devolução do livro e finalização do empréstimo.
     * @return LocalDate - data esperada de devolução do livro.
     */
    public LocalDate getDataEsperadaDev() {
        return dataEsperadaDev;
    }
    /**
     * Retorna a data real de devolução do livro e finalização do empréstimo.
     * @return LocalDate - data real de devolução do livro.
     */
    public LocalDate getDataRealizadaDev() {
        return dataRealizadaDev;
    }
    /**
     * Retorna a data realizada da primeira renovação do emprestimo.
     * @return LocalDate - data da primeira renovação.
     */
    public LocalDate getDataRenovacao1() {
        return dataRenovacao1;
    }
    /**
     * Retorna a data realizada da segunda renovação do emprestimo.
     * @return LocalDate - data da segunda renovação.
     */
    public LocalDate getDataRenovacao2() {
        return dataRenovacao2;
    }
    /**
     * Retorna a quantidade de renovações realizadas do emprestimo.
     * @return LocalDate - quantidade de renovações.
     */
    public int getNumeroDeRenovacoes() {
        return numeroDeRenovacoes;
    }
    /**
     * Verifica se o empréstimo foi finalizado.
     * @return boolean - indica se o empréstimo foi finalizado (true) ou não (false).
     */
    public boolean isstatusEmprestimoFinalizado() {
        return statusEmprestimoFinalizado;
    }
    /**
     * Registrar a devolução do livro.
     */
    public void registrarDevolucao() {
        if (this.isstatusEmprestimoFinalizado()){
            return;
        }
        else {
            this.dataRealizadaDev = LocalDate.now();
            this.statusEmprestimoFinalizado = true;
            livro.setDisponibilidade(true);
            if (emAtraso()){
                multar(leitor, periodoDeSuspensao());
            }
        }
    }
    /**
     * Solicita a renovação de um empréstimo.
     * @return boolean - indica se a renovação foi concluída (true) ou nao (false).
     */
    public boolean solicitarRenovacao(Emprestimo emprestimo) {
        if(!emAtraso()){
            if (emprestimo.numeroDeRenovacoes < Emprestimo.limiteRenovacoesPorEmprestimo) {
                emprestimo.numeroDeRenovacoes++;
                emprestimo.dataEsperadaDev = emprestimo.dataEsperadaDev.plusDays(3); //Adiciona 3 dias no prazo de devolução
                if (numeroDeRenovacoes == 1)
                    emprestimo.dataRenovacao1 = LocalDate.now();
                if (numeroDeRenovacoes == 2)
                    emprestimo.dataRenovacao2 = LocalDate.now();
                return true;
            }
        }
        return false;
    }
    /**
     * Se o Leitor devolver um livro em atraso o metodo multar será chamado para suspender seu acesso
     * @param leitor objeto Leitor - leitor que está devolvendo o livro.
     * @param periodoDeBloqueio long - período de bloqueio para o leitor.
     */
    public void multar(Leitor leitor, long periodoDeBloqueio) {
        this.periodoBloqueadoTotal += periodoDeBloqueio; //Se o Leitor atrasar mais de uma devolução, o periodo de suspensão será a soma de dias de todos os seus atrasos
        LocalDateTime dataBloqueio = LocalDateTime.now();
        LocalDateTime dataDesbloqueio = dataBloqueio.plusDays(periodoBloqueadoTotal);
        if(LocalDateTime.now().isBefore(dataDesbloqueio)) //Se a data atual for anterior a data de desbloqueio => bloquear Leitor
            leitor.bloquearConta();
    }
    /**
     * Verifica se há atraso no empréstimo.
     * @return boolean - indica se o empréstimo está em atraso (true) ou não (false).
     */
    public boolean emAtraso() {
        if (statusEmprestimoFinalizado)
            return dataRealizadaDev.isAfter(dataEsperadaDev);
        else
            return LocalDate.now().isAfter(dataEsperadaDev);
    }
    /**
     * Calcula o período de suspensão para um empréstimo atrasado.
     * @return long - período de suspensão em dias.
     */
    public long periodoDeSuspensao(){
        dataAtual = LocalDate.now();
        long diasEmAtraso = ChronoUnit.DAYS.between(dataEsperadaDev, dataAtual);
        long suspensao = 0;
        if(diasEmAtraso > 0)
            suspensao = (diasEmAtraso * 2);
        return suspensao;
    }
    /**
     * Retorna uma representação em string do empréstimo.
     * @return String - representa o empréstimo.
     */
    public String toString() {
        if (emprestimoRealizado) {
            return ("\n" + leitor + livro + "\nLivro Disponivel: " + livro.isDisponibilidade() +
                "\nEmprestado: " + dataEmprestimo + " - Devolucao esperada: " + dataEsperadaDev + " - Devolvido: " + dataRealizadaDev +
                "\nEm atraso:" + emAtraso() + " - Finalizado: " + isstatusEmprestimoFinalizado() +
                "\nRenovacoes: " + numeroDeRenovacoes);
        }
        return ("Emprestimo não realizado.");
    }
}
