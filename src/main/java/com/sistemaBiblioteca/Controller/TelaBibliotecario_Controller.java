package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaBibliotecario_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NomeBibliotecario;

    @FXML
    private TextField anoPublicacao;

    @FXML
    private TextField autor;

    @FXML
    private TextField barraPesquisa;
    @FXML
    private Button botaoCriarDevolucao;
    @FXML
    private Button botaoCriarEmprestimo;
    @FXML
    private Button botaoCriarLivro;
    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoRegistrarDevolucao;

    @FXML
    private Button botaoRegistrarEmprest;

    @FXML
    private Button botaoRegistrarLivro;

    @FXML
    private Button botaoSair;

    @FXML
    private TextField categoria;
    @FXML
    private TextField cpfLeitorDevolucao;
    @FXML
    private TextField cpfLeitorCriarEmprestimo;

    @FXML
    private TextField editora;

    @FXML
    private TextField isbn;

    @FXML
    private TextField localizacaoPosicao;

    @FXML
    private TextField localizacaoPrateleira;

    @FXML
    private Pane paneRegistrarDevolucao;

    @FXML
    private Pane paneRegistrarEmprestimo;

    @FXML
    private Pane paneRegistrarLivro;

    @FXML
    private Pane paneTelaPrincipal;

    @FXML
    private TextField titulo;
    @FXML
    private TextField tituloLivroDevolucao;
    @FXML
    private TextField tituloLivroCriarEmprestimo;


    @FXML
    void registrarLivro() {
        paneRegistrarLivro.toFront();
    }

    @FXML
    void criarLivro() throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String tituloInserido = titulo.getText();
        String autorInserido = autor.getText();
        String isbnInserido = isbn.getText().replaceAll("[^0-9]", "");
        String categoriaInserida = categoria.getText();
        String anoInserido = anoPublicacao.getText();
        String editoraInserida = editora.getText();
        String prateleiraInserida = localizacaoPrateleira.getText();
        String posicaoInserida = localizacaoPosicao.getText();

        if ( (!tituloInserido.trim().isEmpty()) && (!autorInserido.trim().isEmpty()) && (!isbnInserido.trim().isEmpty()) && (!categoriaInserida.trim().isEmpty()) && (!anoInserido.trim().isEmpty())
                && (!editoraInserida.trim().isEmpty()) && (!prateleiraInserida.trim().isEmpty()) && (!posicaoInserida.trim().isEmpty()) ) {

            try {
                Livro livro = new Livro(tituloInserido, autorInserido, isbnInserido, categoriaInserida, anoInserido, editoraInserida, new Localizacao(prateleiraInserida, posicaoInserida));

                DAO.getLivroDAO().criarLivro(livro);
                telaAviso_controller.showTelaAviso("Livro " + tituloInserido + " registrado com sucesso!");

                titulo.clear();
                autor.clear();
                isbn.clear();
                categoria.clear();
                anoPublicacao.clear();
                editora.clear();
                localizacaoPrateleira.clear();
                localizacaoPosicao.clear();
                paneTelaPrincipal.toFront();
            } catch (Exception e){
                telaAviso_controller.showTelaAviso(e.getMessage());
            }
        } else telaAviso_controller.showTelaAviso("Preencha todos os campos!");

    }
    @FXML
    void registrarEmprestimo() {
        paneRegistrarEmprestimo.toFront();
    }

    @FXML
    void criarEmprestimo(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String tituloInserido = tituloLivroCriarEmprestimo.getText();
        String cpfInserido = cpfLeitorCriarEmprestimo.getText();

        if ( (!tituloInserido.trim().isEmpty()) && (!cpfInserido.trim().isEmpty())) {

            Leitor leitor = DAO.getLeitorDAO().buscarPorId(cpfInserido);

            List<Livro> livrosDoTitulo = DAO.getLivroDAO().buscarLivroPorTitulo(tituloInserido);
            Livro livroDisponivel = null;
            boolean livroEncontrado = false;
            int i = 0;
            if (!livrosDoTitulo.isEmpty()) {
                while ( (!livroEncontrado) && (i < livrosDoTitulo.size()) ) {
                    Livro livro = livrosDoTitulo.get(i);
                    if (livro.isDisponibilidade()) {
                        livroDisponivel = livro;
                        livroEncontrado = true;
                    }
                    i++;
                }
            } else telaAviso_controller.showTelaAviso("Ops! Nenhum livro correspondente ao TÍTULO informado foi localizado.");

            if (leitor != null) {
                //Leitor encontrado
                if (livroDisponivel != null) {
                    //Titulo possui livro disponivel para ser emprestado
                    if (leitor.isStatusAcessoUsuario()) {
                        //Leitor possui acesso ativo
                        Leitor primeiroDaFila = DAO.getLivroDAO().verificaPrimeiroDaFila(livroDisponivel.getTitulo());
                        if (primeiroDaFila == null ) {
                            primeiroDaFila = leitor;
                        }
                        if ( (primeiroDaFila.getCpf()).equals(leitor.getCpf()) ) {
                            //Leitor é o primeiro na fila de reservas desse título ou a fila de reservas desse título esta vazia
                            if (DAO.getLeitorDAO().qtdEmprestimosAtivosArq(leitor) < Emprestimo.limiteEmprestimosPorLeitor) {
                                //A quantidade de emprestimos ativos do leitor é menor que o limite, registrar empréstimo
                                DAO.getLivroDAO().removePrimeiroDafila(livroDisponivel.getTitulo());
                                //Remove Leitor da fila de reservas
                                DAO.getLivroDAO().removerLivroPorTitulo(tituloInserido);
                                //Remove Livro disponivel do acervo
                                Emprestimo emprestimo = new Emprestimo(livroDisponivel, leitor);
                                //Gera o emprestimo
                                DAO.getLivroDAO().atualizarAcervoPosEmprestimo(tituloInserido);
                                //Atualiza o acervo com o livro indisponivel
                                DAO.getLeitorDAO().adicionaHistoricoEmprestimosArq(leitor, emprestimo);
                                //Salva o emprestimo no historico do leitor
                                DAO.getLeitorDAO().salvarHistoricoEmprestimos();
                                //Salva no arquivo

                                telaAviso_controller.showTelaAviso(emprestimo + "\nRegistrado com sucesso!");

                                cpfLeitorCriarEmprestimo.clear();
                                tituloLivroCriarEmprestimo.clear();

                                paneTelaPrincipal.toFront();

                            } else telaAviso_controller.showTelaAviso(leitor.getNome() + ", o numero máximo de emprestimos ativos já foi atingido");
                        } else telaAviso_controller.showTelaAviso("Ops! Fila de reserva em andamento. \nPessoas na fila: " + DAO.getLivroDAO().qtdLeitoresNaFila(livroDisponivel.getTitulo()) + ". \n"
                                    + leitor.getNome() + ", reserve o livro e aguarde para solicitar empréstimo quando disponivel.");
                    } else telaAviso_controller.showTelaAviso("Ops! " + leitor.getNome() + " nao pode receber emprestimos no momento. \nAcesso Bloqueado.");
                } else telaAviso_controller.showTelaAviso("Livro: " + tituloInserido + " indisponível para emprestimo no momento." +
                            "\nPessoas na fila: " + DAO.getLivroDAO().qtdLeitoresNaFila(tituloInserido) +" . " +
                            leitor.getNome() + ", reserve o livro e aguarde para solicitar empréstimo quando disponivel.");
            } else telaAviso_controller.showTelaAviso("Ops! Nenhum cadastro vinculado ao CPF informado foi lozalidado.");
        } else telaAviso_controller.showTelaAviso("Informe o CPF e o Título do livro para registrar o emprestimo.");

    }

    @FXML
    void registrarDevolucao() {
        paneRegistrarDevolucao.toFront();
    }

    @FXML
    void criarDevolucao(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String tituloInserido = tituloLivroDevolucao.getText();
        String cpfInserido = cpfLeitorDevolucao.getText();

        if ((!tituloInserido.trim().isEmpty()) && (!cpfInserido.trim().isEmpty())) {
            Leitor leitor = DAO.getLeitorDAO().buscarPorId(cpfInserido);
            List<Emprestimo> emprestimosLeitorAtivo = DAO.getLeitorDAO().getEmprestimosAtivosArq(leitor);
            if (!emprestimosLeitorAtivo.isEmpty()) {
                boolean livroEncontrado = false;
                int i = 0;
                while ( (!livroEncontrado) && (i < emprestimosLeitorAtivo.size()) ){
                    Emprestimo emprestimoDoTitulo = emprestimosLeitorAtivo.get(i);
                    if ( tituloInserido.equalsIgnoreCase(emprestimoDoTitulo.getLivro().getTitulo() ) ) {
                        emprestimoDoTitulo.registrarDevolucao();
                        DAO.getLivroDAO().atualizarAcervoPosDevolucao(tituloInserido);
                        DAO.getLeitorDAO().salvarHistoricoEmprestimos();
                        telaAviso_controller.showTelaAviso(emprestimoDoTitulo + "\nDevolução registrada com sucesso!");
                        livroEncontrado = true;
                    }
                    i++;
                }
                if (!livroEncontrado) telaAviso_controller.showTelaAviso(leitor.getNome() + " não possui emprestimo ativo para o titulo informado ("+ tituloInserido+").");

                tituloLivroDevolucao.clear();
                cpfLeitorDevolucao.clear();
                paneTelaPrincipal.toFront();
            } else telaAviso_controller.showTelaAviso(leitor.getNome() + " não possui emprestimos ativos.");
        } else telaAviso_controller.showTelaAviso("Informe o CPF e o Título do livro para registrar devolucao do emprestimo.");
    }

    public void sair(ActionEvent event) throws Exception {
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

    @FXML
    public void iniciarPesquisa(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
        Parent root = loader.load();
        TelaInicial_Controller telaInicialController = loader.getController();
        telaInicialController.setBarra_pesquisa_ini(barraPesquisa.getText());
        telaInicialController.setSairDaPesquisaPara("/com/sistemaBiblioteca/TelaBibliotecario.fxml");
        telaInicialController.iniciarPesquisa(event);
    }

    @FXML
    void initialize() {
        assert NomeBibliotecario != null : "fx:id=\"NomeBibliotecario\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert anoPublicacao != null : "fx:id=\"anoPublicacao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoCriarEmprestimo != null : "fx:id=\"botaoCriarEmprestimo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoCriarLivro != null : "fx:id=\"botaoCriarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarDevolucao != null : "fx:id=\"botaoRegistrarDevolucao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarEmprest != null : "fx:id=\"botaoRegistrarEmprest\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarLivro != null : "fx:id=\"botaoRegistrarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert cpfLeitorCriarEmprestimo != null : "fx:id=\"cpfLeitorCriarEmprestimo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert editora != null : "fx:id=\"editora\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert localizacaoPosicao != null : "fx:id=\"localizacaoPosicao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert localizacaoPrateleira != null : "fx:id=\"localizacaoPrateleira\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneRegistrarDevolucao != null : "fx:id=\"paneRegistrarDevolucao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneRegistrarEmprestimo != null : "fx:id=\"paneRegistrarEmprestimo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneRegistrarLivro != null : "fx:id=\"paneRegistrarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneTelaPrincipal != null : "fx:id=\"paneTelaPrincipal\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert tituloLivroCriarEmprestimo != null : "fx:id=\"tituloLivroCriarEmprestimo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
    }

    public void setNome(String cpf) throws Exception {
        this.NomeBibliotecario.setText(DAO.getBibliotecarioDAO().buscarPorId(cpf).getNome());
    }

}
