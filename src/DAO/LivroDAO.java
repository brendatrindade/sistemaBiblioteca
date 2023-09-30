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
        if (!acervo.contains(c)) {
            acervo.add(c);
        }
    }
    /**
     * Deleta um Livro
     *
     * @param c
     */
    @Override
    public void deletar(Livro c) {
        acervo.remove(c);
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
        //Livro possui metodos de busca especificos para seus atributos
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

    public Map<String, List<Livro>> pesquisarLivros(String texto) {
        Map <String, List<Livro>> resultados = new HashMap<>();
        List<Livro> titulos = new ArrayList<>();
        List<Livro> autores = new ArrayList<>();
        List<Livro> isbnes = new ArrayList<>();
        List<Livro> categorias = new ArrayList<>();
        List<Livro> anoPubli = new ArrayList<>();

        for (Livro livroSistema : acervo){
            if (livroSistema.getTitulo().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ){
                titulos.add(livroSistema);
                resultados.put("\nTitulos: ", titulos);
            }
            if( livroSistema.getAutor().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ){
                autores.add(livroSistema);
                resultados.put("\nAutoria: ", autores);
            }
            if( livroSistema.getIsbn().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ) {
                isbnes.add(livroSistema);
                resultados.put("\nISBN: ", isbnes);
            }
            if( livroSistema.getCategoria().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ) {
                categorias.add(livroSistema);
                resultados.put("\nCategorias: ", categorias);
            }
            if (livroSistema.getAnoPublicacao().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ) {
                anoPubli.add(livroSistema);
                resultados.put("\nAno de Publicação: ", anoPubli);
            }
        }
        return resultados;
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
