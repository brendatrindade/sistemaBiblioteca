package com.sistemaBiblioteca.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaAviso_Controller {
        @FXML
        private Text alerta;

        public void setAlerta(String aviso) {
            this.alerta.setText(aviso);
        }

        public void showTelaAviso(String mensage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaAviso.fxml"));
            Parent root = loader.load();
            TelaAviso_Controller telaAviso_controller = loader.getController();
            telaAviso_controller.setAlerta(mensage);
            Stage alertStage = new Stage();
            Scene scene = new Scene(root);
            alertStage.setResizable(false);
            alertStage.setScene(scene);
            alertStage.showAndWait();
            alertStage.setAlwaysOnTop(true);
        }

}
