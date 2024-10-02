package br.com.mfdesenvolvimento.locationshop.Products.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> handleImageUpload(@RequestParam("image") MultipartFile file) {
        // Verificar se o arquivo é uma imagem
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhuma imagem foi selecionada.");
        }

        // Verifica o tipo MIME para garantir que seja uma imagem
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Arquivo não é uma imagem válida.");
        }

        try {
            // Processar o arquivo (salvar, etc.)
            String fileName = file.getOriginalFilename();
            // Aqui você pode salvar o arquivo ou fazer o processamento que precisar
            return ResponseEntity.ok("Imagem carregada com sucesso: " + fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o upload da imagem.");
        }
    }
}