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
import com.sistemaBiblioteca.Model.Operacoes.Livro;
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
    /**
     * Criar e registrar um novo administrador no sistema.
     * @param administrador Objeto contendo os atributos necessários.
     * @return Objeto administrador registrado.
     */
    public Administrador criarAdministrador(Administrador administrador) throws Exception {
        if (!administrador.validaCPF(administrador.getCpf())) {
            throw new Exception("CPF inválido!");
        }
        if (cpfOperadorEstaCadastrado(administrador.getCpf())) {
            throw new Exception("CPF já possui cadastro como operador do sistema.");
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
        Persistencia.salvarAdministrador(this.administradores);
    }
    /**
     * Deleta um administrador do arquivo.
     * @param administrador - administrador a ser deletado.
     * @throws Exception se ocorrer um erro no processo de deletar do arquivo.
     */
    public void deletarAdministradorArquivo(Administrador administrador) throws Exception {
        if (this.administradores.contains(administrador)){
            if( this.administradores.remove(administrador) ){
                salvarAdministradorArquivo();
            } else {
                throw new Exception("Erro ao deletar administrador.");
            }
        } else {
            throw new Exception("Administrador não encontrado no arquivo.");
        }
    }
    /**
     * Deleta todos os administradores do arquivo
     */
    public void deletarTodosAdministradoresArquivo() throws Exception {
        this.administradores = new ArrayList<>();
        salvarAdministradorArquivo();
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
        this.administradores.add(administrador);
    }
    /**
     * Deleta um administrador da lista de administradores.
     * @param administrador - administrador a ser deletado.
     */
    public void deletarAdministrador(Administrador administrador) {
        this.administradores.remove(administrador);
    }
    /**
     * Deleta todos os administradores da lista de administradores.
     */
    public void deletarTodosAdministradores() {
        this.administradores = new ArrayList<>();
    }
    /**
     * Busca um administrador pelo id - CPF.
     * @param id - CPF do administrador a ser buscado.
     * @return Administrador encontrado ou null se o cpf não possuir cadastro.
     */
    public Administrador buscarAdministradorPorId(String id) {
        if(!this.administradores.isEmpty()){
            for(Administrador administrador : this.administradores){
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
        for (Bibliotecario operador : this.operadores) {
            if (operador instanceof Administrador) {
                Administrador administrador = (Administrador) operador;
                this.administradores.add(administrador);
            }
        }
        return this.administradores;
    }

    public boolean cpfAdministradorEstaCadastrado(String cpf){
        if ( !this.administradores.isEmpty() ) {
            for (Administrador a : this.administradores) {
                if ( a.getCpf().equals(cpf) )
                    return true;
            }
        } return false;
    }

    public String getSenhaAdiministrador(String cpf) {
        Administrador a = buscarAdministradorPorId(cpf);
        return a.getSenha();
    }
    /**
     * Verifica se o CPF de um operador já está cadastrado.
     * @param cpf - CPF a ser verificado.
     * @return true se o CPF já estiver cadastrado, false caso contrário.
     */
    public boolean cpfOperadorEstaCadastrado(String cpf){
        if (cpfBibliotecarioEstaCadastrado(cpf)){
            return true;
        } else if (cpfAdministradorEstaCadastrado(cpf)){
            return true;
        }
        return false;
    }
    public boolean administradoresIguais(Administrador adm1, Administrador adm2){
        if (adm1.getCpf().equals(adm2.getCpf())) return true;
        else return false;
    }
    public boolean bloquearAdministrador(Administrador admParaBloquear) throws Exception {
        List<Administrador> administradoresArquivo = lerAdministradorArquivo();
        boolean admEncontrado = false;
        int i = 0;
        while ( (!admEncontrado) && (i < administradoresArquivo.size()) ){
            Administrador administrador = administradoresArquivo.get(i);
            if (administradoresIguais(administrador, admParaBloquear)){
                administrador.bloquearConta();
                this.administradores = administradoresArquivo;
                salvarBibliotecarioArquivo();
                admEncontrado = true;
            }
            i++;
        }
        return admEncontrado;
    }
    public boolean desbloquearAdministrador(Administrador admParaDesbloquear) throws Exception {
        List<Administrador> administradoresArquivo = lerAdministradorArquivo();
        boolean admEncontrado = false;
        int i = 0;
        while ( (!admEncontrado) && (i < administradoresArquivo.size()) ){
            Administrador administrador = administradoresArquivo.get(i);
            if (administradoresIguais(administrador, admParaDesbloquear)){
                administrador.desbloquearConta();
                this.administradores = administradoresArquivo;
                salvarAdministradorArquivo();
                admEncontrado = true;
            }
            i++;
        }
        return admEncontrado;
    }

}
