package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Medicos;

@Repository
public interface MedicosRepository extends JpaRepository<Medicos, Long> {

	@Query("SELECT m FROM Medicos m JOIN m.especialidades e WHERE e.especialidade = :especialidade")
	List<Medicos> findMedicosPorEspecialidade(@Param("especialidade") String especialidade);

	
}