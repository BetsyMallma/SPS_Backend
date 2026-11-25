package com.iestp.sps.dao;
import com.iestp.sps.modelo.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GeneroRepository extends JpaRepository<Genero, Long> {

	Optional<Genero> findByNombre(String nombre);

	List<Genero> findAllByOrderByNombreAsc();
}
