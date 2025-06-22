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
import com.api.petadopt.data.AdotanteEntity;
public interface AdotanteRepository extends JpaRepository<AdotanteEntity, Integer> {
    Optional<AdotanteEntity> findById(Integer id); 
    Optional<AdotanteEntity> findByNome(String nome);
    Optional<AdotanteEntity> findByCpf(String cpf);
    List<AdotanteEntity> findByBairro(String bairro);
    List<AdotanteEntity> findByCidade(String cidade);
    List<AdotanteEntity> findByEstado(String estado);
    List<AdotanteEntity> findByCep(String cep);
    List<AdotanteEntity> findByNomeContaining(String nome);
}
