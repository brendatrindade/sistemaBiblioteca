/**
 * BibliotecarioDAO
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Classe BibliotecarioDAO: implementa DAOgenerico para os Operadores da biblioteca.
 * Generalização dos operadores.
 * Contém métodos gerenciamento dos bibliotecarios e operadores.
 */
public class BibliotecarioDAO implements DAOgenerico<Bibliotecario> , Serializable {
    private List<Bibliotecario> operadores;
    private List<Bibliotecario> bibliotecarios;

    public BibliotecarioDAO() throws Exception {
        this.operadores = new ArrayList<>();
        this.bibliotecarios = new ArrayList<>();
        this.bibliotecarios = Persistencia.lerBibliotecario();
    }

    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) throws Exception {
        if (!bibliotecario.validaCPF(bibliotecario.getCpf())) {
            throw new Exception("CPF inválido!");
        }
        if (cpfBibliotecarioEstaCadastrado(bibliotecario.getCpf())) {
            throw new Exception("CPF já está cadastrado!");
        }
        salvar(bibliotecario);
        salvarBibliotecarioArquivo();
        return bibliotecario;
    }

    /**
     * Salva a lista de bibliotecários em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarBibliotecarioArquivo() throws Exception {
        Persistencia.salvarBibliotecario(bibliotecarios);
    }
    /**
     * Lê a lista de bibliotecários de um arquivo.
     * @return - lista de bibliotecários lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public List<Bibliotecario> lerLivrosArquivo() throws Exception {
        List<Bibliotecario> bibliotecariosArquivo = Persistencia.lerBibliotecario();
        return bibliotecariosArquivo;
    }
    /**
     * Retorna todos os operadores.
     * @return Lista contendo todos os operadores da biblioteca.
     */
    public List<Bibliotecario> getOperadores(){
        return operadores;
    }
    /**
     * Salva um novo bibliotecário na lista de bibliotecários.
     * @param c - bibliotecário a ser salvo.
     */
    @Override
    public void salvar(Bibliotecario c) {
        bibliotecarios.add(c);
    }
    /**
     * Deleta um bibliotecário da lista de bibliotecários.
     * @param c - bibliotecário a ser deletado.
     */
    @Override
    public void deletar(Bibliotecario c) {
        bibliotecarios.remove(c);
    }
    /**
     * Deleta todos os bibliotecários da lista de bibliotecários.
     */
    @Override
    public void deletarTodos() {
        bibliotecarios = new ArrayList<>();
    }
    /**
     * Busca um bibliotecário pelo id - CPF.
     * @param id - CPF do bibliotecário a ser buscado.
     * @return Bibliotecário encontrado ou null se o cpf não possuir cadastro.
     */
    @Override
    public Bibliotecario buscarPorId(String id) {
        if(!bibliotecarios.isEmpty()){
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

        return bibliotecarios;
    }
    /**
     * Verifica se o CPF de um operador já está cadastrado.
     * @param cpf - CPF a ser verificado.
     * @return true se o CPF já estiver cadastrado, false caso contrário.
     * @throws Excecao Se o CPF já estiver cadastrado, a exceção é lançada.
     */
    public boolean cpfBibliotecarioEstaCadastrado(String cpf){
        if (!bibliotecarios.isEmpty()){
            for (Bibliotecario b : bibliotecarios) {
                if (b.getCpf().equals(cpf))
                    return true;
            }
        } return false;
    }


}
