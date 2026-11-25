package com.iestp.sps.modelo;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "titulo")
public class Titulo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String titulo;

	// PELICULA o SERIE
	@Column(nullable = false, length = 10)
	private String tipo;

	@Column(nullable = false)
	private Integer anio;

	// Es opcional: un titulo puede crearse sin calificar todavia
	@Column(precision = 3, scale = 1)
	private BigDecimal calificacion;

	// VISTO o PENDIENTE
	@Column(nullable = false, length = 10)
	private String estado;

	@ManyToOne
	@JoinColumn(name = "id_genero", nullable = false)
	private Genero genero;

	@ManyToOne
	@JoinColumn(name = "id_director", nullable = false)
	private Director director;

	public Titulo() { }

	public Titulo(String titulo, String tipo, Integer anio, BigDecimal calificacion, Genero genero, Director director) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.anio = anio;
		this.calificacion = calificacion;
		this.estado = "PENDIENTE";  // por defecto arranca como pendiente
		this.genero = genero;
		this.director = director;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }

	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }

	public Integer getAnio() { return anio; }
	public void setAnio(Integer anio) { this.anio = anio; }

	public BigDecimal getCalificacion() { return calificacion; }
	public void setCalificacion(BigDecimal calificacion) { this.calificacion = calificacion; }

	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }

	public Genero getGenero() { return genero; }
	public void setGenero(Genero genero) { this.genero = genero; }

	public Director getDirector() { return director; }
	public void setDirector(Director director) { this.director = director; }

}
