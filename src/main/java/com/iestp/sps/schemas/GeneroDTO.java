package com.iestp.sps.schemas;

import com.iestp.sps.modelo.Genero;

public class GeneroDTO {
	private Long id;
	private String nombre;
	private String descripcion;

	public static GeneroDTO fromEntity(Genero genero) {
		GeneroDTO dto = new GeneroDTO();
		dto.id = genero.getId();
		dto.nombre = genero.getNombre();
		dto.descripcion = genero.getDescripcion();
		return dto;
	}

	public Long getId() { return id; }
	public String getNombre() { return nombre; }
	public String getDescripcion() { return descripcion; }
}
