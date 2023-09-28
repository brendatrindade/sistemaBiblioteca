package DAO;

import Excecoes.Excecao;
import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;

import java.util.*;

public class LivroDAO implements CRUD<Livro>{
    private static List<Livro> acervo = new ArrayList<>();
    private static Map<String, Queue<Leitor>> reservasPorTitulo = new HashMap<>();

    /**
     * Cria novo Livro
     */
    public void cria(String titulo, String autor, String isbn, String categoria, String anoPublicacao, String editora) throws Excecao {
        Livro livro = new Livro(titulo,autor,isbn,categoria,anoPublicacao,editora);
        acervo.add(livro);
    }
    /**
     * Adiciona novo Livro
     *
     * @param livro
     */
    @Override
    public void adiciona(Livro livro) {
        acervo.add(livro);
    }
    /**
     * Lê todos os Livros
     */
    @Override
    public List<Livro> get() {
        return acervo;
    }

    /**
     * Atualiza um Livro - Titulo
     *
     * @param livro
     * @param novoDado
     */
    @Override
    public void altera(Livro livro, String novoDado) {
        livro.setTitulo(novoDado);
    }
    /**
     * Deleta um Livro
     *
     * @param livro
     */
    @Override
    public void remove(Livro livro) {
        acervo.remove(livro);
        System.out.println("Livro " + livro.getTitulo() + " apagado com sucesso!");
    }

    /**
     * Deleta todos os Livros
     */
    @Override
    public void removeTodos() {
        acervo = new ArrayList<>();
    }

    //Metodos para atualizar informações do livro
    public void atualizarTituloLivro(Livro livro, String novoTitulo) {
        livro.setTitulo(novoTitulo);
    }
    public void atualizarAutorLivro(Livro livro, String novoAutor) {
        livro.setAutor(novoAutor);
    }
    public void atualizarCategoriaLivro(Livro livro, String novaCategoria) {
        livro.setCategoria(novaCategoria);
    }
    public void atualizarIsbnLivro(Livro livro, String isbn) {
        livro.setIsbn(isbn);
    }
    public void atualizarEditoraLivro(Livro livro, String editora) {
        livro.setEditora(editora);
    }
    public void atualizarAnoPublicacaoLivro(Livro livro, String anoPublicacao) {
        livro.setAnoPublicacao(anoPublicacao);
    }

    //Metodos para pesquisar livros no acervo
    public List<Livro> buscarLivroPorTitulo(String titulo){
        List<Livro> livroPorTitulo = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroPorTitulo.add(livro);
            }
        }
        return livroPorTitulo; // Retorna null se o livro não for encontrado
    }
    public List<Livro> buscarLivroPorAutor(String autor) {
        List<Livro> livroPorAutor = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                livroPorAutor.add(livro);
            }
        }
        return livroPorAutor;
    }
    public List<Livro> buscarLivroPorIsbn(String isbn) {
        List<Livro> livroPorIsbn = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                livroPorIsbn.add(livro);
            }
        }
        return livroPorIsbn;
    }
    public List<Livro> buscarLivroPorCategoria(String categoria) {
        List<Livro> livroPorCategoria = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getCategoria().equalsIgnoreCase(categoria)) {
                livroPorCategoria.add(livro);
            }
        }
        return livroPorCategoria;
    }
    public List<Livro> buscarLivroPorAnoPublicacao(String anoPubli) {
        List<Livro> livroPorAnoPubli = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao().equalsIgnoreCase(anoPubli)) {
                livroPorAnoPubli .add(livro);
            }
        }
        return livroPorAnoPubli;
    }
    public List<Livro> buscarLivroPorEditora(String editora) {
        List<Livro> livroPorEditora = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getEditora().equalsIgnoreCase(editora)) {
                livroPorEditora .add(livro);
            }
        }
        return livroPorEditora;
    }
    // Verificar se o acervo possui um livro - (Objeto livro)
    public boolean possuiLivro(Livro livro) {
        boolean possui = acervo.contains(livro);
        return possui;
    }

    public Queue<Leitor> getReservasPorTitulo(String titulo) {
        titulo = titulo.toLowerCase();
        return reservasPorTitulo.get(titulo);
    }
    public void setLeitoresReservasPorTitulo(String titulo, Queue<Leitor> leitoresNaFila) {
        titulo = titulo.toLowerCase();
        reservasPorTitulo.put(titulo, leitoresNaFila);
    }

    public void atualizaLeitorNaFila(){
        //reservasPorTitulo.remove();

    }

    public Leitor verificaPrimeiroDaFila(String titulo){
        titulo = titulo.toLowerCase();
        if(reservasPorTitulo.get(titulo) != null)
            return reservasPorTitulo.get(titulo).peek();
        return null;
    }

    public List<String> nomesNaFila(String titulo){
        titulo = titulo.toLowerCase();
        List<String> filaDeLeitoresPorTitulo = new ArrayList<>();
        if (reservasPorTitulo.get(titulo) != null) {
            for (Leitor leitor : reservasPorTitulo.get(titulo))
                filaDeLeitoresPorTitulo.add(leitor.getNome());
        }
        return filaDeLeitoresPorTitulo;
    }

    public int qtdLeitoresNaFila(String titulo){
        titulo = titulo.toLowerCase();
        return reservasPorTitulo.get(titulo).size();
    }

    public void removePrimeiroDafila(String titulo){
        titulo = titulo.toLowerCase();
        if (reservasPorTitulo.get(titulo) != null) {
            Leitor primeiro = reservasPorTitulo.get(titulo).poll();
        }
    }

    public void adicionarLivro(Livro livro) {
        if (!acervo.contains(livro)){
            acervo.add(livro);
            System.out.println("Livro " + livro.getTitulo() + " adicionado com sucesso!");}
        else
            System.out.println("Ops! Este exemplar de " + livro.getTitulo() + " já faz parte do acervo");
    }
    // Método para remover um livro do acervo
    public void removerLivro(Livro livro) {
        acervo.remove(livro);
        System.out.println("Livro " + livro.getTitulo() + " removido com sucesso!");
    }



}
