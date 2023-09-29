package DAO;
import Excecoes.Excecao;
import Model.Usuarios.Administrador;
import Model.Usuarios.Bibliotecario;

import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAO implements DAOgenerico<Bibliotecario> {
    private static List<Bibliotecario> operadores = new ArrayList<>();
    private static List<Bibliotecario> bibliotecarios = new ArrayList<>();

    public static List<Bibliotecario> getOperadores(){
        return operadores;
    }

    @Override
    public void salvar(Bibliotecario c) {
        bibliotecarios.add(c);
    }
    @Override
    public void deletar(Bibliotecario c) {
        bibliotecarios.remove(c);
    }
    @Override
    public void deletarTodos() {
        bibliotecarios = new ArrayList<>();
    }
    @Override
    public Bibliotecario buscarPorId(String id) {
        if(bibliotecarios != null){
            for(Bibliotecario bibliotecario : bibliotecarios){
                if(bibliotecario.getCpf().equals(id))
                    return bibliotecario;
            }
        }
        return null;
    }
    /**
     * Retorna todos os Bibliotecarios
     */
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

    //Verificação para controle de cadastro
    public boolean cpfOperadorEstaCadastrado(String cpf) throws Excecao {
        if (bibliotecarios != null){
            for (Bibliotecario operador : bibliotecarios) {
                if (operador.getCpf().equals(cpf))
                    throw new Excecao(operador.getNome() + ", o CPF informado ja esta cadastrado como operador do sistema.");
            }
        } return false;
    }

}
