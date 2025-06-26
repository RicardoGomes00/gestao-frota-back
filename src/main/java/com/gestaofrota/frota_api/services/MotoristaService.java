package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.MotoristaCreateDTO;
import com.gestaofrota.frota_api.models.Motorista;
import com.gestaofrota.frota_api.models.Usuario;
import com.gestaofrota.frota_api.repositories.MotoristaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class MotoristaService {

    private MotoristaRepository motoristaRepository;

    private PasswordEncoder passwordEncoder;

    @Transactional 
    public Motorista createMotorista(MotoristaCreateDTO dto) {
        
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        novoUsuario.setTipoPerfil("motorista"); // Define o perfil padrão

        Motorista novoMotorista = new Motorista();
        novoMotorista.setNomeCompleto(dto.getNomeCompleto());
        novoMotorista.setCpf(dto.getCpf());
        novoMotorista.setCnhNumero(dto.getCnhNumero());
        novoMotorista.setCnhValidade(dto.getCnhValidade());
        novoMotorista.setTelefone(dto.getTelefone());
        novoMotorista.setEnderecoCompleto(dto.getEnderecoCompleto());

        novoMotorista.setUsuario(novoUsuario);

        return motoristaRepository.save(novoMotorista);
    }
}