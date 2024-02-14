package com.sistemaBiblioteca.Controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class TelaRegistrarLivro_Controller{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane telaResgistroDeLivro;

    @FXML
    void initialize() {
        assert telaResgistroDeLivro != null : "fx:id=\"telaResgistroDeLivro\" was not injected: check your FXML file 'telaRegistrarLivro.fxml'.";

    }

}
