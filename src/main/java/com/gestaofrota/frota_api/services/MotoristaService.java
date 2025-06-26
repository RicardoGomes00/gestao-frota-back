package com.gestaofrota.frota_api.services;

import com.gestaofrota.frota_api.dtos.MotoristaDTO;
import com.gestaofrota.frota_api.models.Motorista;
import com.gestaofrota.frota_api.models.Usuario;
import com.gestaofrota.frota_api.repositories.MotoristaRepository;
import com.gestaofrota.frota_api.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MotoristaService {

    private final MotoristaRepository motoristaRepository;
    private final UsuarioRepository usuarioRepository;

    public MotoristaService(MotoristaRepository motoristaRepository, UsuarioRepository usuarioRepository) {
        this.motoristaRepository = motoristaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Motorista createMotorista(MotoristaDTO dto) {
        usuarioRepository.findByEmail(dto.getEmail()).ifPresent(u -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este e-mail já está em uso.");
        });

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setSenha(dto.getSenha());
        
        if (dto.getPerfil() != null && !dto.getPerfil().isEmpty()) {
            novoUsuario.setTipoPerfil(dto.getPerfil().toLowerCase());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'perfil' é obrigatório.");
        }

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        Motorista novoMotorista = new Motorista();
        novoMotorista.setUsuario(usuarioSalvo);
        novoMotorista.setNomeCompleto(dto.getNomeCompleto());
        novoMotorista.setCpf(dto.getCpf());
        novoMotorista.setCnhNumero(dto.getCnhNumero());
        novoMotorista.setCnhValidade(dto.getCnhValidade());
        novoMotorista.setTelefone(dto.getTelefone());
        novoMotorista.setCep(dto.getCep());
        novoMotorista.setLogradouro(dto.getLogradouro());
        novoMotorista.setNumero(dto.getNumero());
        novoMotorista.setBairro(dto.getBairro());
        novoMotorista.setCidade(dto.getCidade());
        novoMotorista.setUf(dto.getUf());
        novoMotorista.setAtivo(true);

        return motoristaRepository.save(novoMotorista);
    }

    public List<Motorista> buscarTodos() {
        return motoristaRepository.findAll();
    }
    
    public Motorista buscarPorId(Long id) {
        return motoristaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado com o ID: " + id));
    }

    @Transactional
    public Motorista atualizarMotorista(Long id, MotoristaDTO motoristaDetalhes) {
        Motorista motoristaExistente = buscarPorId(id); 

        motoristaExistente.setNomeCompleto(motoristaDetalhes.getNomeCompleto());
        motoristaExistente.setCnhNumero(motoristaDetalhes.getCnhNumero());
        motoristaExistente.setCnhValidade(motoristaDetalhes.getCnhValidade());
        motoristaExistente.setTelefone(motoristaDetalhes.getTelefone());
        motoristaExistente.setCep(motoristaDetalhes.getCep());
        motoristaExistente.setLogradouro(motoristaDetalhes.getLogradouro());
        motoristaExistente.setNumero(motoristaDetalhes.getNumero());
        motoristaExistente.setBairro(motoristaDetalhes.getBairro());
        motoristaExistente.setCidade(motoristaDetalhes.getCidade());
        motoristaExistente.setUf(motoristaDetalhes.getUf());

        Usuario usuarioAssociado = motoristaExistente.getUsuario();
        usuarioAssociado.setEmail(motoristaDetalhes.getEmail());
        usuarioAssociado.setTipoPerfil(motoristaDetalhes.getPerfil().toLowerCase());

        if (motoristaDetalhes.getSenha() != null && !motoristaDetalhes.getSenha().trim().isEmpty()) {
            usuarioAssociado.setSenha(motoristaDetalhes.getSenha());
        }

        return motoristaRepository.save(motoristaExistente);
    }

    @Transactional
    public void inativarMotorista(Long id) {
        Motorista motorista = buscarPorId(id);
        motorista.setAtivo(false);
        motoristaRepository.save(motorista);
    }
}
