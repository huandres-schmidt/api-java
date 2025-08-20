package com.api.loja.api.loja.services;

import com.api.loja.api.loja.dtos.LojaDto;
import com.api.loja.api.loja.models.LojaModel;
import com.api.loja.api.loja.repository.LojaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LojaService {
    final private LojaRepository lojaRepository;
    public LojaService(LojaRepository lojaRepository) {this.lojaRepository = lojaRepository;}

    public LojaModel create(LojaDto dto) {
        LojaModel loja = new LojaModel();

        loja.setNome(dto.getNome());
        loja.setCnpj(dto.getCnpj());

        return lojaRepository.save(loja);
    }

    public List<LojaModel> listar() {
        return lojaRepository.findAll();
    }

    public LojaModel atualizar(LojaDto dto, UUID id) {
        var lojaEditar = lojaRepository.findById(id).orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        lojaEditar.setNome(dto.getNome());
        lojaEditar.setCnpj(dto.getCnpj());

        return lojaRepository.save(lojaEditar);
    }

    public void deletar(UUID id) {
        var lojaDeletar = lojaRepository.findById(id).orElseThrow(() -> new RuntimeException("Loja não encontrada"));

        lojaRepository.delete(lojaDeletar);
    }
}
