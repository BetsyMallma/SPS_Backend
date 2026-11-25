package com.iestp.sps.modelo;
import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class Genero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 80)
	private String nombre;

	@Column(length = 200)
	private String descripcion;

	public Genero() { }  // constructor vacío: Hibernate lo necesita para crear objetos al leer filas

	public Genero(String nombre, String descripcion) {
		// constructor "de negocio": no recibe id, porque lo asigna la base de datos al guardar
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

}
