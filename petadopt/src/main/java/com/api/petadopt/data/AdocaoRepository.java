/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.api.petadopt.data;

/**
 *
 * @author vanes
 */
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.api.petadopt.data.AdocaoEntity;

public interface AdocaoRepository extends JpaRepository<AdocaoEntity, Integer> {
    List<AdocaoEntity> findByAdotanteId(Integer adotanteId);
    
    List<AdocaoEntity> findByAnimalId(Integer animalId);
    
    List<AdocaoEntity> findByAnimalNomeContainingIgnoreCase(String animalNome);

    List<AdocaoEntity> findByAnimalEspecieContainingIgnoreCase(String animalEspecie);

    List<AdocaoEntity> findByAdotanteNomeContainingIgnoreCase(String adotanteNome);
}
