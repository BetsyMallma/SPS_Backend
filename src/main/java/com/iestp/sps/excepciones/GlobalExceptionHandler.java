package com.iestp.sps.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.iestp.sps.schemas.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// El Frontend lee el mensaje de error en el campo "detail", por eso se devuelve un ErrorDTO

	// 404: lo que se busca no existe
	@ExceptionHandler({GeneroNoEncontradoException.class,
		DirectorNoEncontradoException.class,
		TituloNoEncontradoException.class})
	public ResponseEntity<ErrorDTO> manejarNoEncontrado(RuntimeException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(ex.getMessage()));
	}

	// 400: el registro ya existe
	@ExceptionHandler({GeneroDuplicadoException.class,
		DirectorDuplicadoException.class,
		TituloDuplicadoException.class})
	public ResponseEntity<ErrorDTO> manejarDuplicado(RuntimeException ex){
		return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage()));
	}

	// 409: no se puede eliminar porque tiene titulos asociados
	@ExceptionHandler({GeneroConTitulosException.class,
		DirectorConTitulosException.class})
	public ResponseEntity<ErrorDTO> manejarConTitulos(RuntimeException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO(ex.getMessage()));
	}

	// 400: cualquier otro error de validacion (anio invalido, calificacion invalida, etc.)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDTO> manejarErrorGenerico(RuntimeException ex){
		return ResponseEntity.badRequest().body(new ErrorDTO(ex.getMessage()));
	}

}
