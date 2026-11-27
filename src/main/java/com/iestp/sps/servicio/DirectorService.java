package com.iestp.sps.servicio;

import com.iestp.sps.dao.DirectorRepository;
import com.iestp.sps.dao.TituloRepository;
import com.iestp.sps.modelo.Director;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.iestp.sps.excepciones.DirectorNoEncontradoException;
import com.iestp.sps.excepciones.DirectorDuplicadoException;
import com.iestp.sps.excepciones.DirectorConTitulosException;

@Service
public class DirectorService {
	private final DirectorRepository directorRepository;
	private final TituloRepository tituloRepository;

	public DirectorService(DirectorRepository directorRepository, TituloRepository tituloRepository) {
		this.directorRepository = directorRepository;
		this.tituloRepository = tituloRepository;
	}

	public List<Director> listar() {
		return directorRepository.findAllByOrderByApellidoAsc();
	}

	public Director obtenerPorId(Long id) {
		return directorRepository.findById(id)
				.orElseThrow(() -> new DirectorNoEncontradoException("Director ID=" + id + " no encontrado"));
	}

	@Transactional
	public Director crear(Director director) {

		if (director.getNombre() == null || director.getNombre().isBlank()
				|| director.getApellido() == null || director.getApellido().isBlank()) {
			throw new RuntimeException("El nombre y el apellido del director son obligatorios");
		}

		if (directorRepository.findByNombreAndApellido(director.getNombre(), director.getApellido()).isPresent()) {
			throw new DirectorDuplicadoException("Director '" + director.getNombre() + " " + director.getApellido() + "' ya registrado");
		}

		return directorRepository.save(director);
	}

	@Transactional
	public Director actualizar(Long id, Director datos) {
		Director existente = obtenerPorId(id);

		// Si no se envía un valor nuevo, se conserva el que ya tenía
		if (datos.getNombre() != null) {
			existente.setNombre(datos.getNombre());
		}
		if (datos.getApellido() != null) {
			existente.setApellido(datos.getApellido());
		}
		if (datos.getNacionalidad() != null) {
			existente.setNacionalidad(datos.getNacionalidad());
		}
		return directorRepository.save(existente);
	}

	@Transactional
	public void eliminar(Long id) {
		Director existente = obtenerPorId(id);

		// No se puede eliminar un director que todavia tiene titulos
		if (!tituloRepository.findByDirector_Id(id).isEmpty()) {
			throw new DirectorConTitulosException("Director ID=" + id + " no se puede eliminar: tiene titulos asociados");
		}
		directorRepository.delete(existente);
	}

} //fin de DirectorService
