package Model.Usuarios;
/**
 * Classe Endereco: Representa o endereço residencial de um Leitor do sistema de biblioteca.
 */
public class Endereco {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    /**
     * Construtor da classe Endereco.
     * @param rua String - rua do endereço.
     * @param numero String - número da rua.
     * @param cidade String - cidade do endereço.
     * @param estado String - estado do endereço.
     */
    public Endereco(String rua, String numero, String cidade, String estado){
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }
    /**
     * Define a rua do endereço.
     * @param rua String - nova rua do endereço.
     */
    public void setRua(String rua) {
        this.rua = rua;
    }
    /**
     * Define o número do endereço.
     * @param numero String - novo número da rua.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    /**
     * Define a cidade do endereço.
     * @param cidade String - nova cidade do endereço.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    /**
     * Define o estado do endereço.
     * @param estado String - novo estado do endereço.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * Retorna a representação em String do endereço.
     * @return String - representa o endereço.
     */
    public String toString() {
        return ("Endereço: " + rua + " , " + numero +"\nCidade: " + cidade + " - " + estado + ".");
    }

}
