package br.com.mfdesenvolvimento.locationshop.Config;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseService {

    public FirebaseService() {
        try {
            // Caminho para o arquivo google-services.json
            FileInputStream serviceAccount = new FileInputStream("locationshop-a0d88-firebase-adminsdk-m6aoe-8f55fddf5d.json");

            // Configurações do Firebase
            // Configurações do Firebase
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            // Inicializa o Firebase com as opções configuradas
            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUidFromToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método main apenas para exemplo de uso
    public static void main(String[] args) {
        FirebaseService firebaseService = new FirebaseService();

        // Exemplo de obtenção do UID a partir do token de autenticação
        String idToken = "ID_TOKEN_HERE"; // Substitua pelo token de autenticação do usuário
        String uid = firebaseService.getUidFromToken(idToken);
        System.out.println("UID do usuário: " + uid);
    }
}