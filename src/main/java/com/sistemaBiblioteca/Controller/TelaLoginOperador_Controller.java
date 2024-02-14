package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TelaLoginOperador_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botao_entrar;

    @FXML
    private TextField cpf_acesso_operador;

    @FXML
    private PasswordField senha_operador;

    @FXML
    void loginOperador(ActionEvent event) throws Exception {
        String cpf = cpf_acesso_operador.getText().replaceAll("[^0-9]", "");
        if (validaCPF(cpf)) {
            if (DAO.getBibliotecarioDAO().cpfBibliotecarioEstaCadastrado(cpf)) {
                if (DAO.getBibliotecarioDAO().buscarPorId(cpf).getCargo() == "Bibliotecario") {
                    //CPF do operador é válido, possui cadastro e é bibliotecario
                    try {
                        Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentScreen.close();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/TelaBibliotecario.fxml"));
                        Parent root = loader.load();

                        TelaBibliotecario_Controller telaBibliotecarioController = loader.getController();
                        telaBibliotecarioController.setNome(cpf);

                        Stage registerStage = new Stage();
                        Scene scene = new Scene(root);
                        registerStage.setResizable(false);
                        registerStage.setScene(scene);
                        registerStage.show();
                        registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));
                    } catch (Exception excep) {
                        excep.printStackTrace();
                    }
                }
            } else {
                if (DAO.getAdministradorDAO().cpfAdministradorEstaCadastrado(cpf)) {
                    if (DAO.getAdministradorDAO().buscarAdministradorPorId(cpf).getCargo() == "Administrador") {
                        //CPF do operador é válido, possui cadastro e é administrador
                        try {
                            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            currentScreen.close();
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/TelaAdministrador.fxml"));
                            Parent root = loader.load();

                            TelaAdministrador_Controller telaAdministradorController = loader.getController();
                            telaAdministradorController.setNome(cpf);

                            Stage registerStage = new Stage();
                            Scene scene = new Scene(root);
                            registerStage.setResizable(false);
                            registerStage.setScene(scene);
                            registerStage.show();
                            registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));
                        } catch (Exception excep) {
                            excep.printStackTrace();
                        }
                    }
                } else {
                    TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
                    telaAviso_controller.showTelaAviso("Ops! CPF não encontrado\n");
                }
            }
        } else {
            TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
            telaAviso_controller.showTelaAviso("Ops! CPF inválido\n");
        }
    }

    @FXML
    void initialize() {
        assert botao_entrar != null : "fx:id=\"botao_entrar\" was not injected: check your FXML file 'TelaLoginOperador.fxml'.";
        assert cpf_acesso_operador != null : "fx:id=\"cpf_acesso_operador\" was not injected: check your FXML file 'TelaLoginOperador.fxml'.";
        assert senha_operador != null : "fx:id=\"senha_operador\" was not injected: check your FXML file 'TelaLoginOperador.fxml'.";
    }

    public boolean validaCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", ""); //todos caracteres não correspondentes a um numero serao removidos
        if (cpf.length() != 11)
            return false;
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);

        if (primeiroDigito >= 10)
            primeiroDigito = 0;

        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito)
            return false;

        soma = 0;

        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }

        int segundoDigito = 11 - (soma % 11);

        if (segundoDigito >= 10)
            segundoDigito = 0;

        return (Character.getNumericValue(cpf.charAt(10)) == segundoDigito);
    }
}
