package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.*;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Reserva;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLeitor_Controller {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField barraPesquisa;

    @FXML
    private Button botaoCriarRenovacao;

    @FXML
    private Button botaoCriarReserva;

    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoRenovarEmprestimo;

    @FXML
    private Button botaoReservar;

    @FXML
    private Button botaoSair;

    @FXML
    private Label nomeLeitor;

    @FXML
    private Pane paneRenovarEmprestimo;

    @FXML
    private Pane paneTelaPrincipal;

    @FXML
    private Pane paneTelaReservar;

    @FXML
    private TextField tituloLivroRenovarEmprestimo;

    @FXML
    private TextField tituloReservarLivro;

    private Leitor leitor;


    @FXML
    void renovarEmprestimo(ActionEvent event) {
        this.paneRenovarEmprestimo.toFront();
    }

    @FXML
    void criarRenovacao(ActionEvent event) throws Exception {

        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String tituloInserido = tituloLivroRenovarEmprestimo.getText();
        List<Emprestimo> emprestimosAtivosLeitor = DAO.getLeitorDAO().getEmprestimosAtivosArq(this.leitor);
        Emprestimo emprestimoParaRenovar = null;

        if ((!tituloInserido.trim().isEmpty())) {
            //Titulo preenchido
            if (this.leitor.isStatusAcessoUsuario()) {
                //Leitor possui acesso ativo
                if (!emprestimosAtivosLeitor.isEmpty()) {
                    //Leitor possui emprestimos ativos

                    Leitor primeiroDaFila = DAO.getLivroDAO().verificaPrimeiroDaFila(tituloInserido);
                    if (primeiroDaFila == null ) {
                        primeiroDaFila = leitor;
                    }
                    if ( (primeiroDaFila.getCpf()).equals(leitor.getCpf()) ) {

                        for (Emprestimo emprestimo : emprestimosAtivosLeitor) {
                            if (emprestimo.getLivro().getTitulo().equalsIgnoreCase(tituloInserido)) {
                                //Emprestimo correspondente ao Titulo
                                emprestimoParaRenovar = emprestimo;
                                break;
                            }
                        }
                        if (emprestimoParaRenovar != null) {
                            //Emprestimo correspondente ao titulo ativo encontrado, solicitar renovacao
                            boolean renovado = emprestimoParaRenovar.solicitarRenovacao(emprestimoParaRenovar);
                            if (renovado) {
                                DAO.getLeitorDAO().adicionaHistoricoEmprestimosArq(this.leitor, emprestimoParaRenovar);
                                //Salva o emprestimo no historico do leitor
                                DAO.getLeitorDAO().salvarHistoricoEmprestimos();
                                //Salva no arquivo
                                telaAviso_controller.showTelaAviso(emprestimoParaRenovar + "\nRenovado com sucesso!");
                            } else {
                                if (emprestimoParaRenovar.emAtraso())
                                    telaAviso_controller.showTelaAviso(emprestimoParaRenovar + "\nEm atraso, não é possivel renovar.");
                                else if (emprestimoParaRenovar.getNumeroDeRenovacoes() == Emprestimo.limiteRenovacoesPorEmprestimo)
                                    telaAviso_controller.showTelaAviso(emprestimoParaRenovar + "\nNúmero máximo de renovações excedido. Não é possível renovar.");
                            }

                            tituloLivroRenovarEmprestimo.clear();
                            paneTelaPrincipal.toFront();
                        } else telaAviso_controller.showTelaAviso("Ops! Nenhum livro emprestado corresponde ao titulo informado.");
                    } else telaAviso_controller.showTelaAviso("Ops! Este titulo já está reservado. Não foi possivel concluir a renovação." +
                            "\nPessoas na fila: " + DAO.getLivroDAO().qtdLeitoresNaFila(tituloInserido) +" .\n" +
                            leitor.getNome() + ", reserve o livro e aguarde para solicitar empréstimo novamente quando disponivel.");
                } else telaAviso_controller.showTelaAviso("Não há empréstimos ativos disponíveis para renovação no momento");
            } else telaAviso_controller.showTelaAviso("Acesso bloqueado, não é possivel renovar.");
        } else telaAviso_controller.showTelaAviso("Informe o Título do livro para solicitar renovação do emprestimo.");

    }

    @FXML
    void reservarLivro(ActionEvent event) {
        this.paneTelaReservar.toFront();

    }

    @FXML
    void criarReserva(ActionEvent event) throws Exception {

        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String tituloParaReservar = tituloReservarLivro.getText();

        List<Livro> livrosPorTitulo = DAO.getLivroDAO().buscarLivroPorTitulo(tituloParaReservar);
        List<Livro> livrosDisponiveis = new ArrayList<>();

        if (!livrosPorTitulo.isEmpty()) {

            for (Livro livro : livrosPorTitulo) {
                if (livro.isDisponibilidade()) {
                    livrosDisponiveis.add(livro);
                }
            }
            if (livrosDisponiveis.isEmpty()) {

                if (this.leitor.isStatusAcessoUsuario()) {

                    Queue<Leitor> leitoresNaFila = DAO.getLivroDAO().getReservasPorTitulo(tituloParaReservar);

                    if (leitoresNaFila == null) {
                        leitoresNaFila = new LinkedList<>();
                    }
                    boolean leitorEstaNaFila = false;
                    for (Leitor leitorNaFila : leitoresNaFila){
                        if( (leitorNaFila.getCpf()).equals(this.leitor.getCpf()) ) {
                            leitorEstaNaFila = true;
                        }
                    }
                    if (!leitorEstaNaFila) {
                        leitoresNaFila.add(this.leitor);
                        DAO.getLivroDAO().setLeitoresReservasPorTitulo(tituloParaReservar, leitoresNaFila);
                        Reserva novaReserva = new Reserva(leitor, tituloParaReservar);
                        novaReserva.setTamanhoFila(leitoresNaFila.size());

                        DAO.getLivroDAO().salvarReservasPorTituloArquivo();

                        telaAviso_controller.showTelaAviso(novaReserva + "\nReserva realizada com sucesso!");

                        tituloReservarLivro.clear();
                        paneTelaPrincipal.toFront();

                    } else telaAviso_controller.showTelaAviso("Ops! " + leitor.getNome() + " já esta na fila do livro: " + tituloParaReservar + ".\n" +
                            "\nPessoas na fila: " + DAO.getLivroDAO().qtdLeitoresNaFila(tituloParaReservar) + ".");

                } else telaAviso_controller.showTelaAviso("Ops! " + leitor.getNome() + " nao pode reservar livros no momento. \nAcesso bloqueado.");

            } else telaAviso_controller.showTelaAviso("Ops! " + tituloParaReservar + " já esta disponivel para emprestimo.");

        } else telaAviso_controller.showTelaAviso("Ops! Titulo nao localizado.");

    }

    @FXML
    public void iniciarPesquisa(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
        Parent root = loader.load();
        TelaInicial_Controller telaInicialController = loader.getController();
        telaInicialController.setBarra_pesquisa_ini(barraPesquisa.getText());
        telaInicialController.setSairDaPesquisaPara("/com/sistemaBiblioteca/TelaLeitor.fxml");
        telaInicialController.iniciarPesquisa(event);

    }

    @FXML
    public void sair(ActionEvent event) {
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
            Parent root = loader.load();
            Stage registerStage = new Stage();
            registerStage.setTitle("Sistema de Biblioteca");
            Scene scene = new Scene(root);
            registerStage.setResizable(false);
            registerStage.setScene(scene);
            registerStage.show();
            registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));

        } catch (Exception excep) {
            excep.printStackTrace();
        }
    }

    public void setLeitor(String cpf) throws Exception {
        this.nomeLeitor.setText(DAO.getLeitorDAO().buscarPorId(cpf).getNome());
        this.leitor = DAO.getLeitorDAO().buscarPorId(cpf);
    }

    @FXML
    void initialize() {
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoCriarRenovacao != null : "fx:id=\"botaoCriarRenovacao\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoCriarReserva != null : "fx:id=\"botaoCriarReserva\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoRenovarEmprestimo != null : "fx:id=\"botaoRenovarEmprestimo\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoReservar != null : "fx:id=\"botaoReservar\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert nomeLeitor != null : "fx:id=\"nomeLeitor\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert paneRenovarEmprestimo != null : "fx:id=\"paneRenovarEmprestimo\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert paneTelaPrincipal != null : "fx:id=\"paneTelaPrincipal\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert paneTelaReservar != null : "fx:id=\"paneTelaReservar\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert tituloLivroRenovarEmprestimo != null : "fx:id=\"tituloLivroRenovarEmprestimo\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert tituloReservarLivro != null : "fx:id=\"tituloReservarLivro\" was not injected: check your FXML file 'TelaLeitor.fxml'.";

    }
}
