/**
 * LivroDAO
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

import com.sistemaBiblioteca.Model.Operacoes.Emprestimo;
import com.sistemaBiblioteca.Model.Operacoes.Livro;
import com.sistemaBiblioteca.Model.Operacoes.Localizacao;
import com.sistemaBiblioteca.Model.Usuarios.Leitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;
/**
 * Classe LivroDAO: implementa a interface DAOgenerico para o objeto Livro.
 * Contém métodos para gerenciamento dos livros no acervo da biblioteca.
 */
public class LivroDAO implements DAOgenerico<Livro>, Serializable {
    private List<Livro> acervo ;
    private Map<String, Queue<Leitor>> reservasPorTitulo;

    public LivroDAO() throws Exception {
        this.acervo = new ArrayList<>();
        this.acervo = Persistencia.lerLivro();
        this.reservasPorTitulo = new HashMap<>();
        this.reservasPorTitulo = Persistencia.lerReservasPorTitulo();
    }

    /**
     * Criar e registrar um novo livro no sistema.
     * @param livro Objeto contendo os atributos necessários.
     * @return Objeto livro registrado.
     */
    public Livro criarLivro(Livro livro) throws Exception {
        if (acervoPossuiLivro(livro)) {
            throw new Exception("Este livro já está registrado!");
        }
        salvar(livro);
        salvarLivroArquivo();
        return livro;
    }
    /**
     * Salva a lista de livros em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarLivroArquivo() throws Exception {
        Persistencia.salvarLivro(this.acervo);
    }
    /**
     * Deleta um livro do acervo arquivado.
     * @param livro - livro a ser deletado.
     * @throws Exception se ocorrer um erro no processo de deletar do arquivo.
     */
    public void deletarLivroArquivo(Livro livro) throws Exception {
        if (acervoPossuiLivro(livro)){
            if( this.acervo.remove(livro) ){
                salvarLivroArquivo();
            } else {
                throw new Exception("Erro ao deletar Livro.");
            }
        } else {
            throw new Exception("Livro não encontrado no Acervo.");
        }
    }
    /**
     * Deleta todos os Livros do arquivo
     */
    public void deletarTodosLivrosArquivo() throws Exception {
        deletarTodos();
        salvarLivroArquivo();
    }



    /**
     * Lê a lista de livros de um arquivo.
     * @return - lista de livros lidos do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public List<Livro> lerLivrosArquivo() throws Exception {
        List<Livro> acervoArquivo = Persistencia.lerLivro();
        return acervoArquivo;
    }
    /**
     * Salva a lista de reservas por título em um arquivo.
     * @throws Exception se ocorrer um erro no processo de salvar no arquivo.
     */
    public void salvarReservasPorTituloArquivo() throws Exception{
        Persistencia.salvarReservasPorTitulo(this.reservasPorTitulo);
    }
    /**
     * Lê a lista de reservas por título de um arquivo.
     * @return - mapa de reservas por título lido do arquivo.
     * @throws Exception se ocorrer um erro durante a leitura do arquivo.
     */
    public Map<String, Queue<Leitor>> lerReservasPorTituloArquivo() throws Exception{
        Map<String, Queue<Leitor>> reservasPorTituloArquivo = Persistencia.lerReservasPorTitulo();
        return reservasPorTituloArquivo;
    }

