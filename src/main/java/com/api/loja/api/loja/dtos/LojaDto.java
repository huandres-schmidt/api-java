package com.api.loja.api.loja.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LojaDto {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    @NotBlank(message = "Cnpj é obrigatório")
    private String cnpj;
}
