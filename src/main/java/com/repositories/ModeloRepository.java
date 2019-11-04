package com.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Domain.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository <Modelo, Integer>{
	Optional<Modelo> findByDescricao(String descricao);
}
