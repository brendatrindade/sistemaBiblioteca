/**
 * AdministradorDAO
 *
 * @author Brenda Araújo Trindade Oliveira
 * @version 1.0
 * @since 02/10/2023
 *
 * Direitos autorais (c) 2023 Brenda Araújo Trindade Oliveira. Todos os direitos reservados.
 * Este software é confidencial e proprietário de Brenda Araújo Trindade Oliveira.
 * Este software é protegido sob direitos autorais.
 *
 */
package com.sistemaBiblioteca.DAO;

import com.sistemaBiblioteca.Excecoes.Excecao;
import com.sistemaBiblioteca.Model.Usuarios.Administrador;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * SubClasse AdministradorDAO: Estende BibliotecarioDAO
 * Especialização do objeto operador enquanto Administrador.
 * Contém métodos para gerenciar administradores da biblioteca.
 */
public class AdministradorDAO extends BibliotecarioDAO{
    private List<Bibliotecario> operadores;
    private List<Administrador> administradores;

    public AdministradorDAO() throws Exception {
        this.operadores = getOperadores();
        this.administradores = new ArrayList<>();
        this.administradores = Persistencia.lerAdministrador();
    }

    public Administrador criarAdministrador(Administrador administrador) throws Exception {
        if (!administrador.validaCPF(administrador.getCpf())) {
            throw new Exception("CPF inválido!");
        }
        if (cpfAdministradorEstaCadastrado(administrador.getCpf())) {
            throw new Exception("CPF já está cadastrado!");
        }
        salvarAdiministrador(administrador);
        salvarAdministradorArquivo();
        return administrador;
    }

    /**
     * Salva a lista de administradores em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarAdministradorArquivo() throws Exception {
        Persistencia.salvarAdministrador(administradores);
    }
    /**
     * Lê a lista de administradores de um arquivo.
     * @return - lista de administradores lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public List<Administrador> lerAdministradorArquivo() throws Exception {
        List<Administrador> administradoresArquivo = Persistencia.lerAdministrador();
        return administradoresArquivo;
    }
    /**
     * Salva um novo administrador na lista de administradores.
     * @param administrador - administrador a ser salvo.
     */
    public void salvarAdiministrador(Administrador administrador) {
        administradores.add(administrador);
    }
    /**
     * Deleta um administrador da lista de administradores.
     * @param administrador - administrador a ser deletado.
     */
    public void deletarAdministrador(Administrador administrador) {
        administradores.remove(administrador);
    }
    /**
     * Deleta todos os administradores da lista de administradores.
     */
    public void deletarTodosAdministradores() {
        administradores = new ArrayList<>();
    }
    /**
     * Busca um administrador pelo id - CPF.
     * @param id - CPF do administrador a ser buscado.
     * @return Administrador encontrado ou null se o cpf não possuir cadastro.
     */
    public Administrador buscarAdministradorPorId(String id) {
        if(!administradores.isEmpty()){
            for(Administrador administrador : administradores){
                if(administrador.getCpf().equals(id))
                    return administrador;
            }
        }
        return null;
    }
    /**
     * Retorna todos os administradores.
     * @return Lista contendo todos os administradores.
     */
    public List<Administrador> getAdministradores(){
        for (Bibliotecario operador : operadores) {
            if (operador instanceof Administrador) {
                Administrador administrador = (Administrador) operador;
                administradores.add(administrador);
            }
        }
        return administradores;
    }

    public boolean cpfAdministradorEstaCadastrado(String cpf){
        if (!administradores.isEmpty()){
            for (Administrador a : administradores) {
                if (a.getCpf().equals(cpf))
                    return true;
            }
        } return false;
    }
}
