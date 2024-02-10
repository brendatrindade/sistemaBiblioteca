package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class TelaBibliotecario_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NomeBibliotecario;

    @FXML
    private TextField barraPesquisa;

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
    private Pane pnlOverview;

    @FXML
    private ScrollPane telaDeRolagem;

    @FXML
    void handleClicks(ActionEvent event) {

    }

    @FXML
    void searchClient(ActionEvent event) {

    }

    @FXML
    void searchClients(KeyEvent event) {

    }

    @FXML
    void showLoginStage(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert NomeBibliotecario != null : "fx:id=\"NomeBibliotecario\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarDevolucao != null : "fx:id=\"botaoRegistrarDevolucao\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarEmprest != null : "fx:id=\"botaoRegistrarEmprest\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoRegistrarLivro != null : "fx:id=\"botaoRegistrarLivro\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert pnlOverview != null : "fx:id=\"pnlOverview\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";
        assert telaDeRolagem != null : "fx:id=\"telaDeRolagem\" was not injected: check your FXML file 'TelaBibliotecario.fxml'.";

    }

    public void setNome(String cpf) throws Exception {
        this.NomeBibliotecario.setText(DAO.getBibliotecarioDAO().buscarPorId(cpf).getNome());
    }
}
