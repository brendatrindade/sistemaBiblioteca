package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LivroPesquisado_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Label chaveBusca;

    @FXML
    private TableColumn<String, List<Livro>> colunaAno;

    @FXML
    private TableColumn<String, List<Livro>> colunaAutor;

    @FXML
    private TableColumn<String, List<Livro>>colunaCategoria;

    @FXML
    private TableColumn<String, List<Livro>> colunaISBN;

    @FXML
    private TableColumn<String, List<Livro>> colunaTitulo;

    @FXML
    private TableView<Livro> tabelaLivros;

    public void setChaveBusca(String chaveBusca){
        this.chaveBusca.setText(chaveBusca);
    }

    public void preencherTabela(List<Livro> titulos, List<Livro> autores, List<Livro> isbnes, List<Livro> categorias, List<Livro> anoPubli ) {

        colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaAno.setCellValueFactory(new PropertyValueFactory<>("anoPublicacao"));
        colunaISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        // Preenche a tabela com os dados das listas recebidas
        ObservableList<Livro> listaLivros = FXCollections.observableArrayList();
        listaLivros.addAll(titulos);
        listaLivros.addAll(autores);
        listaLivros.addAll(categorias);
        listaLivros.addAll(anoPubli);
        listaLivros.addAll(isbnes);

        tabelaLivros.setItems(listaLivros);

    }

    @FXML
    void initialize() {
        assert chaveBusca != null : "fx:id=\"chaveBusca\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";
        assert colunaAno != null : "fx:id=\"colunaAno\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";
        assert colunaAutor != null : "fx:id=\"colunaAutor\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";
        assert colunaCategoria != null : "fx:id=\"colunaCategoria\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";
        assert colunaISBN != null : "fx:id=\"colunaISBN\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";
        assert colunaTitulo != null : "fx:id=\"colunaTitulo\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";
        assert tabelaLivros != null : "fx:id=\"tabelaLivros\" was not injected: check your FXML file 'LivroPesquisado.fxml'.";

    }

}
