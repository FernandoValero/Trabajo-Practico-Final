package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Component

@Entity
/*
 * Define a la clase Sucursal como una entidad
 */

@Table(name="recetas")
/*
 * Asigna el nombre "sucursales" a una tabla
 */

public class Receta {
	@Id
	/*
	 * Indica la clave principal de la entidad
	 */
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	/*
	 * Genera el ID automaticamente
	 */
	
	@Column(name="id_receta")
	/*
	 * La anotacion @Column se usa para asignar un nombre a la columna de la BD
	 */
	private Long id;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Ingrediente> ingredientes;
	
	@Column(name="nombre_receta")
	@NotEmpty(message="*Nombre vacío")
	@Size(min=4,message="*Nombre muy corto")
	private String nombre;
	
	@Column(name="categoria_receta")
	@NotEmpty(message="*Categoria vacía")
	private String categoria;
	
	@Column(name="ingrediente_receta")
	@NotEmpty(message="*Ingrediente vacío")
	private String ingrediente;
	
	@Column(name="preparacion_receta")
	@Size(min=10,message="*Preparacion muy corto")
	@NotEmpty(message="*Preparacion vacío")
	private String preparacion;
	
	@Column(name="imagen_receta")
	private String imagen;
	
	@Column(name="estado_receta")
	private boolean estado;
	
	/**
	 * Constructor de la entidad Receta
	 */
	public Receta() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 *Constructor sobrecargado de la entidad Receta
	 *@param id, valor a asignar al  id de Receta
	 *@param nombre, valor a asignar al  Nombre de Receta
	 *@param categoria, valor a asignar a la Categoria de Receta
	 *@param ingrediente, valor a asignar a el Ingrediente de Receta
	 *@param preparacion, valor a asignar a la preparacion de Receta
	 *@param imagen, imagen a asignar para la Receta
	 *@param estado, valor a asignar al  Estado de la Receta
	 */
	public Receta(Long id, String nombre, String categoria, String ingrediente, String preparacion, String imagen, boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.ingrediente = ingrediente;
		this.preparacion = preparacion;
		this.imagen = imagen;
		this.estado = estado;
	}


	/**
	 * Permite la salida del ID 
	 * @return retorna el ID de la sucursal
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Permite la entrada del atributo ID
	 * @param id, valor a asignar al ID del objeto
	 */
	public void setId(Long id) {
		this.id = id;
	}

	
	/**
	 * Permite la salida del nombre
	 * @return retorna el Nombre de la receta
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Permite la entrada del atributo nombre
	 * @param nombre, valor a asignar al  Nombre de Receta
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	/**
	 * Permite la salida de la categoria
	 @return retorna la categoria de la receta
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * Permite la entrada del atributo categoria
	 * @param categoria, valor a asignar a la categoria de la receta
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	/**
	 * Permite la salida del ingrediente
	 @return retorna el ingrediente de la receta
	 */
	public String getIngrediente() {
		return ingrediente;
	}
	/**
	 * Permite la entrada del atributo ingrediente
	 * @param ingrediente, valor a asignar al ingrediente de la receta
	 */
	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	/**
	 * Permite la salida de la preparacion
	 @return retorna la preparacion de la receta
	 */
	public String getPreparacion() {
		return preparacion;
	}
	/**
	 * Permite la entrada del atributo preparacion
	 * @param preparacion, valor a asignar a la preparacion de la sucursal
	 */
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}

	
	/**
	 * Permite la salida de la imagen
	 @return retorna la imagen de la receta
	 */
	public String getImagen() {
		return imagen;
	}
	/**
	 * Permite la entrada del atributo imagen
	 * @param imagen, imagen a asignar a la receta
	 */
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	
	/**
	 * Permite la salida del estado
	 * @return retorna el estado de la receta
	 */
	public boolean isEstado() {
		return estado;
	}
	/**
	 * Permite la entrada del atributo estado
	 * @param estado, valor a asignar al  estado del objeto
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
















