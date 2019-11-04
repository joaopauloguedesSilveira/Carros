package com.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Domain.Carros;


@Repository
public interface CarroRepository extends JpaRepository <Carros, Integer >{

	
	public Optional<Carros> findByOdometroGreaterThan(double km);
	public Optional<Carros> findByDiariaLessThan(double diaria);
	public Optional<Carros> findByAnoBetween(Integer anoIni, Integer anoFim);
}
