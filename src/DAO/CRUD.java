package DAO;

import Excecoes.Excecao;
import Model.Usuarios.Endereco;

import java.util.List;

public interface CRUD <Tipo_Do_Objeto>{

    /**Adiciona novo Objeto */
    public void adiciona(Tipo_Do_Objeto objeto);
    /**Lê todos os Objetos */
    public List<Tipo_Do_Objeto> get();
    /**Atualiza um Objeto*/
    public void altera(Tipo_Do_Objeto objeto, String novoDado);
    /** Deleta um Objeto*/
    public void remove(Tipo_Do_Objeto objeto);
    /** Deleta todos os dados*/
    public void removeTodos();

}
