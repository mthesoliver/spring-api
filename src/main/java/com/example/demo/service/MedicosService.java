package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Medicos;
import com.example.demo.repository.MedicosRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MedicosService {

	private MedicosRepository medRepository;
	
	public ResponseEntity<Medicos> buscarPorId(Long id) {
		if(medRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.OK).body(medRepository.findById(id).get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public Page<Medicos> buscarTodos(PageRequest page) {
		return medRepository.findAll(page);
	}
	
	
	 public List<Medicos> listarTodosMedicos() {
	        return medRepository.findAll();
	    }
	 
	
	public ResponseEntity<Medicos> cadastrarMedicos(Medicos medicos) {
		Medicos medicosSalvo = medRepository.save(medicos);
		return ResponseEntity.status(HttpStatus.CREATED).body(medicosSalvo);
	}
	
	public ResponseEntity<Medicos> atualizarMedicos(Long id, Medicos medicos) {
		if(medRepository.existsById(id)) {
			medicos.setId(id);
			Medicos medicosSalvo = medRepository.save(medicos);
			return ResponseEntity.status(HttpStatus.OK).body(medicosSalvo);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> excluir(Long id) {
		if(medRepository.existsById(id)) {
			medRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Medico deletado com sucesso");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico não encontrado");
	}
	
}


