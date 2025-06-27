/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.service;

/**
 *
 * @author vanes
 */
import com.api.petadopt.data.AnimalEntity; 
import com.api.petadopt.data.AnimalRepository; 
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import com.api.petadopt.exception.ResourceNotFoundException;

@Service
public class AnimalService {
    @Autowired
    AnimalRepository animalRepository;
    
    public AnimalEntity criarAnimal(AnimalEntity animal) { 

        animalRepository.save(animal); 

        return animal; 
    }
    
    public AnimalEntity atualizarAnimal(Integer animalId, AnimalEntity animalRequest) { 

        AnimalEntity animal = getAnimalId(animalId); 

        animal.setNome(animalRequest.getNome()); 

        animal.setEspecie(animalRequest.getEspecie()); 

        animal.setGenero(animalRequest.getGenero()); 

        animal.setRaca(animalRequest.getRaca()); 
        
        animal.setPorte(animalRequest.getPorte());
        
        animal.setCor(animalRequest.getCor()); 
        
        animal.setTemperamento(animalRequest.getTemperamento()); 
        
        animal.setNascimento(animalRequest.getNascimento()); 
        
        animal.setChegada(animalRequest.getChegada());
        
        animal.setDisponivel(animalRequest.getDisponivel());
        
        animalRepository.save(animal); 

        return animal; 
    } 

    public AnimalEntity getAnimalId(Integer animalId) { 

        return animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com id " + animalId)); 
    } 

    public List<AnimalEntity> listarTodosAnimais() { 

        return animalRepository.findAll(); 
    } 
    
    public List<AnimalEntity> listarAnimaisDisponiveis() { 

        return animalRepository.findByDisponivelTrue();    
    }
    
    public List<AnimalEntity> listarAnimaisIndisponiveis() {
        return animalRepository.findByDisponivelFalse();
    }

    public boolean deletarAnimal(Integer animalId) { 
        Optional<AnimalEntity> animal = animalRepository.findById(animalId);
        if (animal.isPresent()) {
            animalRepository.delete(animal.get());
            return true;
        }
        return false;
    } 
    
    public List<AnimalEntity> getAnimalPorNome(String nome) { 

        return animalRepository.findByNomeContaining(nome); 
          
    }
        
    public List<AnimalEntity> getAnimalPorEspecie(String especie) { 

        return animalRepository.findByEspecieContaining(especie); 
          
    }
    
    public List<AnimalEntity> getAnimalPorRaca(String raca) { 

        return animalRepository.findByRacaContaining(raca); 
          
    }
    
    public List<AnimalEntity> getAnimalPorGenero(String genero) { 

        return animalRepository.findByGeneroContaining(genero); 
          
    }
    
}
