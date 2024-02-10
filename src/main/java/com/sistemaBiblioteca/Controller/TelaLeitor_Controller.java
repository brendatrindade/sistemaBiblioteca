package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class TelaLeitor_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NomeCliente;

    @FXML
    private TextField barraPesquisa;

    @FXML
    private Button botaoHistoricoEmprest;

    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoReservas;

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
        assert NomeCliente != null : "fx:id=\"NomeCliente\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoHistoricoEmprest != null : "fx:id=\"botaoHistoricoEmprest\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoReservas != null : "fx:id=\"botaoReservas\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert pnlOverview != null : "fx:id=\"pnlOverview\" was not injected: check your FXML file 'TelaLeitor.fxml'.";
        assert telaDeRolagem != null : "fx:id=\"telaDeRolagem\" was not injected: check your FXML file 'TelaLeitor.fxml'.";

    }

    public void setNomeCliente(String cpf) throws Exception {
        this.NomeCliente.setText(DAO.getLeitorDAO().buscarPorId(cpf).getNome());
    }

}
