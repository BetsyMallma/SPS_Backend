package com.iestp.sps.routers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.iestp.sps.modelo.Genero;
import com.iestp.sps.schemas.GeneroDTO;
import com.iestp.sps.servicio.GeneroService;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/generos")
public class GeneroController {
	private final GeneroService generoService;

	public GeneroController(GeneroService generoService) {
		this.generoService = generoService;
	}

	//listar todo
	@GetMapping("/")
	public List<GeneroDTO> listar() {
		return generoService.listar()
				.stream()
				.map(GeneroDTO::fromEntity)
				.collect(Collectors.toList());
	}

	//listar genero especifico
	@GetMapping("/{id}")
	public GeneroDTO obtenerPorId(@PathVariable Long id) {
		return GeneroDTO.fromEntity(generoService.obtenerPorId(id));
	}

	//crear genero
	@PostMapping("/")
	public ResponseEntity<Genero> crear(@RequestBody Genero genero) {
		Genero creado = generoService.crear(genero);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}

	//modificar genero
	@PutMapping("/{id}")
	public Genero actualizar(@PathVariable Long id, @RequestBody Genero datos) {
		return generoService.actualizar(id, datos);
	}

	//eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		generoService.eliminar(id);
		return ResponseEntity.noContent().build();
	}

} //fin de clase
