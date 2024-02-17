package com.sistemaBiblioteca.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.sistemaBiblioteca.DAO.DAO;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaAdministrador_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label NomeAdministrador;

    @FXML
    private TextField anoPublicacao;

    @FXML
    private TextField autor;

    @FXML
    private TextField barraPesquisa;

    @FXML
    private Button botaoAdicionarLivro;

    @FXML
    private Button botaoBloquearAcesso;

    @FXML
    private Button botaoBloquearAdministrador;

    @FXML
    private Button botaoBloquearAdministrador11;

    @FXML
    private Button botaoBloquearBibliotecario;

    @FXML
    private Button botaoBloquearLeitor;

    @FXML
    private Button botaoCadastrarAdministrador;

    @FXML
    private Button botaoCadastrarBibliotecario;

    @FXML
    private Button botaoCadastrarLeitor;

    @FXML
    private Button botaoCriarAdministrador;

    @FXML
    private Button botaoCriarBibliotecario;

    @FXML
    private Button botaoCriarLeitor;

    @FXML
    private Button botaoCriarLivro;

    @FXML
    private Button botaoDesbloquearAcesso;

    @FXML
    private Button botaoDesbloquearAdministrador;

    @FXML
    private Button botaoDesbloquearBibliotecario;

    @FXML
    private Button botaoDesbloquearLeitor;

    @FXML
    private Button botaoGerarRelatorio;

    @FXML
    private Button botaoGerenciarAcervo;

    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoPesquisarHistLeitor;

    @FXML
    private Button botaoRemoverLivro;

    @FXML
    private Button botaoSair;

    @FXML
    private TextField cargoAdministrador;

    @FXML
    private TextField cargoBibliotecario;

    @FXML
    private TextField categoria;

    @FXML
    private TextField cidade;

    @FXML
    private TextField cpf;

    @FXML
    private TextField cpfAdministrador;

    @FXML
    private TextField cpfBibliotecario;

    @FXML
    private TextField cpfBloquearAdministrador;

    @FXML
    private TextField cpfBloquearBibliotecario;

    @FXML
    private TextField cpfBloquearLeitor;

    @FXML
    private TextField cpfDesbloquearAdministrador;

    @FXML
    private TextField cpfDesbloquearBibliotecario;

    @FXML
    private TextField cpfDesbloquearLeitor;

    @FXML
    private TextField editora;

    @FXML
    private TextField estado;

    @FXML
    private TextField isbn;

    @FXML
    private TextField localizacaoPosicao;

    @FXML
    private TextField localizacaoPrateleira;

    @FXML
    private TextField nome;

    @FXML
    private TextField nomeAdministrador;

    @FXML
    private TextField nomeBibliotecario;

    @FXML
    private TextField numero;

    @FXML
    private Pane paneBloquearAcesso;

    @FXML
    private Pane paneCadastrarAdministrador;

    @FXML
    private Pane paneCadastrarBibliotecario;

    @FXML
    private Pane paneCadastrarLeitor;

    @FXML
    private Pane paneDesbloquearAcesso;

    @FXML
    private Pane paneGerarRelatorio;

    @FXML
    private Pane paneGerenciarAcervo;

    @FXML
    private Pane panePrincipal;

    @FXML
    private Pane paneRegistrarLivro;

    @FXML
    private Label qtdLivrosAtrasados;

    @FXML
    private Label qtdLivrosEmprestados;

    @FXML
    private Label qtdLivrosReservados;

    @FXML
    private TextField rua;

    @FXML
    private TextField senhaAdministrador;

    @FXML
    private TextField senhaBibliotecario;

    @FXML
    private TextField telefone;

    @FXML
    private TextField titulo;

    @FXML
    private Label tituloLivroMaisPopular;


    private Administrador administrador;


    @FXML
    void cadastrarLeitor(ActionEvent event) {
        paneCadastrarLeitor.toFront();
    }
    @FXML
    void criarLeitor(ActionEvent event) throws IOException {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
        try {
            Leitor leitor = new Leitor(nome.getText(), cpf.getText(), new Endereco(rua.getText(), numero.getText(), cidade.getText(), estado.getText()), telefone.getText() );
            DAO.getLeitorDAO().criarLeitor(leitor);
            telaAviso_controller.showTelaAviso("Leitor " + nome.getText()+ " cadastrado com sucesso!");
            nome.clear();
            cpf.clear();
            rua.clear();
            numero.clear();
            cidade.clear();
            estado.clear();
            telefone.clear();
            panePrincipal.toFront();
        } catch (Exception e){
            telaAviso_controller.showTelaAviso(e.getMessage());
        }
    }
    @FXML
    void cadastrarAdministrador(ActionEvent event) {
        paneCadastrarAdministrador.toFront();
    }
    @FXML
    void criarAdministrador(ActionEvent event) throws IOException {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
        try {
            Administrador administrador = new Administrador( nomeAdministrador.getText(), cpfAdministrador.getText(), senhaAdministrador.getText() );
            DAO.getAdministradorDAO().criarAdministrador(administrador);
            telaAviso_controller.showTelaAviso("Administrador " + nomeAdministrador.getText()+ " cadastrado com sucesso!");
            nomeAdministrador.clear();
            cpfAdministrador.clear();
            senhaAdministrador.clear();
            panePrincipal.toFront();
        } catch (Exception e){
            telaAviso_controller.showTelaAviso(e.getMessage());
        }
    }
    @FXML
    void cadastrarBibliotecario(ActionEvent event) {
        paneCadastrarBibliotecario.toFront();
    }
    @FXML
    void criarBibliotecario(ActionEvent event) throws IOException {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
        try {
            Bibliotecario bibliotecario = new Bibliotecario( nomeBibliotecario.getText(), cpfBibliotecario.getText(), senhaBibliotecario.getText() );
            DAO.getBibliotecarioDAO().criarBibliotecario(bibliotecario);
            telaAviso_controller.showTelaAviso("Bibliotecario " + nomeBibliotecario.getText()+ " cadastrado com sucesso!");
            nomeBibliotecario.clear();
            cpfBibliotecario.clear();
            senhaBibliotecario.clear();
            panePrincipal.toFront();
        } catch (Exception e){
            telaAviso_controller.showTelaAviso(e.getMessage());
        }
    }

    @FXML
    void bloquearAcesso(ActionEvent event) {
        paneBloquearAcesso.toFront();
    }
    @FXML
    void criarBloquearAdministrador(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String cpfAdministrador = cpfBloquearAdministrador.getText();
        Administrador administradorBlock = DAO.getAdministradorDAO().buscarAdministradorPorId(cpfAdministrador);
        administradorBlock.bloquearConta();
        DAO.getAdministradorDAO().salvarAdministradorArquivo();

        if (!DAO.getAdministradorDAO().buscarAdministradorPorId(cpfAdministrador).isStatusAcessoUsuario())
            telaAviso_controller.showTelaAviso("Acesso bloqueado com sucesso!");
        else telaAviso_controller.showTelaAviso("Ops! Não foi possível realizar o bloqueio.");
    }

    @FXML
    void criarBloquearBibliotecario(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String cpfBibliotecario = cpfBloquearBibliotecario.getText();
        Bibliotecario bibliotecarioBlock = DAO.getBibliotecarioDAO().buscarPorId(cpfBibliotecario);
        administrador.bloquearBibliotecario(bibliotecarioBlock);
        DAO.getBibliotecarioDAO().salvarBibliotecarioArquivo();

        if (!DAO.getBibliotecarioDAO().buscarPorId(cpfBibliotecario).isStatusAcessoUsuario())
            telaAviso_controller.showTelaAviso("Acesso bloqueado com sucesso!");
        else telaAviso_controller.showTelaAviso("Ops! Não foi possível realizar o bloqueio.");

    }
    @FXML
    void criarBloquearLeitor(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String cpfLeitor = cpfBloquearLeitor.getText();
        Leitor leitorBlock = DAO.getLeitorDAO().buscarPorId(cpfLeitor);
        administrador.bloquearLeitor(leitorBlock);
        DAO.getLeitorDAO().salvarLeitoresArquivo();

        if(!DAO.getLeitorDAO().buscarPorId(cpfLeitor).isStatusAcessoUsuario())
            telaAviso_controller.showTelaAviso("Acesso bloqueado com sucesso!");
        else telaAviso_controller.showTelaAviso("Ops! Não foi possível realizar o bloqueio.");
    }

    @FXML
    void desbloquearAcesso(ActionEvent event) {
        paneDesbloquearAcesso.toFront();
    }

    @FXML
    void criarDesbloquearAdministrador(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String cpfAdministrador = cpfDesbloquearAdministrador.getText();
        Administrador administradorDesblock = DAO.getAdministradorDAO().buscarAdministradorPorId(cpfAdministrador);
        administradorDesblock.desbloquearConta();
        DAO.getAdministradorDAO().salvarAdministradorArquivo();

        if ( (DAO.getAdministradorDAO().buscarAdministradorPorId(cpfAdministrador)).isStatusAcessoUsuario() )
            telaAviso_controller.showTelaAviso("Acesso desbloqueado com sucesso!");
        else telaAviso_controller.showTelaAviso("Ops! Não foi possível realizar o desbloqueio.");

    }

    @FXML
    void criarDesbloquearBibliotecario(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String cpfBibliotecario = cpfDesbloquearBibliotecario.getText();
        Bibliotecario bibliotecarioDesblock = DAO.getBibliotecarioDAO().buscarPorId(cpfBibliotecario);
        administrador.desbloquerBibliotecario(bibliotecarioDesblock);
        DAO.getBibliotecarioDAO().salvarBibliotecarioArquivo();

        if (  (DAO.getBibliotecarioDAO().buscarPorId(cpfBibliotecario)).isStatusAcessoUsuario() )
            telaAviso_controller.showTelaAviso("Acesso desbloqueado com sucesso!");
        else telaAviso_controller.showTelaAviso("Ops! Não foi possível realizar o desbloqueio.");

    }
    @FXML
    void criarDesbloquearLeitor(ActionEvent event) throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        String cpfLeitor = cpfDesbloquearLeitor.getText();
        Leitor leitorDesblock = DAO.getLeitorDAO().buscarPorId(cpfLeitor);
        administrador.desbloquearLeitor(leitorDesblock);
        DAO.getLeitorDAO().salvarLeitoresArquivo();

        if( (DAO.getLeitorDAO().buscarPorId(cpfLeitor)).isStatusAcessoUsuario() )
            telaAviso_controller.showTelaAviso("Acesso desbloqueado com sucesso!");
        else telaAviso_controller.showTelaAviso("Ops! Não foi possível realizar o desbloqueio.");

    }
    @FXML
    void gerenciarAcervo(ActionEvent event) {
        paneGerenciarAcervo.toFront();
    }
    @FXML
    void criarAdicionarLivro(ActionEvent event) {
        paneRegistrarLivro.toFront();
    }
    @FXML
    void criarLivro(ActionEvent event) throws IOException {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();
        try {
            Livro livro = new Livro(titulo.getText(), autor.getText(), isbn.getText(), categoria.getText(), anoPublicacao.getText(),
                    editora.getText(), new Localizacao(localizacaoPrateleira.getText(), localizacaoPosicao.getText()));

            DAO.getLivroDAO().criarLivro(livro);
            telaAviso_controller.showTelaAviso("Livro " + titulo.getText()+ " registrado com sucesso!");
            titulo.clear();
            autor.clear();
            isbn.clear();
            categoria.clear();
            anoPublicacao.clear();
            editora.clear();
            localizacaoPrateleira.clear();
            localizacaoPosicao.clear();
            panePrincipal.toFront();
        } catch (Exception e){
            telaAviso_controller.showTelaAviso(e.getMessage());
        }
    }
    @FXML
    void criarRemoverLivro(ActionEvent event) {
    }


    @FXML
    void criarPesquisarHistLeitor(ActionEvent event) {

    }
    @FXML
    void gerarRelatorio(ActionEvent event) {

    }


    @FXML
    void iniciarPesquisa(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
        Parent root = loader.load();
        TelaInicial_Controller telaInicialController = loader.getController();
        telaInicialController.setBarra_pesquisa_ini(barraPesquisa.getText());
        telaInicialController.setSairDaPesquisaPara("/com/sistemaBiblioteca/TelaAdministrador.fxml");
        telaInicialController.iniciarPesquisa(event);
    }
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

    public void setAdm(String cpf) throws Exception {
        this.NomeAdministrador.setText(DAO.getAdministradorDAO().buscarAdministradorPorId(cpf).getNome());
        this.administrador = DAO.getAdministradorDAO().buscarAdministradorPorId(cpf);
    }

    @FXML
    TextField tituloField;
    @FXML
    TextField autorField;
    @FXML
    TextField isbnField;
    @FXML
    TextField categoriaField;
    @FXML
    TextField anoPublicacaoField;
    @FXML
    TextField editoraField;
    @FXML
    TextField localizacaoPrateleiraField;
    @FXML
    TextField localizacaoPosicaoField;
    @FXML
    ListView<String> listaLivros;
    @FXML
    private Button buscar;
    @FXML
    private TextField buscarLivro;
    @FXML
    private Pane paneBuscarEditarLivro;
    @FXML
    private Pane paneEditarLivro;

    private Livro livroSelecionado;


    @FXML
    void initialize() {
        assert NomeAdministrador != null : "fx:id=\"NomeAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert anoPublicacao != null : "fx:id=\"anoPublicacao\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert anoPublicacaoField != null : "fx:id=\"anoPublicacaoField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert autorField != null : "fx:id=\"autorField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoAdicionarLivro != null : "fx:id=\"botaoAdicionarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearAcesso != null : "fx:id=\"botaoBloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearAdministrador != null : "fx:id=\"botaoBloquearAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearAdministrador11 != null : "fx:id=\"botaoBloquearAdministrador11\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearBibliotecario != null : "fx:id=\"botaoBloquearBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearLeitor != null : "fx:id=\"botaoBloquearLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarAdministrador != null : "fx:id=\"botaoCadastrarAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarBibliotecario != null : "fx:id=\"botaoCadastrarBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarLeitor != null : "fx:id=\"botaoCadastrarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarAdministrador != null : "fx:id=\"botaoCriarAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarBibliotecario != null : "fx:id=\"botaoCriarBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarLeitor != null : "fx:id=\"botaoCriarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarLivro != null : "fx:id=\"botaoCriarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        //assert botaoCriarLivro1 != null : "fx:id=\"botaoCriarLivro1\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearAcesso != null : "fx:id=\"botaoDesbloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearAdministrador != null : "fx:id=\"botaoDesbloquearAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearBibliotecario != null : "fx:id=\"botaoDesbloquearBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearLeitor != null : "fx:id=\"botaoDesbloquearLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoGerarRelatorio != null : "fx:id=\"botaoGerarRelatorio\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoGerenciarAcervo != null : "fx:id=\"botaoGerenciarAcervo\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoPesquisarHistLeitor != null : "fx:id=\"botaoPesquisarHistLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoRemoverLivro != null : "fx:id=\"botaoRemoverLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert buscar != null : "fx:id=\"buscar\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert buscarLivro != null : "fx:id=\"buscarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cargoAdministrador != null : "fx:id=\"cargoAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cargoBibliotecario != null : "fx:id=\"cargoBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert categoriaField != null : "fx:id=\"categoriaField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cidade != null : "fx:id=\"cidade\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpf != null : "fx:id=\"cpf\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfAdministrador != null : "fx:id=\"cpfAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfBibliotecario != null : "fx:id=\"cpfBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfBloquearAdministrador != null : "fx:id=\"cpfBloquearAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfBloquearBibliotecario != null : "fx:id=\"cpfBloquearBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfBloquearLeitor != null : "fx:id=\"cpfBloquearLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfDesbloquearAdministrador != null : "fx:id=\"cpfDesbloquearAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfDesbloquearBibliotecario != null : "fx:id=\"cpfDesbloquearBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cpfDesbloquearLeitor != null : "fx:id=\"cpfDesbloquearLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert editora != null : "fx:id=\"editora\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert editoraField != null : "fx:id=\"editoraField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert estado != null : "fx:id=\"estado\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert isbnField != null : "fx:id=\"isbnField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert listaLivros != null : "fx:id=\"listaLivros\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPosicao != null : "fx:id=\"localizacaoPosicao\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPosicaoField != null : "fx:id=\"localizacaoPosicaoField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPrateleira != null : "fx:id=\"localizacaoPrateleira\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPrateleiraField != null : "fx:id=\"localizacaoPrateleiraField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert nomeAdministrador != null : "fx:id=\"nomeAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert nomeBibliotecario != null : "fx:id=\"nomeBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert numero != null : "fx:id=\"numero\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneBloquearAcesso != null : "fx:id=\"paneBloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneBuscarEditarLivro != null : "fx:id=\"paneBuscarEditarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneCadastrarAdministrador != null : "fx:id=\"paneCadastrarAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneCadastrarBibliotecario != null : "fx:id=\"paneCadastrarBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneCadastrarLeitor != null : "fx:id=\"paneCadastrarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneDesbloquearAcesso != null : "fx:id=\"paneDesbloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneEditarLivro != null : "fx:id=\"paneEditarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneGerarRelatorio != null : "fx:id=\"paneGerarRelatorio\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneGerenciarAcervo != null : "fx:id=\"paneGerenciarAcervo\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert panePrincipal != null : "fx:id=\"panePrincipal\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneRegistrarLivro != null : "fx:id=\"paneRegistrarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert qtdLivrosAtrasados != null : "fx:id=\"qtdLivrosAtrasados\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert qtdLivrosEmprestados != null : "fx:id=\"qtdLivrosEmprestados\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert qtdLivrosReservados != null : "fx:id=\"qtdLivrosReservados\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert rua != null : "fx:id=\"rua\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert senhaAdministrador != null : "fx:id=\"senhaAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert senhaBibliotecario != null : "fx:id=\"senhaBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert telefone != null : "fx:id=\"telefone\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert tituloField != null : "fx:id=\"tituloField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert tituloLivroMaisPopular != null : "fx:id=\"tituloLivroMaisPopular\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";

        listaLivros.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                List<Livro> livros;
                try {
                    livros = DAO.getLivroDAO().buscarLivroPorTitulo(buscarLivro.getText());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                livroSelecionado = livros.stream().filter(livro -> livro.getTitulo().equals(newSelection)).findFirst().orElse(null);
                if (livroSelecionado != null) {
                    carregarDadosLivro(livroSelecionado);
                }
            }
        });

    }

    public void carregarListaLivros() throws Exception {
        String chaveBusca = buscarLivro.getText();
        List<Livro> livros = DAO.getLivroDAO().buscarLivroPorTitulo(chaveBusca);
        List<String> titulos = livros.stream().map(Livro::getTitulo).collect(Collectors.toList());
        listaLivros.getItems().setAll(titulos);

    }
    @FXML
    void salvarAlteracoes() throws Exception {
        Livro livro = livroSelecionado;
        boolean removido = DAO.getLivroDAO().removerLivro(livro);
        if (removido) {
            DAO.getLivroDAO().atualizarTituloLivro(livro, tituloField.getText());
            DAO.getLivroDAO().atualizarAutorLivro(livro, autorField.getText());
            DAO.getLivroDAO().atualizarIsbnLivro(livro, isbnField.getText());
            DAO.getLivroDAO().atualizarCategoriaLivro(livro, categoriaField.getText());
            DAO.getLivroDAO().atualizarAnoPublicacaoLivro(livro, anoPublicacaoField.getText());
            DAO.getLivroDAO().atualizarEditoraLivro(livro, editoraField.getText());
            DAO.getLivroDAO().salvar(livroSelecionado);
            DAO.getLivroDAO().salvarLivroArquivo();
        }

    }

    public void carregarDadosLivro(Livro livro) {
        tituloField.setText(livro.getTitulo());
        autorField.setText(livro.getAutor());
        isbnField.setText(livro.getIsbn());
        categoriaField.setText(livro.getCategoria());
        anoPublicacaoField.setText(livro.getAnoPublicacao());
        editoraField.setText(livro.getEditora());
        localizacaoPrateleira.setText(livro.getLocalizacao().getPrateleira());
        localizacaoPosicao.setText(livro.getLocalizacao().getPosicao());
        paneEditarLivro.toFront();
    }

    @FXML
    void criarEditarLivro(ActionEvent event) {
        paneBuscarEditarLivro.toFront();
    }

}
