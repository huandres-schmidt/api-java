package com.api.loja.api.produto.controllers;

import com.api.loja.api.produto.dtos.ProdutoDto;
import com.api.loja.api.produto.models.ProdutoModel;
import com.api.loja.api.produto.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody @Valid ProdutoDto dto) {
        ProdutoModel produtoSalvo = produtoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping("/listar")
    public List<ProdutoModel> listar() {
        return produtoService.listar();
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(@RequestBody @Valid ProdutoDto dto, @PathVariable(value = "id") UUID id) {
        try {
          ProdutoModel produtoEditar = produtoService.atualizar(dto, id);
          return ResponseEntity.status(HttpStatus.CREATED).body(produtoEditar);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro");
        }
    }

    @PostMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        try {
            produtoService.deletar(id);
            return ResponseEntity.ok("Produto foi deletado com sucesso");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro:" + e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public List<ProdutoModel> buscar(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome);
    }
}
