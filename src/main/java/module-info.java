
module GerenciadorBiblioteca{

    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    exports com.sistemaBiblioteca.Model.Operacoes;
    exports com.sistemaBiblioteca;
    exports com.sistemaBiblioteca.Controle;
    exports com.sistemaBiblioteca.DAO;
    exports com.sistemaBiblioteca.Controller;

    opens com.sistemaBiblioteca.Model.Operacoes to javafx.fxml, java.base;
    opens com.sistemaBiblioteca to javafx.fxml;
    opens com.sistemaBiblioteca.Controle to javafx.fxml;
    opens com.sistemaBiblioteca.DAO to javafx.fxml;
    opens com.sistemaBiblioteca.Controller to javafx.fxml;

}
