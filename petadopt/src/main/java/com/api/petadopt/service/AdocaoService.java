/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.service;

/**
 *
 * @author vanes
 */
import com.api.petadopt.data.AdocaoEntity; 
import com.api.petadopt.data.AdocaoRepository;
import com.api.petadopt.data.AnimalEntity; 
import com.api.petadopt.data.AnimalRepository;
import com.api.petadopt.data.AdotanteEntity; 
import com.api.petadopt.data.AdotanteRepository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import com.api.petadopt.exception.ResourceNotFoundException;

@Service
public class AdocaoService {
    @Autowired
    AdocaoRepository adocaoRepository;
    
    @Autowired
    AnimalRepository animalRepository;
    
    @Autowired
    AdotanteRepository adotanteRepository;
    
    public AdocaoEntity registrarAdocao(AdocaoEntity adocao) {
        Integer animalId = adocao.getAnimal().getId();
        Integer adotanteId = adocao.getAdotante().getId();
        AnimalEntity animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com ID " + animalId));
        if (!animal.getDisponivel()) {
            throw new IllegalArgumentException("O animal não está disponível para adoção.");
        }

        AdotanteEntity adotante = adotanteRepository.findById(adotanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Adotante não encontrado com ID " + adotanteId));

        adocao.setAnimal(animal);
        adocao.setAdotante(adotante);
        adocao.setDataAdocao(adocao.getDataAdocao() != null ? adocao.getDataAdocao() : LocalDate.now());
        adocaoRepository.save(adocao);

        animal.setDisponivel(false);
        animalRepository.save(animal);
        
        return adocao;
    }
    
    public AdocaoEntity atualizarAdocao(Integer adocaoId, AdocaoEntity adocaoRequest) { 
        AdocaoEntity adocao = getAdocaoId(adocaoId);

        if (adocaoRequest.getAnimal() != null && adocaoRequest.getAnimal().getId() != null) {
            AnimalEntity novoAnimal = animalRepository.findById(adocaoRequest.getAnimal().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com ID " + adocaoRequest.getAnimal().getId()));
            
            if (!novoAnimal.equals(adocao.getAnimal())) {
                AnimalEntity antigoAnimal = adocao.getAnimal();
                if (antigoAnimal != null) {
                    antigoAnimal.setDisponivel(true);
                    animalRepository.save(antigoAnimal);
                }
                novoAnimal.setDisponivel(false);
                animalRepository.save(novoAnimal);

                adocao.setAnimal(novoAnimal);
            }
        }

        if (adocaoRequest.getAdotante() != null && adocaoRequest.getAdotante().getId() != null) {
            AdotanteEntity adotante = adotanteRepository.findById(adocaoRequest.getAdotante().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Adotante não encontrado."));
            adocao.setAdotante(adotante);
        }

        if (adocaoRequest.getDataAdocao() != null) {
            adocao.setDataAdocao(adocaoRequest.getDataAdocao());
        }

        if (adocaoRequest.getObservacao() != null) {
            adocao.setObservacao(adocaoRequest.getObservacao());
        }

        return adocaoRepository.save(adocao); 
    }

    
    public AdocaoEntity getAdocaoId(Integer adocaoId) {
        return adocaoRepository.findById(adocaoId)
                .map(adocao -> {
                    adocao.getAnimal().getId();
                    adocao.getAdotante().getId();
                    return adocao;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Adoção não encontrada com id " + adocaoId));
    }

    public List<AdocaoEntity> listarTodasAdocoes() {
        return adocaoRepository.findAll();
    }

    public List<AdocaoEntity> listarAdocoesPorAdotante(Integer adotanteId) {
        return adocaoRepository.findByAdotanteId(adotanteId);
    }

    public List<AdocaoEntity> listarAdocoesPorAnimal(Integer animalId) {
        return adocaoRepository.findByAnimalId(animalId);
    }

    public boolean deletarAdocao(Integer adocaoId) {
        AdocaoEntity adocao = adocaoRepository.findById(adocaoId)
                .orElseThrow(() -> new ResourceNotFoundException("Adoção não encontrada com ID " + adocaoId));

        AnimalEntity animal = adocao.getAnimal();
        animal.setDisponivel(true);
        animalRepository.save(animal);

        adocaoRepository.delete(adocao);
        return true;
    }
    
    public List<AdocaoEntity> listarAdocoesPorNomeAnimal(String animalNome) {
        return adocaoRepository.findByAnimalNomeContainingIgnoreCase(animalNome);
    }

    public List<AdocaoEntity> listarAdocoesPorEspecieAnimal(String animalEspecie) {
        return adocaoRepository.findByAnimalEspecieContainingIgnoreCase(animalEspecie);
    }

    public List<AdocaoEntity> listarAdocoesPorNomeAdotante(String adotanteNome) {
        return adocaoRepository.findByAdotanteNomeContainingIgnoreCase(adotanteNome);
    }
    
}
