package com.iestp.sps.dao;
import com.iestp.sps.modelo.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface DirectorRepository extends JpaRepository<Director, Long> {

	Optional<Director> findByNombreAndApellido(String nombre, String apellido);

	List<Director> findAllByOrderByApellidoAsc();
}
