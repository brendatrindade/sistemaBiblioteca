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
import com.sistemaBiblioteca.Model.Operacoes.Livro;
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
    /**
     * Criar e registrar um novo bibliotecario no sistema.
     * @param bibliotecario Objeto contendo os atributos necessários.
     * @return Objeto bibliotecario registrado.
     */
    public Bibliotecario criarBibliotecario(Bibliotecario bibliotecario) throws Exception {
        if (!bibliotecario.validaCPF(bibliotecario.getCpf())) {
            throw new Exception("CPF inválido!");
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
        Persistencia.salvarBibliotecario(this.bibliotecarios);
    }
    /**
     * Deleta um bibliotecario do arquivo.
     * @param bibliotecario - bibliotecario a ser deletado.
     * @throws Exception se ocorrer um erro no processo de deletar do arquivo.
     */
    public void deletarBibliotecarioArquivo(Bibliotecario bibliotecario) throws Exception {
        if (this.bibliotecarios.contains(bibliotecario)){
            if( this.bibliotecarios.remove(bibliotecario) ){
                salvarBibliotecarioArquivo();
            } else {
                throw new Exception("Erro ao deletar bibliotecario.");
            }
        } else {
            throw new Exception("Bibliotecario não encontrado no arquivo.");
        }
    }
    /**
     * Deleta todos os bibliotecarios do arquivo
     */
    public void deletarTodosBibliotecariosArquivo() throws Exception {
        deletarTodos();
        salvarBibliotecarioArquivo();
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
        return this.operadores;
    }
    /**
     * Salva um novo bibliotecário na lista de bibliotecários.
     * @param c - bibliotecário a ser salvo.
     */
    @Override
    public void salvar(Bibliotecario c) {
        this.bibliotecarios.add(c);
    }
    /**
     * Deleta um bibliotecário da lista de bibliotecários.
     * @param c - bibliotecário a ser deletado.
     */
    @Override
    public void deletar(Bibliotecario c) {
        this.bibliotecarios.remove(c);
    }
    /**
     * Deleta todos os bibliotecários da lista de bibliotecários.
     */
    @Override
    public void deletarTodos() {
        this.bibliotecarios = new ArrayList<>();
    }
    /**
     * Busca um bibliotecário pelo id - CPF.
     * @param id - CPF do bibliotecário a ser buscado.
     * @return Bibliotecário encontrado ou null se o cpf não possuir cadastro.
     */
    @Override
    public Bibliotecario buscarPorId(String id) {
        if(!this.bibliotecarios.isEmpty()){
            for(Bibliotecario bibliotecario : this.bibliotecarios){
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
        for (Bibliotecario bibliotecario : this.operadores) {
            if (!(bibliotecario instanceof Administrador)){
                this.bibliotecarios.add(bibliotecario);
            }
        }

        return this.bibliotecarios;
    }
    /**
     * Verifica se o CPF de um bibliotecario já está cadastrado.
     * @param cpf - CPF a ser verificado.
     * @return true se o CPF já estiver cadastrado, false caso contrário.
     * @throws Excecao Se o CPF já estiver cadastrado, a exceção é lançada.
     */
    public boolean cpfBibliotecarioEstaCadastrado(String cpf){
        if (!this.bibliotecarios.isEmpty()){
            for (Bibliotecario b : this.bibliotecarios) {
                if (b.getCpf().equals(cpf))
                    return true;
            }
        } return false;
    }
    /**
     * Retorna a senha do bibliotecário.
     * @param cpf - CPF do bibliotecário a ser buscado.
     * @return String senha cadastrada.
     */
    public String getSenhaBibliotecario(String cpf) {
        Bibliotecario b = buscarPorId(cpf);
        return b.getSenha();
    }
    /**
     * Lê a lista de bibliotecarios de um arquivo.
     * @return - lista de bibliotecarios lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public List<Bibliotecario> lerBibliotecarioArquivo() throws Exception {
        List<Bibliotecario> bibliotecariosArquivo = Persistencia.lerBibliotecario();
        return bibliotecariosArquivo;
    }
    public boolean bibliotecariosIguais(Bibliotecario bibliotecario1, Bibliotecario bibliotecario2){
        if (bibliotecario1.getCpf().equals(bibliotecario2.getCpf())) return true;
        else return false;
    }
    public boolean bloquearBibliotecario(Bibliotecario bibliotecarioParaBloquear) throws Exception {
        List<Bibliotecario> bibliotecariosArquivo = lerBibliotecarioArquivo();
        boolean bibliotecarioEncontrado = false;
        int i = 0;
        while ( (!bibliotecarioEncontrado) && (i < bibliotecariosArquivo.size()) ){
            Bibliotecario bibliotecario = bibliotecariosArquivo.get(i);
            if (bibliotecariosIguais(bibliotecario, bibliotecarioParaBloquear)){
                bibliotecario.bloquearConta();
                this.bibliotecarios = bibliotecariosArquivo;
                salvarBibliotecarioArquivo();
                bibliotecarioEncontrado = true;
            }
            i++;
        }
        return bibliotecarioEncontrado;
    }

    public boolean desbloquearBibliotecario(Bibliotecario bibliotecarioParaDesbloquear) throws Exception {
        List<Bibliotecario> bibliotecariosArquivo = lerBibliotecarioArquivo();
        boolean bibliotecarioEncontrado = false;
        int i = 0;
        while ( ( !bibliotecarioEncontrado ) && ( i < bibliotecariosArquivo.size() ) ){
            Bibliotecario bibliotecario = bibliotecariosArquivo.get(i);
            if (bibliotecariosIguais(bibliotecario, bibliotecarioParaDesbloquear)){
                bibliotecario.desbloquearConta();
                this.bibliotecarios = bibliotecariosArquivo;
                salvarBibliotecarioArquivo();
                bibliotecarioEncontrado = true;
            }
            i++;
        }
        return bibliotecarioEncontrado;
    }

}
