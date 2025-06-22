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

}
