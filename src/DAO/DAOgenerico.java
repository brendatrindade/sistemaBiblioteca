package DAO;

public interface DAOgenerico <Classe> {

    void salvar(Classe c);

    void deletar(Classe c);

    void deletarTodos();

    Classe buscarPorId(String id);


}
