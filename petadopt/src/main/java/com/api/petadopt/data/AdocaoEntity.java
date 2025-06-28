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
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name="adocao")
public class AdocaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "O animal é obrigatório")
    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private AnimalEntity animal;

    @NotNull(message = "O adotante é obrigatório")
    @ManyToOne
    @JoinColumn(name = "adotante_id", nullable = false)
    private AdotanteEntity adotante;

    @NotNull(message = "A data de adoção é obrigatória")
    @PastOrPresent(message = "A data de adoção deve estar no passado ou presente")
    private LocalDate dataAdocao;

    @Column(length = 255)
    private String observacao;
    
    public String getDataAdocaoFormat() {
        return dataAdocao != null ? dataAdocao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : "Data não informada";
    }
}
