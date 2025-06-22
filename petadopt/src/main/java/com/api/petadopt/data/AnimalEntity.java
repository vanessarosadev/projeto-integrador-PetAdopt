/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.api.petadopt.data;

/**
 *
 * @author vanes
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name="animal")
public class AnimalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message="O nome é obrigatório")
    @Size(max = 100, message = "O nome não pode ultrapassar 100 caracteres")
    private String nome;

    @NotBlank(message="A especie é obrigatória")
    @Size(max = 100, message = "A especie não pode ultrapassar 100 caracteres")
    private String especie;
    
    @NotBlank(message="O genero é obrigatório")
    @Size(max = 50, message = "O genero não pode ultrapassar 50 caracteres")
    private String genero;
    
    @NotBlank(message="A raca é obrigatória")
    @Size(max = 100, message = "A raca não pode ultrapassar 100 caracteres")
    private String raca; 
    
    @NotBlank(message="O porte é obrigatório")
    @Size(max = 50, message = "O porte não pode ultrapassar 50 caracteres")
    private String porte; 
    
    @NotBlank(message="A cor é obrigatória") 
    @Size(max = 50, message = "A cor não pode ultrapassar 50 caracteres")
    private String cor; 
    
    @NotBlank(message="O temperamento é obrigatório")
    @Size(max = 100, message = "O temperamento não pode ultrapassar 100 caracteres")
    private String temperamento; 

    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate nascimento;

    @NotNull(message = "A data de chegada é obrigatória")
    @PastOrPresent(message = "A data de chegada deve estar no passado ou no presente")
    private LocalDate chegada;
}
