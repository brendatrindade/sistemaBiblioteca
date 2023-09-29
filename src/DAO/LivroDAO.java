package DAO;

import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;

import java.util.*;

public class LivroDAO implements DAOgenerico<Livro>{
    private static List<Livro> acervo = new ArrayList<>();
    private static Map<String, Queue<Leitor>> reservasPorTitulo = new HashMap<>();

    /**
     * Adiciona novo Livro ao acervo
     *
     * @param c
     */
    @Override
    public void salvar(Livro c) {
        if (!acervo.contains(c)){
            acervo.add(c);
            System.out.println("Livro " + c.getTitulo() + " adicionado com sucesso!");}
        else
            System.out.println("Ops! Este exemplar de " + c.getTitulo() + " já faz parte do acervo");
    }
    /**
     * Deleta um Livro
     *
     * @param c
     */
    @Override
    public void deletar(Livro c) {
        acervo.remove(c);
        System.out.println("Livro " + c.getTitulo() + " removido com sucesso!");
    }
    /**
     * Deleta todos os Livros
     */
    @Override
    public void deletarTodos() {
        acervo = new ArrayList<>();
    }
    @Override
    public Livro buscarPorId(String id) {
        return null;
    }
    /**
     * Retorna todos os Livros do acervo
     */
    public List<Livro> getAcervo() {
        return acervo;
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
}
