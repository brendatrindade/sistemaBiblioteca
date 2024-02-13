package com.sistemaBiblioteca.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Main;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    private ScrollPane telaDeRolagem;

    @FXML
    private TextField titulo;


    @FXML
    void registrarLivro() {
        paneRegistrarLivro.toFront();
    }
    @FXML
    void registrarEmprestimo() {
        paneRegistrarEmprestimo.toFront();
    }
    @FXML
    void registrarDevolucao() {
        paneRegistrarDevolucao.toFront();
    }

    public void sair(ActionEvent event) throws Exception {

        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/TelaInicial.fxml"));
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
    void initialize() {
        assert NomeBibliotecario != null : "fx:id=\"NomeBibliotecario\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert anoPublicacao != null : "fx:id=\"anoPublicacao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoCriarLivro != null : "fx:id=\"botaoCriarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarDevolucao != null : "fx:id=\"botaoRegistrarDevolucao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarEmprest != null : "fx:id=\"botaoRegistrarEmprest\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarLivro != null : "fx:id=\"botaoRegistrarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert editora != null : "fx:id=\"editora\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert localizacaoPosicao != null : "fx:id=\"localizacaoPosicao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert localizacaoPrateleira != null : "fx:id=\"localizacaoPrateleira\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneRegistrarDevolucao != null : "fx:id=\"paneRegistrarDevolucao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneRegistrarEmprestimo != null : "fx:id=\"paneRegistrarEmprestimo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneRegistrarLivro != null : "fx:id=\"paneRegistrarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert paneTelaPrincipal != null : "fx:id=\"paneTelaPrincipal\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert telaDeRolagem != null : "fx:id=\"telaDeRolagem\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
    }

    public void setNome(String cpf) throws Exception {
        this.NomeBibliotecario.setText(DAO.getBibliotecarioDAO().buscarPorId(cpf).getNome());
    }

}
