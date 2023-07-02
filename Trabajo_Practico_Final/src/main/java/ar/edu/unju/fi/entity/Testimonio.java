package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import jakarta.persistence.Entity;
/**
 * Respresenta un Testimonio
 */
@Component
@Entity
public class Testimonio {
	private Long id;
	private LocalDate fecha;
	private Usuario usuario;
	private String comentario;
	private boolean estado;
	
	public Testimonio() {
		// TODO Auto-generated constructor stub
	}
	/**
     * Constructor de la entidad Testimonio que recibe todos los parámetros.
     * @param id: el identificador del testimonio.
     * @param fecha: la fecha del testimonio.
     * @param usuario: el usuario que hizo el testimonio.
     * @param comentario: el comentario del testimonio.
     * @param estado: el estado del testimonio.
     */
	public Testimonio(Long id, LocalDate fecha, Usuario usuario, String comentario, boolean estado) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.usuario = usuario;
		this.comentario = comentario;
		this.estado = estado;
	}
	 /**
     * Obtiene el id del testimonio.
     * @return el id del testimonio.
     */
	public Long getId() {
		return id;
	}
	/**
     * Establece el identificador del testimonio.
     * @param id: identificador del testimonio.
     */
	public void setId(Long id) {
		this.id = id;
	}
	/**
     * Obtiene la fecha del testimonio.
     * @return la fecha del testimonio.
     */
	public LocalDate getFecha() {
		return fecha;
	}
	/**
     * Establece la fecha del testimonio.
     * @param fecha: la fecha que se realizó testimonio.
     */
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

    /**
     * Obtiene el usuario que hizo el testimonio.
     * @return el usuario del testimonio.
     */
	public Usuario getUsuario() {
		return usuario;
	}
	/**
     * Establece el usuario del testimonio.
     * @param usuario: usuario que realizó testimonio.
     */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	/**
     * Obtiene el comentario del testimonio.
     * @return el comentario del testimonio.
     */
	public String getComentario() {
		return comentario;
	}
	/**
     * Establece el comentario del testimonio.
     * @param comentario: el comentario ingresado por el usuario.
     */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
     * Verifica el estado del testimonio.
     * @return el estado, si el testimonio está activo o inactivo.
     */
	public boolean isEstado() {
		return estado;
	}
	/**
     * Establece el estado del testimonio.
     * @param estado: estado del testimonio.
     */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	

}
