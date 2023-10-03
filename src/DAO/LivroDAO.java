package DAO;

import Model.Operacoes.Livro;
import Model.Usuarios.Leitor;

import java.util.*;
/**
 * Classe LivroDAO: implementa a interface DAOgenerico para o objeto Livro.
 * Contém métodos para gerenciamento dos livros no acervo da biblioteca.
 */
public class LivroDAO implements DAOgenerico<Livro>{
    private static List<Livro> acervo = new ArrayList<>();
    private static Map<String, Queue<Leitor>> reservasPorTitulo = new HashMap<>();
    /**
     * Adiciona um novo livro ao acervo.
     * @param c - livro a ser adicionado.
     */
    @Override
    public void salvar(Livro c) {
        if (!acervo.contains(c)) {
            acervo.add(c);
        }
    }
    /**
     * Deleta um livro do acervo.
     * @param c - livro a ser deletado.
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
     * Retorna todos os livros do acervo.
     * @return Lista contendo todos os livros do acervo.
     */
    public List<Livro> getAcervo() {
        return acervo;
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoTitulo - String como o novo dado a ser alterado no livro
     */
    public void atualizarTituloLivro(Livro livro, String novoTitulo) {
        livro.setTitulo(novoTitulo);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoAutor - String como o novo dado a ser alterado no livro
     */
    public void atualizarAutorLivro(Livro livro, String novoAutor) {
        livro.setAutor(novoAutor);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novaCategoria - String como o novo dado a ser alterado no livro
     */
    public void atualizarCategoriaLivro(Livro livro, String novaCategoria) {
        livro.setCategoria(novaCategoria);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoIsbn - String como o novo dado a ser alterado no livro
     */
    public void atualizarIsbnLivro(Livro livro, String novoIsbn) {
        livro.setIsbn(novoIsbn);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novaEditora - String como o novo dado a ser alterado no livro
     */
    public void atualizarEditoraLivro(Livro livro, String novaEditora) {
        livro.setEditora(novaEditora);
    }
    /**
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novoAnoPublicacao - String como o novo dado a ser alterado no livro
     */
    public void atualizarAnoPublicacaoLivro(Livro livro, String novoAnoPublicacao) {
        livro.setAnoPublicacao(novoAnoPublicacao);
    }
    /**
     * Busca um livro do acervo.
     * @param titulo - Titulo do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorTitulo(String titulo){
        List<Livro> livroPorTitulo = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                livroPorTitulo.add(livro);
            }
        }
        return livroPorTitulo; // Retorna null se o livro não for encontrado
    }
    /**
     * Busca um livro do acervo.
     * @param autor - Autor do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorAutor(String autor) {
        List<Livro> livroPorAutor = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                livroPorAutor.add(livro);
            }
        }
        return livroPorAutor;
    }
    /**
     * Busca um livro do acervo.
     * @param isbn - ISBN do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorIsbn(String isbn) {
        List<Livro> livroPorIsbn = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                livroPorIsbn.add(livro);
            }
        }
        return livroPorIsbn;
    }
    /**
     * Busca um livro do acervo.
     * @param categoria - Categoria do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorCategoria(String categoria) {
        List<Livro> livroPorCategoria = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getCategoria().equalsIgnoreCase(categoria)) {
                livroPorCategoria.add(livro);
            }
        }
        return livroPorCategoria;
    }
    /**
     * Busca um livro do acervo.
     * @param anoPubli - Ano de publicação do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorAnoPublicacao(String anoPubli) {
        List<Livro> livroPorAnoPubli = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao().equalsIgnoreCase(anoPubli)) {
                livroPorAnoPubli .add(livro);
            }
        }
        return livroPorAnoPubli;
    }
    /**
     * Busca um livro do acervo.
     * @param editora - Editora do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorEditora(String editora) {
        List<Livro> livroPorEditora = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getEditora().equalsIgnoreCase(editora)) {
                livroPorEditora .add(livro);
            }
        }
        return livroPorEditora;
    }
    /**
     * Pesquisa livros no acervo com base em um texto/String fornecido.
     * A pesquisa é realizada nos campos de título, autor, ISBN, categoria e ano de publicação.
     * @param texto - texto a ser usado na pesquisa.
     * @return Um mapa contendo listas de livros que correspondem ao texto em seus respectivos campos.
     */
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
    /**
     * Verifica se um livro específico está no acervo.
     * @param livro - Livro a ser verificado.
     * @return true se o livro estiver no acervo, false caso contrário.
     */
    public boolean possuiLivro(Livro livro) {
        return acervo.contains(livro);
    }
    /**
     * Retorna a fila de reservas para um livro específico.
     * @param titulo O título do livro.
     * @return A fila de leitores que reservaram o livro.
     */
    public Queue<Leitor> getReservasPorTitulo(String titulo) {
        titulo = titulo.toLowerCase();
        return reservasPorTitulo.get(titulo);
    }
    /**
     * Define a fila de reservas para um titulo de livro específico.
     * @param titulo - título do livro.
     * @param leitoresNaFila A fila de leitores que reservaram o livro.
     */
    public void setLeitoresReservasPorTitulo(String titulo, Queue<Leitor> leitoresNaFila) {
        titulo = titulo.toLowerCase();
        reservasPorTitulo.put(titulo, leitoresNaFila);
    }
    /**
     * Verifica quem é o primeiro leitor da fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return O leitor que está no início da fila.
     */
    public Leitor verificaPrimeiroDaFila(String titulo){
        titulo = titulo.toLowerCase();
        if(reservasPorTitulo.get(titulo) != null)
            return reservasPorTitulo.get(titulo).peek();
        return null;
    }
    /**
     * Retorna os nomes dos leitores na fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return Uma lista com os nomes dos leitores na fila.
     */
    public List<String> nomesNaFila(String titulo){
        titulo = titulo.toLowerCase();
        List<String> filaDeLeitoresPorTitulo = new ArrayList<>();
        if (reservasPorTitulo.get(titulo) != null) {
            for (Leitor leitor : reservasPorTitulo.get(titulo))
                filaDeLeitoresPorTitulo.add(leitor.getNome());
        }
        return filaDeLeitoresPorTitulo;
    }
    /**
     * Retorna a quantidade de leitores na fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return A quantidade de leitores na fila.
     */
    public int qtdLeitoresNaFila(String titulo){
        titulo = titulo.toLowerCase();
        return reservasPorTitulo.get(titulo).size();
    }
    /**
     * Remove o primeiro leitor da fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro reservado.
     */
    public void removePrimeiroDafila(String titulo){
        titulo = titulo.toLowerCase();
        if (reservasPorTitulo.get(titulo) != null) {
            Leitor primeiro = reservasPorTitulo.get(titulo).poll();
        }
    }

}
