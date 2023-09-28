package DAO;

import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.List;

public class AdministradorDAO implements CRUD<AdministradorDAO> {
    BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();

    public void setAdministradores(Administrador administrador) {
        bibliotecarioDAO.setAdministrador(administrador);
    }

    public List<Administrador> getAdministradores(){
        return bibliotecarioDAO.getAdministradores();
    }

    /**
     * Adiciona novo Objeto
     *
     * @param administradorDAO
     */
    @Override
    public void adiciona(AdministradorDAO administradorDAO) {

    }

    /**
     * Lê todos os Objetos
     */
    @Override
    public List<AdministradorDAO> get() {
        return null;
    }

    /**
     * Atualiza um Objeto
     *
     * @param administradorDAO
     * @param novoDado
     */
    @Override
    public void altera(AdministradorDAO administradorDAO, String novoDado) {

    }

    /**
     * Deleta um Objeto
     *
     * @param administradorDAO
     */
    @Override
    public void remove(AdministradorDAO administradorDAO) {

    }

    /**
     * Deleta todos os dados
     */
    @Override
    public void removeTodos() {

    }


    public void registraBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioDAO.registrarBibliotecario(bibliotecario);
    }

    public void removeBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioDAO.removerBibliotecario(bibliotecario);
    }


}
