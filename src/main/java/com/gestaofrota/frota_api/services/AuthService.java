// Crie este arquivo em: com.gestaofrota.frota_api.services
package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.LoginRequestDTO;
import com.gestaofrota.frota_api.dtos.LoginResponseDTO;
import com.gestaofrota.frota_api.models.Usuario;
import com.gestaofrota.frota_api.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos."));

        // Comparação de senha em texto puro (conforme sua decisão)
        if (!loginRequest.getPassword().equals(usuario.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos.");
        }

        // Busca o nome do motorista associado para incluir na resposta
        String nome = usuario.getMotorista() != null ? usuario.getMotorista().getNomeCompleto() : "Administrador";
        
        // No futuro, aqui você geraria um token JWT. Por enquanto, usamos um placeholder.
        String token = "token_de_exemplo_jwt";

        return new LoginResponseDTO(
            usuario.getId(),
            nome,
            usuario.getEmail(),
            usuario.getTipoPerfil(),
            token
        );
    }
}
