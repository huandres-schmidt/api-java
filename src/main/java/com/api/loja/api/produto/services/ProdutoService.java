package com.api.loja.api.produto.services;

import com.api.loja.api.produto.dtos.ProdutoDto;
import com.api.loja.api.produto.models.ProdutoModel;
import com.api.loja.api.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {
    final private ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel create(ProdutoDto dto) {
        ProdutoModel produto = new ProdutoModel();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listar() {
        return produtoRepository.findAll();
    }

    public ProdutoModel atualizar(ProdutoDto dto, UUID id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        return produtoRepository.save(produto);
    }

    public void deletar(UUID id) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        produtoRepository.delete(produto);
    }

    public List<ProdutoModel> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
