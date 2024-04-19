package br.com.mfdesenvolvimento.locationshop.Firebase;

import br.com.mfdesenvolvimento.locationshop.Config.FirebaseService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FirebaseServiceTest {

    @Test
    public void testGetUidFromToken() {
        // Mock do token de autenticação válido para um usuário
        String idToken = "cNu3VpqkrTgGQE0dqaOQPZaVjw32";

        // Instância do FirebaseService
        FirebaseService firebaseService = new FirebaseService();

        // Chama o método getUidFromToken para obter o UID
        String uid = firebaseService.getUidFromToken(idToken);

        // Verifica se o UID obtido não é nulo
        assertNotNull(uid);
        // Se necessário, você pode adicionar mais asserções para verificar o formato do UID ou outros critérios
    }
}
