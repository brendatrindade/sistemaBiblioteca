package com.sistemaBiblioteca.DAO;

/**
 * Classe utilizada para realizar o intermedio com a base de dados.
 */

public class DAO {

    private static AdministradorDAO administradorDAO;

    private static BibliotecarioDAO bibliotecarioDAO;

    private static LeitorDAO leitorDAO;

    private static LivroDAO livroDAO;


    public static AdministradorDAO getAdministradorDAO() throws Exception {
        if(administradorDAO == null){
            administradorDAO = new AdministradorDAO();
        }
        return administradorDAO;
    }

    public static BibliotecarioDAO getBibliotecarioDAO() throws Exception {
        if(bibliotecarioDAO == null){
            bibliotecarioDAO = new BibliotecarioDAO();
        }
        return bibliotecarioDAO;
    }

    public static LivroDAO getLivroDAO() throws Exception {
        if(livroDAO == null){
            livroDAO = new LivroDAO();
        }
        return livroDAO;
    }

    public static LeitorDAO getLeitorDAO() throws Exception {
        if(leitorDAO == null){
            leitorDAO = new LeitorDAO();
        }
        return leitorDAO;
    }

}


