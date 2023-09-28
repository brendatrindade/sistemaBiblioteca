package Model.Operacoes;

import Excecoes.Excecao;
import Model.Usuarios.Leitor;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int limiteEmprestimosPorLeitor = 3;
    private Livro livro;
    private Leitor leitor;
    private LocalDate dataEmprestimo;
    private LocalDate dataEsperadaDev;
    private LocalDate dataRealizadaDev;
    private LocalDate dataAtual;
    private boolean statusEmprestimoFinalizado; // empréstimo concluído = true, pendente = false
    private boolean emprestimoRealizado = false;

    public Emprestimo(Livro livro, Leitor leitor) {
        try {
            if (leitor.isStatusAcessoUsuario()) {
                if(livro.verificaPrimeiroDaFila(livro.getTitulo()) == leitor || livro.verificaPrimeiroDaFila(livro.getTitulo()) == null){
                    if (leitor.qtdEmprestimosAtivos() < limiteEmprestimosPorLeitor) {
                        if (livro.isDisponibilidade()) {
                            livro.removeLeitorDaFila(livro.getTitulo());
                            livro.setDisponibilidade(false);
                            this.livro = livro;
                            this.leitor = leitor;
                            this.dataEmprestimo = LocalDate.now(); // Atribui a data atual
                            this.dataEsperadaDev = this.dataEmprestimo.plusDays(7); // Determina um prazo de 7
                            this.dataRealizadaDev = null; // Aguardando devolução
                            this.statusEmprestimoFinalizado = false; // O empréstimo inicializa por padrão como pendente
                            this.emprestimoRealizado = true;
                            leitor.setHistoricoEmprestimos(this);
                            System.out.println("Emprestimo livro: " + livro.getTitulo() + " - ISBN: " + livro.getIsbn() + " realizado com sucesso!");

                        } else throw new Excecao("Livro:" + livro.getTitulo() + " indiponível para emprestimo no momento");

                    } else throw new Excecao(leitor.getNome() + ", seu numero máximo de emprestimos ativos já foi atingido");

                } else throw new Excecao("Pessoas na fila: " + livro.qtdLeitoresNaFila(livro.getTitulo()) + ". " + leitor.getNome() + ", reserve o livro e aguarde para pegar emprestado quando disponivel." );

            } else throw new Excecao("Ops! " + leitor.getNome() + " nao pode receber emprestimos no momento.");
        }
        catch (Excecao e){
            System.out.println(e.getMessage());
        }
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
        if (this.isstatusEmprestimoFinalizado())
            System.out.println("Este empréstimo já foi concluído.");
        else {
            this.dataRealizadaDev = LocalDate.now();
            this.statusEmprestimoFinalizado = true;
            livro.setDisponibilidade(true);
            System.out.println("Devolução concluída com sucesso!");
            if (emAtraso()){
                leitor.multar(periodoDeSuspensao());
            }
        }
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
            return ("---------------------------------------------------------------------------------------------\n" +
                    leitor + livro + "\nLivro Disponivel? " + livro.isDisponibilidade() +
                    "\nEmprestado: " + dataEmprestimo + " - Devolucao esperada: " + dataEsperadaDev + " - Devolvido: " + dataRealizadaDev +
                    "\nEm atraso:" + emAtraso() + " - Finalizado? " + isstatusEmprestimoFinalizado() + ".\n");
        }
        return ("Emprestimo não realizado.");
    }

}
