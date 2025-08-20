package com.api.loja.api.loja.repository;

import com.api.loja.api.loja.models.LojaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LojaRepository extends JpaRepository<LojaModel, UUID> {}
