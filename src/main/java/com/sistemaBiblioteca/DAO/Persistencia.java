/**
 * Persistencia
 *
 * @author Brenda Araújo Trindade Oliveira
 * @version 1.0
 * @since 04/12/2023
 *
 */
package com.sistemaBiblioteca.DAO;
import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Usuarios.Administrador;
import com.sistemaBiblioteca.Model.Usuarios.Bibliotecario;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;

import java.io.*;
import java.util.*;

/**
 * Esta classe fornece métodos para realizar operações de persistência de dados em arquivos.
 * @param <T> - tipo de objeto para a classe manipular.
 */
public class Persistencia<T> {
    public static boolean existeCache(){
        if((new File("cache")).exists()){
            return true;
        }
        return false;
    }
    /**
     * Cria uma pasta de armazenamento cache se ele não existir e inicializa com listas vazias.
     * @throws Exception se ocorrer um erro durante a criação do cache.
     */
    public static void criarCache() throws Exception{
        if(!(new File("cache")).exists()){
            File file = new File("cache");
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        if(!(new File("cache\\leitores.ser")).exists()){
            Persistencia.salvarLeitor(new ArrayList<Leitor>());
        }
        if(!(new File("cache\\livros.ser")).exists()){
            Persistencia.salvarLivro(new ArrayList<Livro>());
        }
        if(!(new File("cache\\bibliotecarios.ser")).exists()){
            Persistencia.salvarBibliotecario(new ArrayList<Bibliotecario>());
        }
        if(!(new File("cache\\administradores.ser")).exists()){
            Persistencia.salvarAdministrador(new ArrayList<Administrador>());
        }
        if(!(new File("cache\\reservasPorTitulo.ser")).exists()){
            Persistencia.salvarReservasPorTitulo(new HashMap<>());
        }
        if(!(new File("cache\\historicoEmprestimos.ser")).exists()){
            Persistencia.salvarHistoricoEmprestimos(new HashMap<>());
        }
    }
    /**
     * Salva uma lista de objetos em um arquivo.
     * @param caminhoArquivo - caminho do arquivo.
     * @param lista - lista de objetos a ser salva.
     */
    public void salvarNoArquivo(String caminhoArquivo, List<T> lista) {
        try {
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(lista);
            obj.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Recupera uma lista de objetos de um arquivo.
     * @param caminhoArquivo - caminho do arquivo.
     * @return - lista de objetos lidos do arquivo.
     */
    public List<T> lerDoArquivo(String caminhoArquivo) {
        List<T> lista = null;
        try {
            FileInputStream arquivado = new FileInputStream(caminhoArquivo);
            ObjectInputStream obj = new ObjectInputStream(arquivado);
            lista = (List<T>) obj.readObject();
            obj.close();
            arquivado.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Classe não encontrada");
            c.printStackTrace();
        }
        return lista;
    }

    /**
     * Salva uma lista de leitores em um arquivo.
     * @param listaLeitores - lista de leitores a ser salva.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public static void salvarLeitor(List<Leitor> listaLeitores) throws Exception {
        try {
            File caminhoArquivo = new File("cache\\leitores.ser");
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(listaLeitores);
            obj.close();
            arquivo.close();
        } catch (Exception e) {
            throw new Exception("Não foi possível salvar Leitores.");
        }
    }
    /**
     * Lê uma lista de leitores de um arquivo.
     * @return - lista de leitores lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public static List<Leitor> lerLeitor() throws Exception{
        try {
            FileInputStream caminhoArquivo = new FileInputStream("cache\\leitores.ser");
            ObjectInputStream arquivo = new ObjectInputStream(caminhoArquivo);
            List<Leitor> leitores = (List<Leitor>) arquivo.readObject();
            arquivo.close();
            if (leitores.isEmpty()) {
                return new ArrayList<>();
            }
            return leitores;
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo não foi encontrado no sistema.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("Classe não encontrada.", e);
        } catch (IOException e) {
            throw new Exception("Problemas na leitura do arquivo.", e);
        }
    }
    /**
     * Salva uma lista de livros em um arquivo.
     * @param listaLivros - lista de livros a ser salva.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public static void salvarLivro(List<Livro> listaLivros) throws Exception {
        try {
            File caminhoArquivo = new File("cache\\livros.ser");
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(listaLivros);
            obj.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lê uma lista de livros de um arquivo.
     * @return - lista de livros lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public static List<Livro> lerLivro() throws Exception{
        try {
            FileInputStream caminhoArquivo = new FileInputStream("cache\\livros.ser");
            ObjectInputStream arquivo = new ObjectInputStream(caminhoArquivo);
            List<Livro> livros = (List<Livro>) arquivo.readObject();
            arquivo.close();
            if (livros.isEmpty()) {
                return new ArrayList<>();
            }
            return livros;
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo não foi encontrado no sistema.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("Classe não encontrada.", e);
        } catch (IOException e) {
            throw new Exception("Problemas na leitura do arquivo.", e);
        }
    }
    /**
     * Salva uma lista de bibliotecários em um arquivo.
     * @param listaBibliotecarios - lista de bibliotecários a ser salva.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public static void salvarBibliotecario(List<Bibliotecario> listaBibliotecarios) throws Exception {
        try {
            File caminhoArquivo = new File("cache\\bibliotecarios.ser");
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(listaBibliotecarios);
            obj.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lê uma lista de bibliotecários de um arquivo.
     * @return - lista de bibliotecários lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public static List<Bibliotecario> lerBibliotecario() throws Exception{
        try {
            FileInputStream caminhoArquivo = new FileInputStream("cache\\bibliotecarios.ser");
            ObjectInputStream arquivo = new ObjectInputStream(caminhoArquivo);
            List<Bibliotecario> bibliotecarios = (List<Bibliotecario>) arquivo.readObject();
            arquivo.close();
            if (bibliotecarios.isEmpty()) {
                return new ArrayList<>();
            }
            return bibliotecarios;
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo não foi encontrado no sistema.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("Classe não encontrada.", e);
        } catch (IOException e) {
            throw new Exception("Problemas na leitura do arquivo.", e);
        }
    }
    /**
     * Salva uma lista de administradores em um arquivo.
     * @param listaAdministradores - lista de administradores a ser salva.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public static void salvarAdministrador (List<Administrador> listaAdministradores) throws Exception {
        try {
            File caminhoArquivo = new File("cache\\administradores.ser");
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(listaAdministradores);
            obj.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lê uma lista de administradores de um arquivo.
     * @return - lista de administradores lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public static List<Administrador> lerAdministrador() throws Exception{
        try {
            FileInputStream caminhoArquivo = new FileInputStream("cache\\administradores.ser");
            ObjectInputStream arquivo = new ObjectInputStream(caminhoArquivo);
            List<Administrador> administradores = (List<Administrador>) arquivo.readObject();
            arquivo.close();
            if (administradores.isEmpty()) {
                return new ArrayList<>();
            }
            return administradores;
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo não foi encontrado no sistema.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("Classe não encontrada.", e);
        } catch (IOException e) {
            throw new Exception("Problemas na leitura do arquivo.", e);
        }
    }
    /**
     * Salva a lista de reserva de um título de livro específico no arquivo.
     * @param reservasPorTitulo - mapa de reservas por título a ser salvo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public static void salvarReservasPorTitulo(Map<String, Queue<Leitor>> reservasPorTitulo) throws Exception {
        try {
            File caminhoArquivo = new File("cache\\reservasPorTitulo.ser");
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(reservasPorTitulo);
            obj.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lê a lista de reserva arquivada de um título de livro específico.
     * @return - mapa de reservas por título lido do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public static Map<String, Queue<Leitor>> lerReservasPorTitulo() throws Exception{
        try {
            FileInputStream caminhoArquivo = new FileInputStream("cache\\reservasPorTitulo.ser");
            ObjectInputStream arquivo = new ObjectInputStream(caminhoArquivo);
            Map<String, Queue<Leitor>> reservasPorTitulo = (Map<String, Queue<Leitor>>) arquivo.readObject();
            arquivo.close();
            if (reservasPorTitulo.isEmpty()) {
                return new HashMap<>();
            }
            return reservasPorTitulo;
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo não foi encontrado no sistema.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("Classe não encontrada.", e);
        } catch (IOException e) {
            throw new Exception("Problemas na leitura do arquivo.", e);
        }
    }
    /**
     * Salva o histórico de empréstimos em um arquivo.
     * @param historicoEmprestimos - mapa do histórico de empréstimos a ser salvo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public static void salvarHistoricoEmprestimos(Map<Leitor,List<Emprestimo>> historicoEmprestimos) throws Exception {
        try {
            File caminhoArquivo = new File("cache\\historicoEmprestimos.ser");
            FileOutputStream arquivo = new FileOutputStream(caminhoArquivo);
            ObjectOutputStream obj = new ObjectOutputStream(arquivo);
            obj.writeObject(historicoEmprestimos);
            obj.close();
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Lê o histórico de empréstimos arquivado de um leitor.
     * @return - mapa do histórico de empréstimos lido do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public static Map<Leitor,List<Emprestimo>> lerHistoricoEmprestimos() throws Exception{
        try {
            FileInputStream caminhoArquivo = new FileInputStream("cache\\historicoEmprestimos.ser");
            ObjectInputStream arquivo = new ObjectInputStream(caminhoArquivo);
            Map<Leitor,List<Emprestimo>> historicoEmprestimos = (Map<Leitor,List<Emprestimo>>) arquivo.readObject();
            arquivo.close();
            if (historicoEmprestimos.isEmpty()) {
                return new HashMap<>();
            }
            return historicoEmprestimos;
        } catch (FileNotFoundException e) {
            throw new Exception("O arquivo não foi encontrado no sistema.", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("Classe não encontrada.", e);
        } catch (IOException e) {
            throw new Exception("Problemas na leitura do arquivo.", e);
        }
    }

}

