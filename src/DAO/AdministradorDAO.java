package DAO;

import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.List;

public class AdministradorDAO implements CRUD<Administrador> {
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
     * @param administrador
     */
    @Override
    public void adiciona(Administrador administrador) {
        bibliotecarioDAO.setAdministrador(administrador);
    }

    /**
     * Lê todos os Objetos
     */
    @Override
    public List<Administrador> get() {
        return bibliotecarioDAO.getAdministradores();
    }

    /**
     * Atualiza um Objeto - nome
     *
     * @param administrador
     * @param novoDado
     */
    @Override
    public void altera(Administrador administrador, String novoDado) {
        administrador.setNome(novoDado);
    }
    /**
     * Deleta um Objeto
     *
     * @param administrador
     */
    @Override
    public void remove(Administrador administrador) {
        bibliotecarioDAO.removeAdministrador(administrador);
    }

    /**
     * Deleta todos os dados
     */
    @Override
    public void removeTodos() {

    }

    public void removeBibliotecario(Bibliotecario bibliotecario){
        bibliotecarioDAO.removerBibliotecario(bibliotecario);
    }


}
