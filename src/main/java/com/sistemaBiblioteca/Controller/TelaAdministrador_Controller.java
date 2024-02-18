package com.sistemaBiblioteca.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import com.sistemaBiblioteca.DAO.DAO;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Administrador;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import com.sistemaBiblioteca.Model.Usuarios.Endereco;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
    private TextField anoPublicacaoField;

    @FXML
    private TextField anoPublicacaoRemover;

    @FXML
    private TextField autor;

    @FXML
    private TextField autorField;

    @FXML
    private TextField autorRemover;

    @FXML
    private TextField barraPesquisa;

    @FXML
    private Button botaoAdicionarLivro;

    @FXML
    private Button botaoBloquearAcesso;

    @FXML
    private Button botaoBloquearAdministrador;

    @FXML
    private Button botaoBloquearBibliotecario;

    @FXML
    private Button botaoBloquearLeitor;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Button botaoBuscarRemover;

    @FXML
    private Button botaoCadastrarAdministrador;

    @FXML
    private Button botaoCadastrarBibliotecario;

    @FXML
    private Button botaoCadastrarLeitor;

    @FXML
    private Button botaoConfirmarRemover;

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
    private Button botaoEditarLivro;

    @FXML
    private Button botaoGerarRelatorio;

    @FXML
    private Button botaoGerenciarAcervo;

    @FXML
    private Button botaoPesquisar;

    @FXML
    private Button botaoPesquisarHistLeitor;

    @FXML
    private Button botaoPesquisarHistorico;

    @FXML
    private Button botaoRemoverLivro;

    @FXML
    private Button botaoSair;

    @FXML
    private Button botaoSalvarAlteracaoLivro;

    @FXML
    private TextField buscarLivro;

    @FXML
    private TextField buscarLivroRemover;

    @FXML
    private TextField cargoAdministrador;

    @FXML
    private TextField cargoBibliotecario;

    @FXML
    private TextField categoria;

    @FXML
    private TextField categoriaField;

    @FXML
    private TextField categoriaRemover;

    @FXML
    private TextField cidade;

    @FXML
    private TableColumn<Emprestimo, String> colunaTitulo;
    @FXML
    private TableColumn<Emprestimo, LocalDate> colunaDataPrevista;
    @FXML
    private TableColumn<Emprestimo, LocalDate> colunaDataRealizada;
    @FXML
    private TableColumn<Emprestimo, LocalDate> colunaDataRealizadaDev;
    @FXML
    private TableColumn<Emprestimo, Integer> colunaNumeroDeRenovacoes;

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
    private TextField cpfHistoricoEmprestimo;

    @FXML
    private TextField editora;

    @FXML
    private TextField editoraField;

    @FXML
    private TextField editoraRemover;

    @FXML
    private TextField estado;

    @FXML
    private TextField isbn;

    @FXML
    private TextField isbnField;

    @FXML
    private TextField isbnRemover;

    @FXML
    private TextField localizacaoPosicao;

    @FXML
    private TextField localizacaoPosicaoField;

    @FXML
    private TextField localizacaoPosicaoRemover;

    @FXML
    private TextField localizacaoPrateleira;

    @FXML
    private TextField localizacaoPrateleiraField;

    @FXML
    private TextField localizacaoPrateleiraRemover;

    @FXML
    private TextField nome;

    @FXML
    private TextField nomeAdministrador;

    @FXML
    private TextField nomeBibliotecario;

    @FXML
    private Label nomeLeitorHistorico;

    @FXML
    private TextField numero;

    @FXML
    private Pane paneBloquearAcesso;

    @FXML
    private Pane paneBuscarEditarLivro;

    @FXML
    private Pane paneBuscarRemoverLivro;

    @FXML
    private Pane paneCadastrarAdministrador;

    @FXML
    private Pane paneCadastrarBibliotecario;

    @FXML
    private Pane paneCadastrarLeitor;

    @FXML
    private Pane paneDesbloquearAcesso;

    @FXML
    private Pane paneEditarLivro;

    @FXML
    private Pane paneGerarRelatorio;

    @FXML
    private Pane paneGerenciarAcervo;

    @FXML
    private Pane paneInfosRemoverLivro;

    @FXML
    private Pane panePesquisarHistorico;

    @FXML
    private Pane panePrincipal;

    @FXML
    private Pane paneRegistrarLivro;

    @FXML
    private AnchorPane paneTabelaHistorico;

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
    private TableView<Emprestimo> tabelaHistórico;

    @FXML
    private TextField telefone;

    @FXML
    private TextField titulo;

    @FXML
    private TextField tituloField;

    @FXML
    private Label tituloLivroMaisPopular;

    @FXML
    private TextField tituloRemover;

    private Livro livroSelecionado;

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
            if ( !(DAO.getAdministradorDAO().cpfOperadorEstaCadastrado(cpfBibliotecario.getText())) ) {
            Bibliotecario bibliotecario = new Bibliotecario( nomeBibliotecario.getText(), cpfBibliotecario.getText(), senhaBibliotecario.getText() );
            DAO.getBibliotecarioDAO().criarBibliotecario(bibliotecario);
            telaAviso_controller.showTelaAviso("Bibliotecario " + nomeBibliotecario.getText()+ " cadastrado com sucesso!");
            nomeBibliotecario.clear();
            cpfBibliotecario.clear();
            senhaBibliotecario.clear();
            panePrincipal.toFront();
            } else telaAviso_controller.showTelaAviso("CPF já possui cadastro como operador do sistema.");
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
    void criarEditarLivro() {
        paneBuscarEditarLivro.toFront();
    }

    @FXML
    void iniciarBusca(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
        Parent root = loader.load();
        TelaInicial_Controller telaInicialController = loader.getController();
        telaInicialController.setBarra_pesquisa_ini(buscarLivro.getText());
        telaInicialController.setSairDaPesquisaPara("/com/sistemaBiblioteca/TelaAdministrador.fxml");
        telaInicialController.setAdmEditandoLivro(true);
        telaInicialController.iniciarPesquisa(event);

    }
    void carregarDadosLivro(Livro livro) {

        tituloField.setText(livro.getTitulo());
        autorField.setText(livro.getAutor());
        isbnField.setText(livro.getIsbn());
        categoriaField.setText(livro.getCategoria());
        anoPublicacaoField.setText(livro.getAnoPublicacao());
        editoraField.setText(livro.getEditora());
        localizacaoPrateleiraField.setText(livro.getLocalizacao().getPrateleira());
        localizacaoPosicaoField.setText(livro.getLocalizacao().getPosicao());

        paneEditarLivro.toFront();

    }

    public void setLivroSelecionado( Livro livroSelecionado){
        this.livroSelecionado = livroSelecionado;
    }

    @FXML
    void salvarAlteracoes() throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        boolean removido = DAO.getLivroDAO().removerLivro(this.livroSelecionado);
        if (removido) {
            DAO.getLivroDAO().atualizarTituloLivro(this.livroSelecionado, tituloField.getText());
            DAO.getLivroDAO().atualizarAutorLivro(this.livroSelecionado, autorField.getText());
            DAO.getLivroDAO().atualizarIsbnLivro(this.livroSelecionado, isbnField.getText());
            DAO.getLivroDAO().atualizarCategoriaLivro(this.livroSelecionado, categoriaField.getText());
            DAO.getLivroDAO().atualizarAnoPublicacaoLivro(this.livroSelecionado, anoPublicacaoField.getText());
            DAO.getLivroDAO().atualizarEditoraLivro(this.livroSelecionado, editoraField.getText());
            DAO.getLivroDAO().atualizarLocalizacao(this.livroSelecionado, localizacaoPrateleiraField.getText(), localizacaoPosicaoField.getText());
            DAO.getLivroDAO().salvar(this.livroSelecionado);
            DAO.getLivroDAO().salvarLivroArquivo();

            telaAviso_controller.showTelaAviso("Livro: " + this.livroSelecionado.getTitulo()+ " editado com sucesso!");

            tituloField.clear();
            autorField.clear();
            isbnField.clear();
            categoriaField.clear();
            anoPublicacaoField.clear();
            editoraField.clear();
            localizacaoPrateleiraField.clear();
            localizacaoPosicaoField.clear();
            panePrincipal.toFront();

        } else telaAviso_controller.showTelaAviso("Ops! Edição não concluida.");

    }

    @FXML
    void criarRemoverLivro(ActionEvent event) {
        paneBuscarRemoverLivro.toFront();
    }
    @FXML
    void iniciarBuscaRemover(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/sistemaBiblioteca/TelaInicial.fxml"));
        Parent root = loader.load();
        TelaInicial_Controller telaInicialController = loader.getController();
        telaInicialController.setBarra_pesquisa_ini(buscarLivroRemover.getText());
        telaInicialController.setSairDaPesquisaPara("/com/sistemaBiblioteca/TelaAdministrador.fxml");
        telaInicialController.setAdmRemovendoLivro(true);
        telaInicialController.iniciarPesquisa(event);

    }
    void carregarDadosLivroRemover(Livro livro) {

        tituloRemover.setText(livro.getTitulo());
        autorRemover.setText(livro.getAutor());
        isbnRemover.setText(livro.getIsbn());
        categoriaRemover.setText(livro.getCategoria());
        anoPublicacaoRemover.setText(livro.getAnoPublicacao());
        editoraRemover.setText(livro.getEditora());
        localizacaoPrateleiraRemover.setText(livro.getLocalizacao().getPrateleira());
        localizacaoPosicaoRemover.setText(livro.getLocalizacao().getPosicao());

        paneInfosRemoverLivro.toFront();
    }

    @FXML
    void confirmarRemover() throws Exception {
        TelaAviso_Controller telaAviso_controller = new TelaAviso_Controller();

        boolean removido = DAO.getLivroDAO().removerLivro(this.livroSelecionado);
        if (removido) {

            telaAviso_controller.showTelaAviso("Livro: " + this.livroSelecionado.getTitulo()+ " removido com sucesso!");

            tituloRemover.clear();
            autorRemover.clear();
            isbnRemover.clear();
            categoriaRemover.clear();
            anoPublicacaoRemover.clear();
            editoraRemover.clear();
            localizacaoPrateleiraRemover.clear();
            localizacaoPosicaoRemover.clear();

            panePrincipal.toFront();

        } else telaAviso_controller.showTelaAviso("Ops! Remoção não concluida.");

    }

    @FXML
    void criarPesquisarHistLeitor(ActionEvent event) {
        panePesquisarHistorico.toFront();
    }

    @FXML
    void criarPesquisarHistoricoEmprestimo(ActionEvent event) throws Exception {
        String cpf = cpfHistoricoEmprestimo.getText();

        if ( !cpf.trim().isEmpty() ) {

            Leitor leitorHistorico = DAO.getLeitorDAO().buscarPorId(cpf);
            List<Leitor> leitores = DAO.getLeitorDAO().lerLeitoresArquivo();
            List<Emprestimo> historicoDoLeitor = new ArrayList<>();

            for (Leitor leitorArquivo : leitores) {
                if (DAO.getLeitorDAO().leitoresIguais(leitorHistorico, leitorArquivo)) {
                    historicoDoLeitor = DAO.getLeitorDAO().getHistoricoEmprestimosArq(leitorArquivo);
                }
            }
            if (historicoDoLeitor != null) {
                preencherTabela(historicoDoLeitor);
                nomeLeitorHistorico.setText(leitorHistorico.getNome());
                paneTabelaHistorico.toFront();
            }
        }

    }

    public void preencherTabela(List<Emprestimo> historicoDoLeitor) {
        colunaTitulo.setCellValueFactory(cellData -> {
            Emprestimo emprestimo = cellData.getValue();
            Livro livro = emprestimo.getLivro();
            String titulo = livro.getTitulo();
            return new SimpleStringProperty(titulo);
        });

        colunaDataPrevista.setCellValueFactory(new PropertyValueFactory<>("dataEsperadaDev"));
        colunaDataRealizada.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        colunaDataRealizadaDev.setCellValueFactory(new PropertyValueFactory<>("dataRealizadaDev"));
        colunaNumeroDeRenovacoes.setCellValueFactory(new PropertyValueFactory<>("numeroDeRenovacoes"));

        ObservableList<Emprestimo> historico = FXCollections.observableArrayList(historicoDoLeitor);
        tabelaHistórico.setItems(historico);
    }


    @FXML
    void gerarRelatorio(ActionEvent event) throws Exception {
        paneGerarRelatorio.toFront();

        int totalEmprestimosAtivos = DAO.getLeitorDAO().totalEmprestimosAtivosArquivo();
        int totalEmprestimosAtrasados = DAO.getLeitorDAO().totalEmprestimosAtrasadosArquivo();
        int totalLivrosReservados = DAO.getLivroDAO().totalLivrosReservadosArquivo();
        String titulos = String.join("\n ", DAO.getLeitorDAO().tituloMaisEmprestado());

        qtdLivrosEmprestados.setText(Integer.toString(totalEmprestimosAtivos));
        qtdLivrosAtrasados.setText(Integer.toString(totalEmprestimosAtrasados));
        qtdLivrosReservados.setText(Integer.toString(totalLivrosReservados));

        tituloLivroMaisPopular.setText(titulos);

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
    void initialize() {
        assert NomeAdministrador != null : "fx:id=\"NomeAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert anoPublicacao != null : "fx:id=\"anoPublicacao\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert anoPublicacaoField != null : "fx:id=\"anoPublicacaoField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert anoPublicacaoRemover != null : "fx:id=\"anoPublicacaoRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert autor != null : "fx:id=\"autor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert autorField != null : "fx:id=\"autorField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert autorRemover != null : "fx:id=\"autorRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert barraPesquisa != null : "fx:id=\"barraPesquisa\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoAdicionarLivro != null : "fx:id=\"botaoAdicionarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearAcesso != null : "fx:id=\"botaoBloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearAdministrador != null : "fx:id=\"botaoBloquearAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearBibliotecario != null : "fx:id=\"botaoBloquearBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBloquearLeitor != null : "fx:id=\"botaoBloquearLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBuscar != null : "fx:id=\"botaoBuscar\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoBuscarRemover != null : "fx:id=\"botaoBuscarRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarAdministrador != null : "fx:id=\"botaoCadastrarAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarBibliotecario != null : "fx:id=\"botaoCadastrarBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCadastrarLeitor != null : "fx:id=\"botaoCadastrarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoConfirmarRemover != null : "fx:id=\"botaoConfirmarRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarAdministrador != null : "fx:id=\"botaoCriarAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarBibliotecario != null : "fx:id=\"botaoCriarBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarLeitor != null : "fx:id=\"botaoCriarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoCriarLivro != null : "fx:id=\"botaoCriarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearAcesso != null : "fx:id=\"botaoDesbloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearAdministrador != null : "fx:id=\"botaoDesbloquearAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearBibliotecario != null : "fx:id=\"botaoDesbloquearBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoDesbloquearLeitor != null : "fx:id=\"botaoDesbloquearLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoEditarLivro != null : "fx:id=\"botaoEditarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoGerarRelatorio != null : "fx:id=\"botaoGerarRelatorio\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoGerenciarAcervo != null : "fx:id=\"botaoGerenciarAcervo\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoPesquisar != null : "fx:id=\"botaoPesquisar\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoPesquisarHistLeitor != null : "fx:id=\"botaoPesquisarHistLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoRemoverLivro != null : "fx:id=\"botaoRemoverLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoSair != null : "fx:id=\"botaoSair\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert botaoSalvarAlteracaoLivro != null : "fx:id=\"botaoSalvarAlteracaoLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert buscarLivro != null : "fx:id=\"buscarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert buscarLivroRemover != null : "fx:id=\"buscarLivroRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cargoAdministrador != null : "fx:id=\"cargoAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert cargoBibliotecario != null : "fx:id=\"cargoBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert categoria != null : "fx:id=\"categoria\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert categoriaField != null : "fx:id=\"categoriaField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert categoriaRemover != null : "fx:id=\"categoriaRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
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
        assert editoraRemover != null : "fx:id=\"editoraRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert estado != null : "fx:id=\"estado\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert isbn != null : "fx:id=\"isbn\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert isbnField != null : "fx:id=\"isbnField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert isbnRemover != null : "fx:id=\"isbnRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPosicao != null : "fx:id=\"localizacaoPosicao\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPosicaoField != null : "fx:id=\"localizacaoPosicaoField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPosicaoRemover != null : "fx:id=\"localizacaoPosicaoRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPrateleira != null : "fx:id=\"localizacaoPrateleira\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPrateleiraField != null : "fx:id=\"localizacaoPrateleiraField\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert localizacaoPrateleiraRemover != null : "fx:id=\"localizacaoPrateleiraRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert nomeAdministrador != null : "fx:id=\"nomeAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert nomeBibliotecario != null : "fx:id=\"nomeBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert numero != null : "fx:id=\"numero\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneBloquearAcesso != null : "fx:id=\"paneBloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneBuscarEditarLivro != null : "fx:id=\"paneBuscarEditarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneBuscarRemoverLivro != null : "fx:id=\"paneBuscarRemoverLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneCadastrarAdministrador != null : "fx:id=\"paneCadastrarAdministrador\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneCadastrarBibliotecario != null : "fx:id=\"paneCadastrarBibliotecario\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneCadastrarLeitor != null : "fx:id=\"paneCadastrarLeitor\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneDesbloquearAcesso != null : "fx:id=\"paneDesbloquearAcesso\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneEditarLivro != null : "fx:id=\"paneEditarLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneGerarRelatorio != null : "fx:id=\"paneGerarRelatorio\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneGerenciarAcervo != null : "fx:id=\"paneGerenciarAcervo\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
        assert paneInfosRemoverLivro != null : "fx:id=\"paneInfosRemoverLivro\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";
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
        assert tituloRemover != null : "fx:id=\"tituloRemover\" was not injected: check your FXML file 'TelaAdministrador.fxml'.";

    }

}
