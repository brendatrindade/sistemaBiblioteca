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

public class TelaAdministrador_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NomeAdministrador;

    @FXML
    private TextField barraPesquisa;

    @FXML
    private Button botaoBloquearAcesso;

    @FXML
    private Button botaoCadastrarLeitor;

    @FXML
    private Button botaoCadastrarOperador;

    @FXML
    private Button botaoGerarRelatorio;

    @FXML
    private Button botaoGerenciarAcervo;

    @FXML
    private Button botaoPesquisar;

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
        assert NomeAdministrador != null : "fx:id=\"NomeAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearAcesso != null : "fx:id=\"botaoBloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarLeitor != null : "fx:id=\"botaoCadastrarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarOperador != null : "fx:id=\"botaoCadastrarOperador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoGerarRelatorio != null : "fx:id=\"botaoGerarRelatorio\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoGerenciarAcervo != null : "fx:id=\"botaoGerenciarAcervo\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert pnlOverview != null : "fx:id=\"pnlOverview\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert telaDeRolagem != null : "fx:id=\"telaDeRolagem\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";

    }

    public void setNome(String cpf) throws Exception {
        this.NomeAdministrador.setText(DAO.getAdministradorDAO().buscarAdministradorPorId(cpf).getNome());
    }

}
