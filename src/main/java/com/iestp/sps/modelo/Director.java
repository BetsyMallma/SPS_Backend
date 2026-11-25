package com.iestp.sps.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "director")
public class Director {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 60)
	private String nombre;

	@Column(nullable = false, length = 60)
	private String apellido;

	@Column(length = 60)
	private String nacionalidad;

	public Director() { }

	public Director(String nombre, String apellido, String nacionalidad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getApellido() { return apellido; }
	public void setApellido(String apellido) { this.apellido = apellido; }

	public String getNacionalidad() { return nacionalidad; }
	public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

}
