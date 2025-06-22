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

}
