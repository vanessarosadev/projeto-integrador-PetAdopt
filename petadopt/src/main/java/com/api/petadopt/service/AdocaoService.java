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
    
    public AdocaoEntity registrarAdocao(Integer animalId, Integer adotanteId, LocalDate dataAdocao, String observacao) {
        AnimalEntity animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com ID " + animalId));
        if (!animal.getDisponivel()) {
            throw new IllegalArgumentException("O animal não está disponível para adoção.");
        }

        AdotanteEntity adotante = adotanteRepository.findById(adotanteId)
                .orElseThrow(() -> new ResourceNotFoundException("Adotante não encontrado com ID " + adotanteId));

        AdocaoEntity adocao = new AdocaoEntity(animal, adotante, dataAdocao, observacao);
        adocaoRepository.save(adocao);

        animal.setDisponivel(false);
        animalRepository.save(animal);

        return adocao;
    }
    
    public AdocaoEntity getAdocaoId(Integer adocaoId) { 

        return adocaoRepository.findById(adocaoId)
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
    
}
