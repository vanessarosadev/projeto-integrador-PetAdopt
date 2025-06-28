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
import com.api.petadopt.data.AdocaoEntity;
import com.api.petadopt.service.AnimalService;
import com.api.petadopt.service.AdotanteService;
import com.api.petadopt.service.AdocaoService;
import com.api.petadopt.exception.ResourceNotFoundException;
import java.util.Collections;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

@Controller
public class FrontController {
    @Autowired
    AnimalService animalService;
    
    @Autowired
    AdotanteService adotanteService;
    
    @Autowired
    AdocaoService adocaoService;
    
    @GetMapping({"/", "/index"})
    public String home() {
        return "index";
    }
    
    @GetMapping("/listar-animais") 
    public String listarTodosAnimais(@RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "nome", required = false) String nome,
                                @RequestParam(value = "especie", required = false) String especie,
                                @RequestParam(value = "genero", required = false) String genero,
                                @RequestParam(value = "raca", required = false) String raca,
                                @RequestParam(value = "disponivel", required = false) String disponivel,
                                Model model) {
        if (id != null) {
            try {
                model.addAttribute("listarTodosAnimais", List.of(animalService.getAnimalId(id)));
            } catch (ResourceNotFoundException e) {
                model.addAttribute("mensagemErro", "Animal não encontrado com o ID fornecido.");
                model.addAttribute("listarTodosAnimais", animalService.listarTodosAnimais());
            }
        } else if (nome != null && !nome.isEmpty()) {
            model.addAttribute("listarTodosAnimais", animalService.getAnimalPorNome(nome));
        } else if (especie != null && !especie.isEmpty()) {
            model.addAttribute("listarTodosAnimais", animalService.getAnimalPorEspecie(especie));
        } else if (genero != null && !genero.isEmpty()) {
            model.addAttribute("listarTodosAnimais", animalService.getAnimalPorGenero(genero));
        } else if (raca != null && !raca.isEmpty()) {
            model.addAttribute("listarTodosAnimais", animalService.getAnimalPorRaca(raca));
        } else if ("true".equals(disponivel)) {
            model.addAttribute("listarTodosAnimais", animalService.listarAnimaisDisponiveis());
        } else if ("false".equals(disponivel)) {
            model.addAttribute("listarTodosAnimais", animalService.listarAnimaisIndisponiveis());
        } else {
            model.addAttribute("listarTodosAnimais", animalService.listarTodosAnimais());
        }
        return "listar-animais"; 
    }
    
    @GetMapping("/listar-adotantes") 
    public String listarTodosAdotantes(@RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "cpf", required = false) String cpf,
                                @RequestParam(value = "nome", required = false) String nome,
                                @RequestParam(value = "bairro", required = false) String bairro,
                                @RequestParam(value = "cidade", required = false) String cidade,
                                @RequestParam(value = "estado", required = false) String estado,
                                @RequestParam(value = "cep", required = false) String cep,
                                Model model) {
        if (id != null) {
            try {
                model.addAttribute("listarTodosAdotantes", List.of(adotanteService.getAdotanteId(id)));
            } catch (ResourceNotFoundException e) {
                model.addAttribute("mensagemErro", "Adotante não encontrado com o ID fornecido.");
                model.addAttribute("listarTodosAdotantes", adotanteService.listarTodosAdotantes());
            }
        } else if (cpf != null && !cpf.isEmpty()) {
            try {
                model.addAttribute("listarTodosAdotantes", List.of(adotanteService.getAdotanteCpf(cpf)));
            } catch (ResourceNotFoundException e) {
                model.addAttribute("mensagemErro", "Adotante não encontrado com o CPF fornecido.");
                model.addAttribute("listarTodosAdotantes", adotanteService.listarTodosAdotantes());
            }
        } else if (nome != null && !nome.isEmpty()) {
            model.addAttribute("listarTodosAdotantes", adotanteService.getAdotantePorNome(nome));
        } else if (bairro != null && !bairro.isEmpty()) {
            model.addAttribute("listarTodosAdotantes", adotanteService.getAdotantePorBairro(bairro));
        } else if (cidade != null && !cidade.isEmpty()) {
            model.addAttribute("listarTodosAdotantes", adotanteService.getAdotantePorCidade(cidade));
        } else if (estado != null && !estado.isEmpty()) {
            model.addAttribute("listarTodosAdotantes", adotanteService.getAdotantePorEstado(estado));
        } else if (cep != null && !cep.isEmpty()) {
            model.addAttribute("listarTodosAdotantes", adotanteService.getAdotantePorCep(cep));
        } else {
            model.addAttribute("listarTodosAdotantes", adotanteService.listarTodosAdotantes());
        }
        return "listar-adotantes"; 
    }
    
    @GetMapping("/listar-adocoes") 
    public String listarTodasAdocoes(@RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "animalId", required = false) Integer animalId,
                                @RequestParam(value = "animalNome", required = false) String animalNome,
                                @RequestParam(value = "animalEspecie", required = false) String animalEspecie,
                                @RequestParam(value = "adotanteId", required = false) Integer adotanteId,
                                @RequestParam(value = "adotanteNome", required = false) String adotanteNome,
                                Model model) {
        if (id != null) {
            try {
                model.addAttribute("listarTodasAdocoes", List.of(adocaoService.getAdocaoId(id)));
            } catch (ResourceNotFoundException e) {
                model.addAttribute("mensagemErro", "Adoção não encontrada com o ID fornecido.");
                model.addAttribute("listarTodasAdocoes", adocaoService.listarTodasAdocoes());
            }
        } else if (animalId != null) {
            model.addAttribute("listarTodasAdocoes", adocaoService.listarAdocoesPorAnimal(animalId));
        } else if (animalNome != null && !animalNome.isEmpty()) {
            model.addAttribute("listarTodasAdocoes", adocaoService.listarAdocoesPorNomeAnimal(animalNome));
        } else if (animalEspecie != null && !animalEspecie.isEmpty()) {
            model.addAttribute("listarTodasAdocoes", adocaoService.listarAdocoesPorEspecieAnimal(animalEspecie));
        } else if (adotanteId != null) {
            model.addAttribute("listarTodasAdocoes", adocaoService.listarAdocoesPorAdotante(adotanteId));
        } else if (adotanteNome != null && !adotanteNome.isEmpty()) {
            model.addAttribute("listarTodasAdocoes", adocaoService.listarAdocoesPorNomeAdotante(adotanteNome));
        } else {
            model.addAttribute("listarTodasAdocoes", adocaoService.listarTodasAdocoes());
        }
        return "listar-adocoes"; 
    }
    
    @GetMapping("/exibir-animal/{id}") 
    public String exibirAnimal(@PathVariable Integer id, Model model) { 
        AnimalEntity animal = animalService.getAnimalId(id);
        model.addAttribute("animal", animal);
        return "exibir-animal";
    }

    @GetMapping("/exibir-adotante/{id}") 
    public String exibirAdotante(@PathVariable Integer id, Model model) { 
        AdotanteEntity adotante = adotanteService.getAdotanteId(id);
        model.addAttribute("adotante", adotante);
        return "exibir-adotante";
    }
    
    @GetMapping("/exibir-adocao/{id}") 
    public String exibirAdocao(@PathVariable Integer id, Model model) { 
        AdocaoEntity adocao = adocaoService.getAdocaoId(id);
        if (adocao.getAnimal() == null || adocao.getAdotante() == null) {
            throw new IllegalArgumentException("A adoção não possui informações completas de animal ou adotante.");
        }
        model.addAttribute("adocao", adocao);
        return "exibir-adocao";
    }
    
    @GetMapping("/deletar-animal/{id}") 
    public String deletarAnimal(@PathVariable(value = "id") Integer id, Model model) { 
        try {
            animalService.deletarAnimal(id);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("erro", "Animal não encontrado.");
            return "erro";
        }
        return "/listar-animais"; 
    } 
    
    @GetMapping("/deletar-adotante/{id}") 
    public String deletarAdotante(@PathVariable(value = "id") Integer id, Model model) { 
        try {
            adotanteService.deletarAdotante(id);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("erro", "Adotante não encontrado.");
            return "erro";
        }
        return "/listar-adotantes"; 
    } 

    @GetMapping("/deletar-adocao/{id}") 
    public String deletarAdocao(@PathVariable(value = "id") Integer id, Model model) { 
        try {
            adocaoService.deletarAdocao(id);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("erro", "Adocao não encontrada.");
            return "erro";
        }
        return "/listar-adocoes"; 
    } 
    
    @GetMapping("/cadastrar-animal-form") 
    public String cadastrarAnimalForm(Model model) { 
        AnimalEntity animal = new AnimalEntity(); 
        model.addAttribute("animal", animal); 
        return "cadastrar-animal"; 
    }
    
    @GetMapping("/atualizar-animal-form/{id}") 
    public String atualizarAnimalForm(@PathVariable(value = "id") Integer id, Model model) { 
        AnimalEntity animal = animalService.getAnimalId(id); 
        model.addAttribute("animal", animal); 
        return "cadastrar-animal"; 
    }
    
    @PostMapping("/cadastrar-animal") 
    public String cadastrarAnimal(@Valid @ModelAttribute("animal") AnimalEntity animal, BindingResult result, Model model) {     
        if (result.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro ao cadastrar o animal. Tente novamente.");
            return "cadastrar-animal"; 
        } 
        if (animal.getId()==null) { 
            animalService.criarAnimal(animal);        
        } else { 
            animalService.atualizarAnimal(animal.getId(), animal); 
        } 
        return "/exibir-animal"; 
    } 
    
    @GetMapping("/cadastrar-adotante-form") 
    public String cadastrarAdotanteForm(Model model) { 
        AdotanteEntity adotante = new AdotanteEntity(); 
        model.addAttribute("adotante", adotante); 
        return "cadastrar-adotante"; 
    }
    
    @GetMapping("/atualizar-adotante-form/{id}") 
    public String atualizarAdotanteForm(@PathVariable(value = "id") Integer id, Model model) { 
        AdotanteEntity adotante = adotanteService.getAdotanteId(id); 
        model.addAttribute("adotante", adotante); 
        return "cadastrar-adotante"; 
    }
    
    @PostMapping("/cadastrar-adotante") 
    public String cadastrarAdotante(@Valid @ModelAttribute("adotante") AdotanteEntity adotante, BindingResult result, Model model) {     
        if (result.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro ao cadastrar o adotante. Tente novamente.");
            return "cadastrar-adotante"; 
        } 
        if (adotante.getId()==null) { 
            adotanteService.criarAdotante(adotante);        
        } else { 
            adotanteService.atualizarAdotante(adotante.getId(), adotante); 
        } 
        return "/exibir-adotante"; 
    } 

    @GetMapping("/registrar-adocao-form") 
    public String registrarAdocaoForm(Model model) { 
        List<AnimalEntity> animais = animalService.listarAnimaisDisponiveis();
        model.addAttribute("animais", animais);

        List<AdotanteEntity> adotantes = adotanteService.listarTodosAdotantes();
        model.addAttribute("adotantes", adotantes);
        
        AdocaoEntity adocao = new AdocaoEntity(); 
        model.addAttribute("adocao", adocao); 
        return "registrar-adocao"; 
    }
    
    @GetMapping("/atualizar-adocao-form/{id}") 
    public String atualizarAdocaoForm(@PathVariable(value = "id") Integer id, Model model) { 
        AdocaoEntity adocao = adocaoService.getAdocaoId(id);
        model.addAttribute("adocao", adocao);

        List<AnimalEntity> animaisDisponiveis = animalService.listarAnimaisDisponiveis();
        if (adocao.getAnimal() != null && !animaisDisponiveis.contains(adocao.getAnimal())) {
            animaisDisponiveis.add(adocao.getAnimal());
        }
        model.addAttribute("animais", animaisDisponiveis);

        List<AdotanteEntity> adotantes = adotanteService.listarTodosAdotantes();
        model.addAttribute("adotantes", adotantes);

        return "registrar-adocao"; 
    }
    
    @PostMapping("/registrar-adocao") 
    public String registrarAdocao(@Valid @ModelAttribute("adocao") AdocaoEntity adocao, BindingResult result, Model model) {     
        if (result.hasErrors()) {
            model.addAttribute("mensagemErro", "Erro ao cadastrar a adoção. Tente novamente.");
            return "registrar-adocao"; 
        } 
        if (adocao.getId()==null) { 
            adocaoService.registrarAdocao(adocao);        
        } else { 
            adocaoService.atualizarAdocao(adocao.getId(), adocao); 
        } 
        return "/exibir-adocao"; 
    }
}
