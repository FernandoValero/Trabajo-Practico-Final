package ar.edu.unju.fi.entity;

import org.springframework.stereotype.Component;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
@Entity
@Table(name = "ingredientes")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingrediente")
    private Long id_ingrediente;

    @Column(name = "nombre")
    @NotEmpty(message = "*Debe agregar el nombre")
    @Size(min = 4, message = "*Nombre muy corto")
    private String nombre;

    @Column(name = "id_receta")
    private Long id_receta;

    public Ingrediente() {
    }

    public Ingrediente(Long id_ingrediente, String nombre, Long id_receta) {
        this.id_ingrediente = id_ingrediente;
        this.nombre = nombre;
        this.id_receta = id_receta;
    }

    public Long getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(Long id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId_receta() {
        return id_receta;
    }

    public void setId_receta(Long id_receta) {
        this.id_receta = id_receta;
    }
}
