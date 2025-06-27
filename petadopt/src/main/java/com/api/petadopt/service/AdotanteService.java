/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.service;

/**
 *
 * @author vanes
 */
import com.api.petadopt.data.AdotanteEntity; 
import com.api.petadopt.data.AdotanteRepository; 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import com.api.petadopt.exception.ResourceNotFoundException;

@Service
public class AdotanteService {
    @Autowired
    AdotanteRepository adotanteRepository;
    
    public AdotanteEntity criarAdotante(AdotanteEntity adotante) { 

        adotanteRepository.save(adotante); 

        return adotante; 
    }
    
    public AdotanteEntity atualizarAdotante(Integer adotanteId, AdotanteEntity adotanteRequest) { 

        AdotanteEntity adotante = getAdotanteId(adotanteId); 

        adotante.setNome(adotanteRequest.getNome()); 

        adotante.setRg(adotanteRequest.getRg()); 

        adotante.setCpf(adotanteRequest.getCpf()); 

        adotante.setNascimento(adotanteRequest.getNascimento()); 
        
        adotante.setTelefone(adotanteRequest.getTelefone());
        
        adotante.setLogradouro(adotanteRequest.getLogradouro()); 
        
        adotante.setBairro(adotanteRequest.getBairro()); 
        
        adotante.setCidade(adotanteRequest.getCidade()); 
        
        adotante.setEstado(adotanteRequest.getEstado());
        
        adotante.setCep(adotanteRequest.getCep());
        
        adotanteRepository.save(adotante); 

        return adotante; 
    }

    public AdotanteEntity getAdotanteId(Integer adotanteId) { 

        return adotanteRepository.findById(adotanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Adotante não encontrado com id " + adotanteId)); 
    }
    
    public AdotanteEntity getAdotanteCpf(String adotanteCpf) { 

        return adotanteRepository.findByCpf(adotanteCpf)
                .orElseThrow(() -> new ResourceNotFoundException("Adotante não encontrado com CPF " + adotanteCpf)); 
    }

    public List<AdotanteEntity> listarTodosAdotantes() { 

        return adotanteRepository.findAll(); 
    } 

    public boolean deletarAdotante(Integer adotanteId) { 
        Optional<AdotanteEntity> adotante = adotanteRepository.findById(adotanteId);
        if (adotante.isPresent()) {
            adotanteRepository.delete(adotante.get());
            return true;
        }
        return false;
    } 
    
    public List<AdotanteEntity> getAdotantePorNome(String nome) { 

        return adotanteRepository.findByNomeContaining(nome); 
          
    }
    
    public List<AdotanteEntity> getAdotantePorBairro(String bairro) { 

        return adotanteRepository.findByBairroContaining(bairro); 
          
    }
    
    public List<AdotanteEntity> getAdotantePorCidade(String cidade) { 

        return adotanteRepository.findByCidadeContaining(cidade); 
          
    }
    
    public List<AdotanteEntity> getAdotantePorEstado(String estado) { 

        return adotanteRepository.findByEstadoContaining(estado); 
          
    }
    
    public List<AdotanteEntity> getAdotantePorCep(String cep) { 

        return adotanteRepository.findByCepContaining(cep); 
          
    }

}
