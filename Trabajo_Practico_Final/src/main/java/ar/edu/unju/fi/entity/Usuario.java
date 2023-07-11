package ar.edu.unju.fi.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

/**
 * Respresenta un Usuario
 */
@Component
@Entity
@Table(name= "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="usu_id")
	private Long id;
	
	@NotEmpty(message="*Escriba su Nombre")
	@Size(min=3,message="*Debe tener almenos 3 caracteres")
	@Column(name="usu_nombre")
	private String nombre;
	
	@NotEmpty(message="*Escriba su Apellido")
	@Size(min=3,message="*Debe tener almenos 3 caracteres")
	@Column(name="usu_apellido")
	private String apellido;
	
	@NotEmpty(message="*El E-mail No puede estar Vacio")
	@Email(message="*Debe de Ingresar un E-mail con formato valido")
	@Column(name="usu_email")
	private String email;
	
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@NotNull(message="*Debe de Ingresar una Fecha de Nacimiento")
	@Past(message="*La Fecha de Nacimiento debe de ser meno a la Fecha actual")
	@Column(name="usu_fecha_nacimineto")
	private LocalDate fechaNacimiento;
	
	@NotEmpty(message="*El telefono No puede estar Vacio")
	@Column(name="usu_telefono")
	private String telefono;
	
	@NotEmpty(message="*Debe de Seleccionar su Sexo")
	@Column(name="usu_sexo")
	private String sexo;
	
	@DecimalMin(value = "0.26", inclusive = false, message = "La altura debe ser mayor que 25cm.")
	@Max(value=3,message="Debe ser menor o igual 3mt")
	@Column(name="usu_estatura")
	private double estatura;
	
	@Column(name="usu_admin")
	private boolean admin;
	
	@Column(name="usu_estado")
	private boolean estado;
	
	@OneToMany(mappedBy = "usuario")
	private List<IndiceMasaCorporal> imcs;
	
	@OneToMany(mappedBy = "usuario")
	private List<Testimonio> testimonios;
	
	public Usuario(){
		// TODO Auto-generated constructor stub
	}
	/**
     * Constructor de la entidad Usuario que recibe todos los parámetros.
     * @param id: el identificador del usuario.
     * @param nombre: nombre del usuario.
     * @param apellido: el apellido del usuario.
     * @param email: el email del usuario.
     * @param fechaNacimiento: la fecha de nacimiento del usuario.
     * @param sexo: el sexo del usuario.
     * @param estatura: la estatura del usuario.
     * @param admin: administracion del usuario
     * @param estado: el estado del usuario.
     */
	public Usuario(Long id, String nombre, String apellido, String email, LocalDate fechaNacimiento, String telefono, String sexo, Double estatura,boolean admin, boolean estado){
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.sexo = sexo;
		this.estatura = estatura;
		this.admin = admin;
		this.estado = estado;
	}

	/**
     * Obtiene el id del usuario.
     * @return el id del usuario.
     */
	public Long getId() {
		return id;
	}

	/**
     * Establece el identificador del usuario.
     * @param id: identificador del usuario.
     */
	public void setId(Long id) {
		this.id = id;
	}

	/**
     * Obtiene el nombre del usuario.
     * @return el nombre del usuario.
     */
	public String getNombre() {
		return nombre;
	}

	/**
     * Establece el nombre del usuario.
     * @param nombre: el nombre del usuario.
     */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
     * Obtiene el apellido del usuario.
     * @return el apellido del usuario.
     */
	public String getApellido() {
		return apellido;
	}


	/**
     * Establece el apellido del usuario.
     * @param apellido: el apellido del usuario.
     */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	/**
     * Obtiene el email del usuario.
     * @return el email del usuario.
     */
	public String getEmail() {
		return email;
	}


	/**
     * Establece email del usuario.
     * @param email: el email del usuario.
     */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
     * Obtiene la fecha de nacimiento del usuario.
     * @return la fecha de nacimiento del usuario.
     */
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
     * Establece la Fecha de Nacimiento del usuario.
     * @param fechaNac: la Fecha de Nacimiento del usuario.
     */
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	/**
     * Obtiene el telefono del usuario.
     * @return el telefono del usuario.
     */
	public String getTelefono() {
		return telefono;
	}


	/**
     * Establece el telefono del usuario.
     * @param telefono: el telefono ingresado por el usuario.
     */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	/**
     * Obtiene el sexo del usuario.
     * @return el sexo del usuario.
     */
	public String getSexo() {
		return sexo;
	}


	/**
     * Establece el sexo del usuario.
     * @param sexo: el sexo ingresado por el usuario.
     */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	/**
     * Obtiene la estatura del usuario.
     * @return la estatura del usuario.
     */
	public Double getEstatura() {
		return estatura;
	}

	/**
     * Establece la estatura del usuario.
     * @param estatura: la estatura ingresado por el usuario.
     */
	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}
	/**
     * Verifica si un usuario es admin.
     * @return admin, si el usuario es admin o no.
     */
	public boolean isAdmin() {
		return admin;
	}

	/**
     * Establece si un usuario es administrador.
     * @param admin: admin del usuario.
     */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	/**
     * Verifica el estado del usuario.
     * @return el estado, si el usuario está activo o inactivo.
     */
	public boolean isEstado() {
		return estado;
	}

	/**
     * Establece el estado del usuario.
     * @param estado: estado del usuario.
     */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public List<IndiceMasaCorporal> getImcs() {
		return imcs;
	}

	public void setImcs(List<IndiceMasaCorporal> imcs) {
		this.imcs = imcs;
	}

	public List<Testimonio> getTestimonios() {
		return testimonios;
	}

	public void setTestimonios(List<Testimonio> testimonios) {
		this.testimonios = testimonios;
	}


	/**
     * Método que determina la cantidad de años que tiene una persona según su fecha
     * de nacimiento.
     * 
     * @return int
     */
	public int getEdad(){
		Period tiempo = Period.between(this.fechaNacimiento, LocalDate.now());
        return tiempo.getYears();
	}

	
	/**
	 * Método que calcula el peso ideal basado en la edad y el estatura.
	 * 
	 * @return double que es el peso ideal calculado.
	 */
	public double calcularPesoIdeal(){
		double estaturaCentimetro = this.estatura * 100;
		int edad = this.getEdad();
		return estaturaCentimetro - 100 +((edad/10)*0.9);
	}
}
