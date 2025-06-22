/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.controller;

/**
 *
 * @author vanes
 */
import com.api.petadopt.data.AdotanteEntity;
import com.api.petadopt.service.AdotanteService;
import com.api.petadopt.exception.ResourceNotFoundException;
import java.util.Collections;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/adotantes")
public class AdotanteController {
    @Autowired
    AdotanteService adotanteService;

    @GetMapping("/listar-adotantes") 
    public ResponseEntity<List> getAllAdotantes() { 
        
        List<AdotanteEntity> adotantes = adotanteService.listarTodosAdotantes(); 

        return ResponseEntity.ok(adotantes); 
    } 

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<AdotanteEntity> getAdotanteById(@PathVariable Integer id) { 
        try {
            AdotanteEntity adotante = adotanteService.getAdotanteId(id);
            return ResponseEntity.ok(adotante);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @PostMapping("/cadastrar-adotante") 
    public ResponseEntity<AdotanteEntity> addAdotante(@Valid @RequestBody AdotanteEntity adotante) { 

        AdotanteEntity novoAdotante = adotanteService.criarAdotante(adotante); 

        return ResponseEntity.status(HttpStatus.CREATED).body(novoAdotante); 
    } 

    @PutMapping("/atualizar-adotante/{id}") 
    public ResponseEntity<AdotanteEntity> atualizarAdotante(@PathVariable Integer id, @Valid @RequestBody AdotanteEntity adotante) {
        try {
            AdotanteEntity adotanteAtualizado = adotanteService.atualizarAdotante(id, adotante);
            return ResponseEntity.ok(adotanteAtualizado);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @DeleteMapping("/deletar-adotante/{id}") 
    public ResponseEntity deletarAdotante(@PathVariable Integer id) {
        if (adotanteService.deletarAdotante(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } 
    
    @GetMapping("/pesquisar-nome/{nome}")
    public ResponseEntity<List> getPesquisarAdotantePorNome(@PathVariable String nome) { 
        try {
            List<AdotanteEntity> adotantes = adotanteService.getAdotantePorNome(nome);
            return ResponseEntity.ok(adotantes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-cpf/{cpf}")
    public ResponseEntity<List> getPesquisarAdotantePorCpf(@PathVariable String cpf) { 
        try {
            List<AdotanteEntity> adotantes = adotanteService.getAdotantePorCpf(cpf);
            return ResponseEntity.ok(adotantes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-bairro/{bairro}")
    public ResponseEntity<List> getPesquisarAdotantePorBairro(@PathVariable String bairro) {
        try {
            List<AdotanteEntity> adotantes = adotanteService.getAdotantePorBairro(bairro);
            return ResponseEntity.ok(adotantes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-cidade/{cidade}")
    public ResponseEntity<List> getPesquisarAdotantePorCidade(@PathVariable String cidade) {
        try {
            List<AdotanteEntity> adotantes = adotanteService.getAdotantePorCidade(cidade);
            return ResponseEntity.ok(adotantes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-estado/{estado}")
    public ResponseEntity<List> getPesquisarAdotantePorEstado(@PathVariable String estado) {
        try {
            List<AdotanteEntity> adotantes = adotanteService.getAdotantePorEstado(estado);
            return ResponseEntity.ok(adotantes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @GetMapping("/pesquisar-cep/{cep}")
    public ResponseEntity<List> getPesquisarAdotantePorCep(@PathVariable String cep) {
        try {
            List<AdotanteEntity> adotantes = adotanteService.getAdotantePorCep(cep);
            return ResponseEntity.ok(adotantes);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
