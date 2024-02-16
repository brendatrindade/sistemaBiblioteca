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
    private Button botaoSair;

    @FXML
    private TextField cpf_acesso_operador;

    @FXML
    private PasswordField senha_operador;


    @FXML
    void sair(ActionEvent event) {
        try {
            Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentScreen.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
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
    void loginOperador(ActionEvent event) throws Exception {

            String cpf = cpf_acesso_operador.getText().replaceAll("[^0-9]", "");
            String senhaInserida = senha_operador.getText();

            if ( (!cpf.trim().isEmpty()) && (!senhaInserida.trim().isEmpty())) {
                //Campos cpf e senha foram preenchidos
                if (validaCPF(cpf)) {
                    //CPF inserido é válido
                    if(DAO.getBibliotecarioDAO().cpfBibliotecarioEstaCadastrado(cpf)){
                        //CPF possui cadastro
                        if ("Bibliotecario".equals(DAO.getBibliotecarioDAO().buscarPorId(cpf).getCargo())) {
                            //Cargo é bibliotecario
                            if (senhaInserida.equals(DAO.getBibliotecarioDAO().getSenhaBibliotecario(cpf))) {
                                //A senha está correta, acessar.
                                try {
                                    Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    currentScreen.close();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaBibliotecario.fxml"));
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
                            } else {
                                TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
                                telaAviso_controller.showTelaAviso("Ops! Senha inválida.\n");
                            }
                        }
                    } else {
                        if (DAO.getAdministradorDAO().cpfAdministradorEstaCadastrado(cpf)){
                            //CPF possui cadastro
                            if ("Administrador".equals(DAO.getAdministradorDAO().buscarAdministradorPorId(cpf).getCargo())) {
                                //Cargo é administrador
                                if ( senhaInserida.equals(DAO.getAdministradorDAO().getSenhaAdiministrador(cpf)) ) {
                                    //A senha está correta, acessar.
                                    try {
                                        Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        currentScreen.close();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaAdministrador.fxml"));
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
                                } else {
                                    TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
                                    telaAviso_controller.showTelaAviso("Ops! Senha inválida.\n");
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
            } else{
                TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
                telaAviso_controller.showTelaAviso("Informe o CPF e a SENHA para acessar\n");
            }
    }

    @FXML
    void initialize() {
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaLoginOperador.fxml'.";
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
