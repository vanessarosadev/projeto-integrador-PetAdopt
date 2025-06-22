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
import com.api.petadopt.data.AnimalEntity;
public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {
    Optional<AnimalEntity> findById(Integer id); 
    Optional<AnimalEntity> findByNome(String nome);
    List<AnimalEntity> findByEspecie(String especie);
    List<AnimalEntity> findByGenero(String genero);
    List<AnimalEntity> findByRaca(String raca);
    List<AnimalEntity> findByPorte(String porte);
    List<AnimalEntity> findByCor(String cor);
    List<AnimalEntity> findByTemperamento(String temperamento);
    List<AnimalEntity> findByNomeContaining(String nome);
}
