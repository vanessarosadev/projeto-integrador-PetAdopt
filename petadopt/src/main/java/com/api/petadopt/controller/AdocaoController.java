/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.controller;

/**
 *
 * @author vanes
 */
import com.api.petadopt.data.AdocaoEntity;
import com.api.petadopt.service.AdocaoService;
import com.api.petadopt.exception.ResourceNotFoundException;
import java.util.List;
import java.time.LocalDate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {
    @Autowired
    AdocaoService adocaoService;

    @GetMapping("/listar-adocoes") 
    public ResponseEntity<List> getAllAdocoes() { 
        
        List<AdocaoEntity> adocoes = adocaoService.listarTodasAdocoes(); 

        return ResponseEntity.ok(adocoes); 
    } 

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<AdocaoEntity> getAdocaoById(@PathVariable Integer id) { 
        try {
            AdocaoEntity adocao = adocaoService.getAdocaoId(id);
            return ResponseEntity.ok(adocao);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @PostMapping("/registrar-adocao") 
    public ResponseEntity<AdocaoEntity> addAdocao(@Valid @RequestBody AdocaoEntity adocao) { 

        AdocaoEntity novaAdocao = adocaoService.registrarAdocao(adocao); 

        return ResponseEntity.status(HttpStatus.CREATED).body(novaAdocao); 
    } 

    @PutMapping("/atualizar-adocao/{id}") 
    public ResponseEntity<AdocaoEntity> atualizarAdocao(@PathVariable Integer id, @Valid @RequestBody AdocaoEntity adocao) {
        try {
            AdocaoEntity adocaoAtualizada = adocaoService.atualizarAdocao(id, adocao);
            return ResponseEntity.ok(adocaoAtualizada);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } 

    @DeleteMapping("/deletar-adocao/{id}") 
    public ResponseEntity deletarAdocao(@PathVariable Integer id) {
        if (adocaoService.deletarAdocao(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    } 

}