    public void getLocalizacao(Livro livro){
        Localizacao localizacao = livro.getLocalizacao();
    }
    /**
     * Adiciona um novo livro ao acervo.
     * @param c - livro a ser adicionado.
     */
    @Override
    public void salvar(Livro c) {
        if (!this.acervo.contains(c)) {
            this.acervo.add(c);
        }
    }
    /**
     * Deleta um livro do acervo.
     * @param c - livro a ser deletado.
     */
    @Override
    public void deletar(Livro c) {
        this.acervo.remove(c);
    }
    /**
     * Deleta todos os Livros
     */
    @Override
    public void deletarTodos() {
        this.acervo = new ArrayList<>();
    }
    /**
     * Deleta todos as Reservas
     */
    public void deletarTodasReservas() throws Exception {
        this.reservasPorTitulo = new HashMap<>();
        salvarReservasPorTituloArquivo();
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
        return this.acervo;
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
     * Atualiza um livro do acervo.
     * @param livro - Livro a ser alterado
     * @param novaPrateleira - String como o novo dado a ser alterado no livro
     * @param novaPosicao - String como o novo dado a ser alterado no livro
     */
    public void atualizarLocalizacao(Livro livro, String novaPrateleira, String novaPosicao){
        livro.setLocalizacao(novaPrateleira, novaPosicao);
    }
    /**
     * Busca um livro do acervo.
     * @param titulo - Titulo do livro a ser localizado.
     * @return Lista de livros correspondentes ao dado fornecido
     */
    public List<Livro> buscarLivroPorTitulo(String titulo) throws Exception {
        List<Livro> livroPorTitulo = new ArrayList<>();
        for (Livro livro : lerLivrosArquivo()) {
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
    public List<Livro> buscarLivroPorAutor(String autor) throws Exception {
        List<Livro> livroPorAutor = new ArrayList<>();
        for (Livro livro : lerLivrosArquivo()) {
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
    public List<Livro> buscarLivroPorIsbn(String isbn) throws Exception {
        List<Livro> livroPorIsbn = new ArrayList<>();
        for (Livro livro : lerLivrosArquivo()) {
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
    public List<Livro> buscarLivroPorCategoria(String categoria) throws Exception {
        List<Livro> livroPorCategoria = new ArrayList<>();
        for (Livro livro : lerLivrosArquivo()) {
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
    public List<Livro> buscarLivroPorAnoPublicacao(String anoPubli) throws Exception {
        List<Livro> livroPorAnoPubli = new ArrayList<>();
        for (Livro livro : lerLivrosArquivo()) {
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
    public List<Livro> buscarLivroPorEditora(String editora) throws Exception {
        List<Livro> livroPorEditora = new ArrayList<>();
        for (Livro livro : lerLivrosArquivo()) {
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
    public Map<String, List<Livro>> pesquisarLivros(String texto) throws Exception {

        Map <String, List<Livro>> resultados = new HashMap<>();

        List<Livro> titulos = new ArrayList<>();
        List<Livro> autores = new ArrayList<>();
        List<Livro> isbnes = new ArrayList<>();
        List<Livro> categorias = new ArrayList<>();
        List<Livro> anoPubli = new ArrayList<>();

        for (Livro livroSistema : lerLivrosArquivo()){
            if (livroSistema.getTitulo().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ){
                titulos.add(livroSistema);
                resultados.put("Titulos", titulos);
            }
            if( livroSistema.getAutor().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ){
                autores.add(livroSistema);
                resultados.put("Autoria", autores);
            }
            if( livroSistema.getIsbn().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ) {
                isbnes.add(livroSistema);
                resultados.put("ISBN", isbnes);
            }
            if( livroSistema.getCategoria().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ) {
                categorias.add(livroSistema);
                resultados.put("Categorias", categorias);
            }
            if (livroSistema.getAnoPublicacao().toLowerCase().replaceAll("\\s","").contains(texto.toLowerCase().replaceAll("\\s","")) ) {
                anoPubli.add(livroSistema);
                resultados.put("Ano de Publicação", anoPubli);
            }
        }
        return resultados;
    }
    /**
     * Verifica se um livro específico está no acervo.
     * @param livro - Livro a ser verificado.
     * @return true se o livro estiver no acervo, false caso contrário.
     */
    public boolean acervoPossuiLivro(Livro livro) {
        return this.acervo.contains(livro);
    }
    /**
     * Retorna a fila de reservas para um livro específico.
     * @param titulo O título do livro.
     * @return A fila de leitores que reservaram o livro.
     */
    public Queue<Leitor> getReservasPorTitulo(String titulo) {
        titulo = titulo.toLowerCase();
        return this.reservasPorTitulo.get(titulo);
    }
    /**
     * Define a fila de reservas para um titulo de livro específico.
     * @param titulo - título do livro.
     * @param leitoresNaFila A fila de leitores que reservaram o livro.
     */
    public void setLeitoresReservasPorTitulo(String titulo, Queue<Leitor> leitoresNaFila) throws Exception {
        titulo = titulo.toLowerCase();
        this.reservasPorTitulo.put(titulo, leitoresNaFila);
        salvarReservasPorTituloArquivo();
    }
    /**
     * Verifica quem é o primeiro leitor da fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro.
     * @return O leitor que está no início da fila.
     */
    public Leitor verificaPrimeiroDaFila(String titulo){
        titulo = titulo.toLowerCase();
        if( this.reservasPorTitulo.get(titulo) != null )
            return this.reservasPorTitulo.get(titulo).peek();
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
        if (this.reservasPorTitulo.get(titulo) != null) {
            for (Leitor leitor : this.reservasPorTitulo.get(titulo))
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
        if (this.reservasPorTitulo.get(titulo) != null) {
            return this.reservasPorTitulo.get(titulo).size();
        } else return 0;
    }
    /**
     * Remove o primeiro leitor da fila de reserva de um titulo de livro específico.
     * @param titulo - título do livro reservado.
     */
    public void removePrimeiroDafila(String titulo){
        titulo = titulo.toLowerCase();
        if (this.reservasPorTitulo.get(titulo) != null) {
            Leitor primeiro = this.reservasPorTitulo.get(titulo).poll();
        }
    }
    public void removerLivroPorTitulo(String titulo) throws Exception {
        List<Livro> livrosArquivo = lerLivrosArquivo();
        boolean livroEncontrado = false;
        int i = 0;
        while ( (!livroEncontrado) && (i < livrosArquivo.size()) ){
            Livro livro = livrosArquivo.get(i);
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                if(livro.isDisponibilidade()){
                    acervo.remove(livro);
                    livroEncontrado = true;
                }
            }
            i++;
        }
    }
    public void atualizarAcervoPosEmprestimo(String tituloLivro) throws Exception {
        List<Livro> livrosArquivo = lerLivrosArquivo();
        boolean livroEncontrado = false;
        int i = 0;
        while ( (!livroEncontrado) && (i < livrosArquivo.size()) ){
            Livro livro = livrosArquivo.get(i);
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                if(livro.isDisponibilidade()) {
                    livro.setDisponibilidade(false);
                    this.acervo = livrosArquivo;
                    salvarLivroArquivo();
                    livroEncontrado = true;
                }
            }
            i++;
        }
    }
    public void atualizarAcervoPosDevolucao(String tituloLivro) throws Exception {
        List<Livro> livrosArquivo = lerLivrosArquivo();
        boolean livroEncontrado = false;
        int i = 0;
        while ( (!livroEncontrado) && (i < livrosArquivo.size()) ){
            Livro livro = livrosArquivo.get(i);
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                if(!livro.isDisponibilidade()) {
                    livro.setDisponibilidade(true);
                    this.acervo = livrosArquivo;
                    salvarLivroArquivo();
                    livroEncontrado = true;
                }
            }
            i++;
        }
    }
    public void removerLivroPorTituloIndisponivel(String titulo) throws Exception {
        List<Livro> livrosAcervo = lerLivrosArquivo();
        boolean livroEncontrado = false;
        int i = 0;
        while ( (!livroEncontrado) && (i < livrosAcervo.size()) ){
            Livro livro = livrosAcervo.get(i);
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                if (!livro.isDisponibilidade()) {
                    acervo.remove(livro);
                    salvarLivroArquivo();
                    livroEncontrado = true;
                }
            }
            i++;
        }
    }
    public boolean livrosIguais(Livro livro1, Livro livro2){
         if( ( livro1.getTitulo().equals(livro2.getTitulo()) ) && ( livro1.getAutor().equals(livro2.getAutor()) ) &&
             ( livro1.getIsbn().equals(livro2.getIsbn()) ) && ( livro1.getCategoria().equals(livro2.getCategoria()) )&&
             ( livro1.getAnoPublicacao().equals(livro2.getAnoPublicacao()) ) && ( livro1.getEditora().equals(livro2.getEditora()) )&&
             ( livro1.isDisponibilidade() == livro2.isDisponibilidade() ) ){
                return true;
        } else {
            return false;
        }
    }
    public boolean removerLivro(Livro livroParaRemover) throws Exception {
        List<Livro> livrosAcervo = lerLivrosArquivo();
        boolean livroEncontrado = false;
        int i = 0;
        while ( (!livroEncontrado) && (i < livrosAcervo.size()) ){
            Livro livro = livrosAcervo.get(i);
            if (livrosIguais(livro, livroParaRemover)){
                livrosAcervo.remove(livro);
                this.acervo = livrosAcervo;
                salvarLivroArquivo();
                livroEncontrado = true;
            }
            i++;
        }
        return livroEncontrado;
    }
    public int totalLivrosReservadosArquivo() throws Exception {
        int totalReservas = 0;
        for ( Queue<Leitor> filaPorTitulo : lerReservasPorTituloArquivo().values() ) {
            totalReservas += filaPorTitulo.size();
        }
        return totalReservas;
    }

}
