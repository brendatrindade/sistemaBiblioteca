package DAO;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAO implements CRUD<Bibliotecario> {
    private static List<Bibliotecario> operadores = new ArrayList<>();
    private static List<Administrador> administradores = new ArrayList<>();
    private static List<Bibliotecario> bibliotecarios = new ArrayList<>();
    private static List<Bibliotecario> bibliotecariosCadastrados = new ArrayList<>();

    public void setAdministrador(Administrador administrador) {
        administradores.add(administrador);
    }
    public void setBibliotecario(Bibliotecario bibliotecario){
        bibliotecarios.add(bibliotecario);
    }


    public List<Administrador> getAdministradores(){
        for (Bibliotecario operador : operadores) {
            if (operador instanceof Administrador) {
                Administrador administrador = (Administrador) operador;
                administradores.add(administrador);
            }
        }
        if(administradores.isEmpty()){
            System.out.println("O sistema não possui Administradores cadastrados.");
            return null;}
        return administradores;
    }
    public List<Bibliotecario> getBibliotecarios(){
        for (Bibliotecario bibliotecario : operadores) {
            if (!(bibliotecario instanceof Administrador)){
                bibliotecarios.add(bibliotecario);
            }
        }
        if(bibliotecarios.isEmpty()){
            System.out.println("O sistema não possui Bibliotecarios cadastrados.");
            return null;}
        return bibliotecarios;
    }

    public List<Bibliotecario> getBibliotecariosCadastrados() {
        return bibliotecariosCadastrados;
    }
    public void registrarBibliotecario(Bibliotecario bibliotecario){
        bibliotecariosCadastrados.add(bibliotecario);
    }
    public void removerBibliotecario(Bibliotecario bibliotecario){
        bibliotecariosCadastrados.remove(bibliotecario);
    }

    /**
     * Adiciona novo Objeto
     *
     * @param bibliotecario
     */
    @Override
    public void adiciona(Bibliotecario bibliotecario) {
        operadores.add(bibliotecario);
    }
    /**
     * Lê todos os Objetos
     */
    @Override
    public List<Bibliotecario> get() {
        return operadores;
    }

    /**
     * Atualiza um Objeto - Nome
     *
     * @param bibliotecario
     * @param novoDado
     */
    @Override
    public void altera(Bibliotecario bibliotecario, String novoDado) {
        bibliotecario.setNome(novoDado);
    }

    /**
     * Deleta um Objeto
     *
     * @param bibliotecario
     */
    @Override
    public void remove(Bibliotecario bibliotecario) {
        operadores.remove(bibliotecario);
    }

    /**
     * Deleta todos os dados
     */
    @Override
    public void removeTodos() {
        operadores = new ArrayList<>();
    }











}
