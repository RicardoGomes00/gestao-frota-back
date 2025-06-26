// Crie este arquivo em: com.gestaofrota.frota_api.controllers
package com.gestaofrota.frota_api.controllers;

import com.gestaofrota.frota_api.dtos.LoginRequestDTO;
import com.gestaofrota.frota_api.dtos.LoginResponseDTO;
import com.gestaofrota.frota_api.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // Rota base para autenticação
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
