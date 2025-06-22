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
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name="adotante")
public class AdotanteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome não pode ultrapassar 255 caracteres")
    private String nome;

    @NotNull(message = "O RG é obrigatório")
    @Digits(integer = 10, fraction = 0, message = "O RG deve conter no máximo 10 dígitos")
    private Integer rg;

    @NotNull(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos")
    private String cpf;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    private LocalDate nascimento;

    @NotNull(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos")
    private String telefone;

    @NotBlank(message = "O logradouro é obrigatório")
    @Size(max = 255, message = "O logradouro não pode ultrapassar 255 caracteres")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatório")
    @Size(max = 100, message = "O bairro não pode ultrapassar 100 caracteres")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(max = 100, message = "A cidade não pode ultrapassar 100 caracteres")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    @Size(max = 50, message = "O estado não pode ultrapassar 50 caracteres")
    private String estado;

    @NotNull(message = "O CEP é obrigatório")
    @Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos")
    private String cep;
}
