package com.sistemaBiblioteca.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.DAO.Persistencia;
import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Administrador;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaInicial_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField barra_pesquisa_ini;

    @FXML
    private Button botaoAcessoOperador;

    @FXML
    private Button botao_entrar;

    @FXML
    private Button botao_ok_pesquisar;

    @FXML
    private TextField cpf_acesso;

    public void setGerarDados(boolean gerar) throws Exception {
        if (gerar) {
            initialize2();}
    };

    @FXML
    void loginLeitor(ActionEvent event) throws Exception {
        String cpf = cpf_acesso.getText().replaceAll("[^0-9]", "");
        if (validaCPF(cpf)) {
            if (DAO.getLeitorDAO().cpfLeitorEstaCadastrado(cpf)) {
                // CPF inserido é valido e possui cadastro, chamar a tela do Leitor
                try {
                    Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentScreen.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/TelaLeitor.fxml"));
                    Parent root = loader.load();

                    TelaLeitor_Controller telaLeitorController = loader.getController();
                    telaLeitorController.setNomeCliente(cpf);

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
                TelaErro_Controller telaErro_controller = new TelaErro_Controller();
                telaErro_controller.showTelaErroCPF("Ops! CPF não encontrado\n");
            }
        } else {
            TelaErro_Controller telaErro_controller = new TelaErro_Controller();
            telaErro_controller.showTelaErroCPF("Ops! CPF inválido\n");
        }
    }

    @FXML
    void loginOperador(ActionEvent event) throws Exception {

        Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentScreen.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/TelaLoginOperador.fxml"));
        Parent root = loader.load();

        Stage registerStage = new Stage();
        Scene scene = new Scene(root);
        registerStage.setResizable(false);
        registerStage.setScene(scene);
        registerStage.show();
        registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));

    }

    @FXML
    private void iniciarPesquisa(ActionEvent event) throws Exception {
        List<Livro> titulos = new ArrayList<>();
        List<Livro> autores = new ArrayList<>();
        List<Livro> isbnes = new ArrayList<>();
        List<Livro> categorias = new ArrayList<>();
        List<Livro> anoPubli = new ArrayList<>();

        String txtInserido = barra_pesquisa_ini.getText();

        Map<String, List<Livro> > resultadoPesquisa = DAO.getLivroDAO().pesquisarLivros(txtInserido);

        if ((resultadoPesquisa.get("Titulos")) == null){
            titulos.add(new Livro("Sem resultados", "-", "-", "-", "-", "-", new Localizacao("-", "-")));
        } else{
            List<Livro> l = resultadoPesquisa.get("Titulos");
            titulos.addAll(l);
        }
        if ((resultadoPesquisa.get("Autoria")) == null){
            titulos.add(new Livro("-", "Sem resultados", "-", "-", "-", "-", new Localizacao("-", "-")));
        }else{
            List<Livro> l = resultadoPesquisa.get("Autoria");
            titulos.addAll(l);
        }
        if (((resultadoPesquisa.get("ISBN")) == null)){
            titulos.add(new Livro("-", "-", "Sem resultados", "-", "-", "-", new Localizacao("-", "-")));
        }else{
            List<Livro> l = resultadoPesquisa.get("ISBN");
            titulos.addAll(l);
        }
        if ((resultadoPesquisa.get("Categorias")) == null){
            titulos.add(new Livro("-", "-", "-", "Sem resultados", "-", "-", new Localizacao("-", "-")));
        }else{
            List<Livro> l = resultadoPesquisa.get("Categorias");
            titulos.addAll(l);
        }
        if ((resultadoPesquisa.get("Ano de Publicação")) == null){
            titulos.add(new Livro("-", "-", "-", "-", "Sem resultados", "-", new Localizacao("-", "-")));
        }else{
            List<Livro> l = resultadoPesquisa.get("Ano de Publicação");
            titulos.addAll(l);
        }

        if (!txtInserido.trim().isEmpty()) {
            try {
                Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentScreen.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/SistemaBiblioteca/LivroPesquisado.fxml"));
                Parent root = loader.load();

                LivroPesquisado_Controller livroPesquisadoController = loader.getController();
                livroPesquisadoController.preencherTabela(titulos, autores, isbnes, categorias, anoPubli);

                Stage registerStage = new Stage();
                Scene scene = new Scene(root);
                registerStage.setResizable(false);
                registerStage.setScene(scene);
                registerStage.show();
                registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));
            } catch (Exception excep) {
                excep.printStackTrace();
            }
        } else{
                TelaErro_Controller telaErro_controller = new TelaErro_Controller();
                telaErro_controller.showTelaErroCPF("Digite algo para pesquisar\n");
            }

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

    @FXML
    void showRegisterStage(MouseEvent event) {

    }

    @FXML
    void initialize() throws Exception {
        assert barra_pesquisa_ini != null : "fx:id=\"barra_pesquisa_ini\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert botaoAcessoOperador != null : "fx:id=\"botaoAcessoOperador\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert botao_entrar != null : "fx:id=\"botao_entrar\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert botao_ok_pesquisar != null : "fx:id=\"botao_ok_pesquisar\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert cpf_acesso != null : "fx:id=\"cpf_acesso\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        if(Persistencia.existeCache())
            setGerarDados(true);
    }

    void initialize2() throws Exception {

        DAO.getLeitorDAO().deletarTodos();
        DAO.getBibliotecarioDAO().deletarTodos();
        DAO.getAdministradorDAO().deletarTodosAdministradores();
        DAO.getLivroDAO().deletarTodos();

        Persistencia.criarCache();

        Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        Leitor leitor1 = new Leitor("Brenda L.", "78642486597", endereco, "74999823548");
        Endereco endereco2 = new Endereco("Nova Rua", "1001", "Fortaleza", "Ceara");
        Leitor leitor2 = new Leitor("Leitor 2.", "58138131012", endereco2, "75989873521");

        Bibliotecario bibliotecario1 = new Bibliotecario("Julia B.", "123.456.789-09", "senha123");
        Administrador administrador1 = new Administrador("Maria A.", "361.215.045-60", "senha456");

        Localizacao localizacao = new Localizacao("Z", "12");
        Livro livro1 = new Livro("Primeiro Edai", "Klack", "1357579800294", "Educativo", "2024", "Univer", localizacao);

        DAO.getLeitorDAO().criarLeitor(leitor1);
        DAO.getLeitorDAO().criarLeitor(leitor2);
        DAO.getBibliotecarioDAO().criarBibliotecario(bibliotecario1);
        DAO.getAdministradorDAO().criarAdministrador(administrador1);
        DAO.getLivroDAO().criarLivro(livro1);

    }

}
