package com.iestp.sps.servicio;

import com.iestp.sps.dao.GeneroRepository;
import com.iestp.sps.dao.TituloRepository;
import com.iestp.sps.modelo.Genero;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.iestp.sps.excepciones.GeneroNoEncontradoException;
import com.iestp.sps.excepciones.GeneroDuplicadoException;
import com.iestp.sps.excepciones.GeneroConTitulosException;

@Service
public class GeneroService {
	private final GeneroRepository generoRepository;
	private final TituloRepository tituloRepository;

	public GeneroService(GeneroRepository generoRepository, TituloRepository tituloRepository) {
		this.generoRepository = generoRepository;
		this.tituloRepository = tituloRepository;
	}

	public List<Genero> listar() {
		return generoRepository.findAllByOrderByNombreAsc();
	}

	public Genero obtenerPorId(Long id) {
		return generoRepository.findById(id)
				.orElseThrow(() -> new GeneroNoEncontradoException("Genero ID=" + id + " no encontrado"));
	}

	@Transactional
	public Genero crear(Genero genero) {

		if (genero.getNombre() == null || genero.getNombre().isBlank()) {
			throw new RuntimeException("El nombre del genero es obligatorio");
		}

		if (generoRepository.findByNombre(genero.getNombre()).isPresent()) {
			throw new GeneroDuplicadoException("Genero '" + genero.getNombre() + "' ya registrado");
		}

		return generoRepository.save(genero);
	}

	@Transactional
	public Genero actualizar(Long id, Genero datos) {
		Genero existente = obtenerPorId(id); // reutiliza la búsqueda de arriba

		// Si no se envía un valor nuevo, se conserva el que ya tenía
		if (datos.getNombre() != null) {
			existente.setNombre(datos.getNombre());
		}
		if (datos.getDescripcion() != null) {
			existente.setDescripcion(datos.getDescripcion());
		}
		return generoRepository.save(existente);
	}

	@Transactional
	public void eliminar(Long id) {
		Genero existente = obtenerPorId(id); // si no existe, lanza la excepción antes de intentar borrar

		// No se puede eliminar un genero que todavia tiene titulos
		if (!tituloRepository.findByGenero_Id(id).isEmpty()) {
			throw new GeneroConTitulosException("Genero ID = " + id + " no se puede eliminar: tiene titulos asociados");
		}
		generoRepository.delete(existente);
	}

} //fin de GeneroService
