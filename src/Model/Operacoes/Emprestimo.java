package Model.Operacoes;

import Model.Usuarios.Leitor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    public static int limiteEmprestimosPorLeitor = 3;
    private long periodoBloqueadoTotal;
    private Livro livro;
    private Leitor leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataEsperadaDev;
    private LocalDate dataRealizadaDev;
    private LocalDate dataAtual;
    private boolean statusEmprestimoFinalizado; // empréstimo concluído = true, pendente = false
    private boolean emprestimoRealizado = false;

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

    public Livro getLivro() {
        return livro;
    }
    public Leitor getLeitor() {
        return leitor;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public LocalDate getDataEsperadaDev() {
        return dataEsperadaDev;
    }
    public LocalDate getDataRealizadaDev() {
        return dataRealizadaDev;
    }
    public boolean isstatusEmprestimoFinalizado() {
        return statusEmprestimoFinalizado;
    }

    // Registrar a devolução do livro
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

    //Se o Leitor devolver um livro em atraso o metodo multar será chamado para suspender seu acesso
    public void multar(Leitor leitor, long periodoDeBloqueio) {
        this.periodoBloqueadoTotal += periodoDeBloqueio; //Se o Leitor atrasar mais de uma devolução, o periodo de suspensão será a soma de dias de todos os seus atrasos
        LocalDateTime dataBloqueio = LocalDateTime.now();
        LocalDateTime dataDesbloqueio = dataBloqueio.plusDays(periodoBloqueadoTotal);
        if(LocalDateTime.now().isBefore(dataDesbloqueio)) //Se a data atual for anterior a data de desbloqueio => bloquear Leitor
            leitor.bloquearConta();
    }
    
    // Verifica se há atraso no empréstimo
    public boolean emAtraso() { // true = atrasado, // false = em dias
        if (statusEmprestimoFinalizado)
            return dataRealizadaDev.isAfter(dataEsperadaDev);
        else
            return LocalDate.now().isAfter(dataEsperadaDev);
    }

    public long periodoDeSuspensao(){
        dataAtual = LocalDate.now();
        long diasEmAtraso = ChronoUnit.DAYS.between(dataEsperadaDev, dataAtual);
        long suspensao = 0;
        if(diasEmAtraso > 0)
            suspensao = (diasEmAtraso * 2);
        return suspensao;
    }

    public String toString() {
        if (emprestimoRealizado) {
            return ("\n" + leitor + livro + "\nLivro Disponivel: " + livro.isDisponibilidade() +
                "\nEmprestado: " + dataEmprestimo + " - Devolucao esperada: " + dataEsperadaDev + " - Devolvido: " + dataRealizadaDev +
                "\nEm atraso:" + emAtraso() + " - Finalizado: " + isstatusEmprestimoFinalizado() + ".\n");
        }
        return ("Emprestimo não realizado.");
    }

}
