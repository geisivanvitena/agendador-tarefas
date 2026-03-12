package com.geisivan.agendadortarefas.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtUtil {

    // Chave secreta JWT via variável de ambiente
    @Value("${jwt.secret}")
    private String secretKey;

    // Retorna a chave secreta decodificada
    private SecretKey getSecretKey() {
        byte[] key = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    // Extrai as claims do token JWT (informações adicionais do token)
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey()) // Define a chave secreta para validar a assinatura do token
                .build()
                .parseSignedClaims(token) // Analisa o token JWT e obtém as claims
                .getPayload(); // Obtém o payload (corpo) do token, que contém as claims
    }

    // Extrai o nome de usuário do token JWT
    public String extractUsername(String token) {
        // Obtém o assunto (nome de usuário) das claims do token
        return extractClaims(token).getSubject();
    }
    // Método utilitário que Remove "Bearer " se estiver presente (maiúsculas/minúsculas)
    public String extrairEmailDoToken(String token) {
        String tokenLimpo = (token != null && token.toLowerCase().startsWith("bearer "))
                ? token.substring(7)
                : token;

        // Retorna o username (e-mail) extraído do token
        return extractUsername(tokenLimpo);
    }

    // Verifica se o token JWT está expirado
    public boolean isTokenExpired(String token) {
        // Compara a data de expiração do token com a data atual
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token JWT verificando o nome de usuário e se o token não está expirado
    public boolean validateToken(String token, String username) {
        // Extrai o nome de usuário do token
        final String extractedUsername = extractUsername(token);
        // Verifica se o nome de usuário do token corresponde ao fornecido e se o token não está expirado
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
