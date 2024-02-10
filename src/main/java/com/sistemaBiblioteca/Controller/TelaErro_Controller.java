package com.sistemaBiblioteca.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaErro_Controller {
        @FXML
        private Text alerta;

        public void setAlerta(String aviso) {
            this.alerta.setText(aviso);
        }

        public void showTelaErroCPF(String mensage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/TelaErro.fxml"));
            Parent root = loader.load();
            TelaErro_Controller telaErro_controller = loader.getController();
            telaErro_controller.setAlerta(mensage);
            Stage alertStage = new Stage();
            Scene scene = new Scene(root);
            alertStage.setTitle("Erro");
            alertStage.setResizable(false);
            alertStage.setScene(scene);
            alertStage.showAndWait();
            alertStage.setAlwaysOnTop(true);
            alertStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));

        }

}
