package com.api.loja.api.loja.controllers;

import com.api.loja.api.loja.dtos.LojaDto;
import com.api.loja.api.loja.models.LojaModel;
import com.api.loja.api.loja.services.LojaService;
import com.api.loja.api.produto.dtos.ProdutoDto;
import com.api.loja.api.produto.models.ProdutoModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/loja")
public class LojaController {
    private final LojaService lojaService;

    public LojaController(LojaService lojaService) {this.lojaService = lojaService;}

    @PostMapping("/salvar-loja")
    public ResponseEntity<?> salvarLoja(@RequestBody LojaDto lojaDto) {
        LojaModel lojaSalva = lojaService.create(lojaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(lojaSalva);
    }

    @GetMapping("/listar-loja")
    public List<LojaModel> listar() {
        return lojaService.listar();
    }

    @PostMapping("/editar-loja/{id}")
    public ResponseEntity<?> editar(@RequestBody @Valid LojaDto lojaDto, @PathVariable(value = "id") UUID id) {
        try {
            LojaModel lojaEditar = lojaService.atualizar(lojaDto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(lojaEditar);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }

    @DeleteMapping("/deletar-loja/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        try {
            lojaService.deletar(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }
}
