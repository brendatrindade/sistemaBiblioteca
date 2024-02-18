package com.sistemaBiblioteca.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.DAO.Persistencia;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    private String sairPesquisa= null;
    private boolean admEditandoLivro = false;
    private boolean admRemovendoLivro = false;

    public void setGerarDados(boolean gerar) throws Exception {
        if (gerar) initialize2();
    }
    public void setAdmEditandoLivro(boolean sim){
        if (sim) this.admEditandoLivro = sim;
    }
    public void setAdmRemovendoLivro(boolean sim) {
        if (sim) this.admRemovendoLivro = sim;
    }

    public void setSairDaPesquisaPara(String sairPara){
        this.sairPesquisa = sairPara;
    }

    public void setBarra_pesquisa_ini(String textoInserido){
        this.barra_pesquisa_ini.setText(textoInserido);
    }

    @FXML
    public void loginLeitor(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
        String cpf = cpf_acesso.getText().replaceAll("[^0-9]", "");

        if (validaCPF(cpf)) {
            if (DAO.getLeitorDAO().cpfLeitorEstaCadastrado(cpf)) {
                // CPF inserido é valido e possui cadastro, chamar a tela do Leitor
                try {
                    Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    currentScreen.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaLeitor.fxml"));
                    Parent root = loader.load();

                    TelaLeitor_Controller telaLeitorController = loader.getController();
                    telaLeitorController.setLeitor(cpf);

                    Stage registerStage = new Stage();
                    Scene scene = new Scene(root);
                    registerStage.setResizable(false);
                    registerStage.setScene(scene);
                    registerStage.show();
                    registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));
                } catch (Exception excep) {
                    excep.printStackTrace();
                }
            } else telaAviso_controller.showTelaAviso("Ops! CPF não encontrado\n");
        } else telaAviso_controller.showTelaAviso("Ops! CPF inválido\n");

    }

    @FXML
    public void loginOperador(ActionEvent event) throws Exception {
        Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentScreen.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaLoginOperador.fxml"));
        Parent root = loader.load();
        Stage registerStage = new Stage();
        Scene scene = new Scene(root);
        registerStage.setResizable(false);
        registerStage.setScene(scene);
        registerStage.show();
        registerStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/sistemaBiblioteca/Imagens/IconeBiblioteca.png")));
    }

    @FXML
    public void iniciarPesquisa(ActionEvent event) throws Exception {

        List<Livro> titulos = new ArrayList<>();
        List<Livro> autores = new ArrayList<>();
        List<Livro> isbnes = new ArrayList<>();
        List<Livro> categorias = new ArrayList<>();
        List<Livro> anoPubli = new ArrayList<>();

        String txtInserido = barra_pesquisa_ini.getText();

        Map<String, List<Livro> > resultadoPesquisa = DAO.getLivroDAO().pesquisarLivros(txtInserido);

        if ((resultadoPesquisa.get("Titulos")) == null){
            Livro semTitulo = new Livro("Sem resultados", "-", "-", "-", "-", "-", new Localizacao("-", "-"));
            semTitulo.setDisponibilidade(false);
            titulos.add(semTitulo);
        } else{
            List<Livro> l = resultadoPesquisa.get("Titulos");
            titulos.addAll(l);
        }
        if ((resultadoPesquisa.get("Autoria")) == null){
            Livro semAutoria = new Livro("-", "Sem resultados", "-", "-", "-", "-", new Localizacao("-", "-"));
            semAutoria.setDisponibilidade(false);
            titulos.add(semAutoria);
        }else{
            List<Livro> l = resultadoPesquisa.get("Autoria");
            titulos.addAll(l);
        }
        if (((resultadoPesquisa.get("ISBN")) == null)){
            Livro semIsbn = new Livro("-", "-", "Sem resultados", "-", "-", "-", new Localizacao("-", "-"));
            semIsbn.setDisponibilidade(false);
            titulos.add(semIsbn);
        }else{
            List<Livro> l = resultadoPesquisa.get("ISBN");
            titulos.addAll(l);
        }
        if ((resultadoPesquisa.get("Categorias")) == null){
            Livro semCategoria =new Livro("-", "-", "-", "Sem resultados", "-", "-", new Localizacao("-", "-"));
            semCategoria.setDisponibilidade(false);
            titulos.add(semCategoria);
        }else{
            List<Livro> l = resultadoPesquisa.get("Categorias");
            titulos.addAll(l);
        }
        if ((resultadoPesquisa.get("Ano de Publicação")) == null){
            Livro semAno = new Livro("-", "-", "-", "-", "Sem resultados", "-", new Localizacao("-", "-"));
            semAno.setDisponibilidade(false);
            titulos.add(semAno);
        }else{
            List<Livro> l = resultadoPesquisa.get("Ano de Publicação");
            titulos.addAll(l);
        }

        if (!txtInserido.trim().isEmpty()) {
            try {
                Stage currentScreen = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentScreen.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/LivroPesquisado.fxml"));
                Parent root = loader.load();

                LivroPesquisado_Controller livroPesquisadoController = loader.getController();
                livroPesquisadoController.preencherTabela(titulos, autores, isbnes, categorias, anoPubli);
                livroPesquisadoController.setChaveBusca(txtInserido);
                livroPesquisadoController.setEditarLivros(admEditandoLivro);
                livroPesquisadoController.setRemoverLivro(admRemovendoLivro);

                if (sairPesquisa != null){
                    livroPesquisadoController.setSairPara(sairPesquisa);
                }

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
                TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
                telaAviso_controller.showTelaAviso("Digite algo para pesquisar\n");
            }

    }

    @FXML
    void initialize() {
        assert barra_pesquisa_ini != null : "fx:id=\"barra_pesquisa_ini\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert botaoAcessoOperador != null : "fx:id=\"botaoAcessoOperador\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert botao_entrar != null : "fx:id=\"botao_entrar\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert botao_ok_pesquisar != null : "fx:id=\"botao_ok_pesquisar\" was not injected: check your FXML file 'TelaInicial.fxml'.";
        assert cpf_acesso != null : "fx:id=\"cpf_acesso\" was not injected: check your FXML file 'TelaInicial.fxml'.";
    }

    void initialize2() throws Exception {

        if (!Persistencia.existeCache()) {

            DAO.getLeitorDAO().deletarTodosLeitoresArquivo();
            DAO.getLeitorDAO().deletarTodoHistoricoEmprestimo();

            DAO.getBibliotecarioDAO().deletarTodosBibliotecariosArquivo();
            DAO.getAdministradorDAO().deletarTodosAdministradores();

            DAO.getLivroDAO().deletarTodasReservas();
            DAO.getLivroDAO().deletarTodosLivrosArquivo();


            Persistencia.criarCache();

            Endereco endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
            Leitor leitor1 = new Leitor("Brenda L.", "78642486597", endereco, "74999823548");
            Endereco endereco2 = new Endereco("Nova Rua", "1001", "Fortaleza", "Ceara");
            Leitor leitor2 = new Leitor("Leitor 2.", "58138131012", endereco2, "75989873521");

            Bibliotecario bibliotecario1 = new Bibliotecario("Julia B.", "123.456.789-09", "senha123");
            Administrador administrador1 = new Administrador("Maria A.", "361.215.045-60", "senha456");

            Localizacao localizacao = new Localizacao("Z", "12");
            Livro livro1 = new Livro("Primeiro Edai", "Klack", "1357579800294", "Educativo", "1999", "Univer", localizacao);

            Localizacao localizacao2 = new Localizacao("A", "45");
            Livro livro2 = new Livro("Segundo Edai", "Klack", "2657579833294", "Educativo", "2003", "Univer", localizacao2);

            DAO.getLeitorDAO().criarLeitor(leitor1);
            DAO.getLeitorDAO().criarLeitor(leitor2);
            DAO.getBibliotecarioDAO().criarBibliotecario(bibliotecario1);
            DAO.getAdministradorDAO().criarAdministrador(administrador1);
            DAO.getLivroDAO().criarLivro(livro1);
            DAO.getLivroDAO().criarLivro(livro2);

        }
        setGerarDados(false);
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
