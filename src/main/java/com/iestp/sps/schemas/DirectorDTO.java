package com.iestp.sps.schemas;

import com.iestp.sps.modelo.Director;

public class DirectorDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private String nacionalidad;

	public static DirectorDTO fromEntity(Director director) {
		DirectorDTO dto = new DirectorDTO();
		dto.id = director.getId();
		dto.nombre = director.getNombre();
		dto.apellido = director.getApellido();
		dto.nacionalidad = director.getNacionalidad();
		return dto;
	}

	public Long getId() { return id; }
	public String getNombre() { return nombre; }
	public String getApellido() { return apellido; }
	public String getNacionalidad() { return nacionalidad; }
}
