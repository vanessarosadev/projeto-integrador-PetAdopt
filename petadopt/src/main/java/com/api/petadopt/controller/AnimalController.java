/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.controller;

/**
 *
 * @author vanes
 */
import com.api.petadopt.data.AnimalEntity;
import com.api.petadopt.data.AdotanteEntity;
import com.api.petadopt.service.AnimalService;
import com.api.petadopt.exception.ResourceNotFoundException;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/animais")
public class AnimalController {
    @Autowired
    AnimalService animalService;

    @GetMapping("/listar-animais") 
    public ResponseEntity<List> getAllAnimais() { 
        
        List<AnimalEntity> animais = animalService.listarTodosAnimais(); 

        return ResponseEntity.ok(animais); 
    } 

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<AnimalEntity> getAnimalById(@PathVariable Integer id) { 
        try {
            AnimalEntity animal = animalService.getAnimalId(id);
            return ResponseEntity.ok(animal);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @PostMapping("/cadastrar-animal") 
    public ResponseEntity<AnimalEntity> addAnimal(@Valid @RequestBody AnimalEntity animal) { 

        AnimalEntity novoAnimal = animalService.criarAnimal(animal); 

        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal); 
    } 

    @PutMapping("/atualizar-animal/{id}") 
    public ResponseEntity<AnimalEntity> atualizarAnimal(@PathVariable Integer id, @Valid @RequestBody AnimalEntity animal) {
        try {
            AnimalEntity animalAtualizado = animalService.atualizarAnimal(id, animal);
            return ResponseEntity.ok(animalAtualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @DeleteMapping("/deletar-animal/{id}") 
    public ResponseEntity deletarAnimal(@PathVariable Integer id) {
        if (animalService.deletarAnimal(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } 
    
    @GetMapping("/pesquisar-nome/{nome}")
    public ResponseEntity<List> getPesquisarAnimalPorNome(@PathVariable String nome) { 
        try {
            List<AnimalEntity> animais = animalService.getAnimalPorNome(nome);
            return ResponseEntity.ok(animais);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-especie/{especie}")
    public ResponseEntity<List> getPesquisarAnimalPorEspecie(@PathVariable String especie) {
        try {
            List<AnimalEntity> animais = animalService.getAnimalPorEspecie(especie);
            return ResponseEntity.ok(animais);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-raca/{raca}")
    public ResponseEntity<List> getPesquisarAnimalPorRaca(@PathVariable String raca) {
        try {
            List<AnimalEntity> animais = animalService.getAnimalPorRaca(raca);
            return ResponseEntity.ok(animais);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
