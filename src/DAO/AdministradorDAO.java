package DAO;

import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO extends BibliotecarioDAO{
    private static List<Bibliotecario> operadores = getOperadores();
    private static List<Administrador> administradores = new ArrayList<>();

    public void salvarAdiministrador(Administrador administrador) {
        administradores.add(administrador);
    }
    public void deletarAdministrador(Administrador administrador) {
        administradores.remove(administrador);
    }
    public void deletarTodosAdministradores() {
        administradores = new ArrayList<>();
    }
    public Administrador buscarAdministradorPorId(String id) {
        if(administradores != null){
            for(Administrador administrador : administradores){
                if(administrador.getCpf().equals(id))
                    return administrador;
            }
        }
        return null;
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

}
