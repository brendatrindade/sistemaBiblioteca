package com.sistemaBiblioteca;

import com.sistemaBiblioteca.Controller.TelaInicial_Controller;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application{

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("TelaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        TelaInicial_Controller telaInicial = fxmlLoader.getController();
        telaInicial.setGerarDados(true);

        stage.setTitle("Sistema de Biblioteca");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));

    }
    public static void main(String[] args) {
        launch();
    }
}

//Mudar selecao de livro para a tabela
//verificar gerar dados inicias
//limpar arquivos after dos testes