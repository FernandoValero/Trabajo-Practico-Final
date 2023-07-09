package ar.edu.unju.fi.entity;

import java.util.List;

import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "ingredientes")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    @NotEmpty(message = "*Debe agregar el nombre")
    @Size(min = 4, message = "*Nombre muy corto")
    private String nombre;

    @ManyToMany(mappedBy = "ingredientes")
    private List<Receta> recetas;
    
    @Column(name="estado_ingrediente")
	private boolean estado;

    public Ingrediente() {
    }

    public Ingrediente(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long get() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
	 * Permite la salida del estado
	 * @return retorna el estado del ingrediente
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
