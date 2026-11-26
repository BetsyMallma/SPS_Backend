package com.iestp.sps.dao;
import com.iestp.sps.modelo.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TituloRepository extends JpaRepository<Titulo, Long> {

	// para detectar un titulo repetido: mismo titulo + mismo director + mismo anio
	Optional<Titulo> findByTituloAndDirector_IdAndAnio(String titulo, Long directorId, Integer anio);

	List<Titulo> findAllByOrderByTituloAsc();

	// para saber si un genero o un director todavia tiene titulos asociados
	List<Titulo> findByGenero_Id(Long generoId);

	List<Titulo> findByDirector_Id(Long directorId);
}
