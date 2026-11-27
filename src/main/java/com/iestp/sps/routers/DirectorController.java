package com.iestp.sps.routers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.iestp.sps.modelo.Director;
import com.iestp.sps.schemas.DirectorDTO;
import com.iestp.sps.servicio.DirectorService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/directores")
public class DirectorController {
	private final DirectorService directorService;

	public DirectorController(DirectorService directorService) {
		this.directorService = directorService;
	}

	//listar todo
	@GetMapping("/")
	public List<DirectorDTO> listar() {
		return directorService.listar()
				.stream()
				.map(DirectorDTO::fromEntity)
				.collect(Collectors.toList());
	}

	//listar director especifico
	@GetMapping("/{id}")
	public DirectorDTO obtenerPorId(@PathVariable Long id) {
		return DirectorDTO.fromEntity(directorService.obtenerPorId(id));
	}

	//crear director
	@PostMapping("/")
	public ResponseEntity<Director> crear(@RequestBody Director director) {
		Director creado = directorService.crear(director);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	//modificar director
	@PutMapping("/{id}")
	public Director actualizar(@PathVariable Long id, @RequestBody Director datos) {
		return directorService.actualizar(id, datos);
	}

	//eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		directorService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

} //fin de clase
