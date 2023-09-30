package Testes.Usuarios;

import Excecoes.Excecao;
import Model.Usuarios.Endereco;
import Model.Usuarios.Leitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class LeitorTeste {
    private Leitor leitor;
    private Endereco endereco;

    @BeforeEach
    public void CriarLeitor() throws Excecao {
        endereco = new Endereco("Candido Nunes", "75", "Angico-Mairi", "Bahia");
        leitor = new Leitor("Brenda", "78642486597", endereco, "74999823548");

        assertEquals("Brenda", leitor.getNome());
        assertEquals("78642486597", leitor.getCpf());
        assertEquals(endereco, leitor.getEndereco());
        assertEquals("74999823548", leitor.getTelefone());
    }

    @Test
    public void CpfInvalido() {
        assertThrows(Excecao.class, () -> new Leitor("Brenda", "12365425807", endereco, "74999823548"));
    }
    @Test
    public void SolicitarReserva() {
        Boolean reserva = leitor.solicitarReserva("Titulo Inexistente");
        assertFalse(reserva);
    }
}

