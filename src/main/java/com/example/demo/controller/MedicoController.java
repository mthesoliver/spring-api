package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Medicos;
import com.example.demo.repository.MedicosRepository;
import com.example.demo.service.MedicosService;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("medicos")
@AllArgsConstructor
public class MedicoController {
	
	private MedicosService medService;
	@Autowired
	private MedicosRepository medRepository;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Medicos> buscarPorId(@PathVariable Long id) {
		return medService.buscarPorId(id);
	}
	
	@GetMapping
	public Page<Medicos> buscarTodos(@RequestParam(defaultValue = "0") Integer pagina, @RequestParam(defaultValue = "10") Integer itensPorPagina) {
		return medService.buscarTodos(PageRequest.of(pagina, itensPorPagina));
	}
	
	@GetMapping(value = "/todos")
    public List<Medicos> list() {
        return medService.listarTodosMedicos();
    }
	
	@GetMapping("/especialidade")
	public List<Medicos> getMedicosPorEspecialidade(@RequestParam String especialidade) {
	    List<Medicos> medicos = medRepository.findMedicosPorEspecialidade(especialidade);
	    return medicos;
	}
	
	@PostMapping
	public ResponseEntity<Medicos> cadastrarMedicos(@RequestBody Medicos medicos) {
		return medService.cadastrarMedicos(medicos);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Medicos> atualizarMedicos(@PathVariable Long id, @RequestBody Medicos medicos) {
		return medService.atualizarMedicos(id, medicos);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		return medService.excluir(id);
	}

}
