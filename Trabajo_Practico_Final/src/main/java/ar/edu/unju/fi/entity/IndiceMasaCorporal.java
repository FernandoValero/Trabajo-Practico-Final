package ar.edu.unju.fi.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

/**
 * Representa un Indice de Masa Corporal
 */
@Component
@Entity
@Table(name = "mediciones_imc")
public class IndiceMasaCorporal {
    /**
     * Representa un identificador único para un IMC .
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imc_id")
	private Long id;
    
    /**
     * Representa la fecha que se crea el IMC.
     */
    @Column(name = "imc_fecha")
    private LocalDate fechaIMC;
    
    /**
     * Representa el estado de IMC. 
     * True si esta activo.
     * False si es eliminado.
     */
    @Column(name = "imc_estado")
    private boolean estado;
    
    /**
     * Representa a un usuario.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
    /**
     * Representa el peso del usuario.
     */
    @Column(name = "imc_peso")
    @NotEmpty(message = "Debe ingresar un valor")
    @Min(value = 1, message = "el valor ingresado tiene que ser mayor que 0")
    private double peso;


    /**
     * Constructor por defecto
     */
    public IndiceMasaCorporal() {
    }

    
    /**
     * Constructor parametrizado
     * @param id de tipo Long
     * @param fechaIMC de tipo LocalDate
     * @param estado de tipo boolean
     * @param usuario de tipo Usuario
     * @param peso de tipo double.
     */
    public IndiceMasaCorporal(Long id, LocalDate fechaIMC, boolean estado, Usuario usuario, double peso) {
        this.id = id;
        this.fechaIMC = fechaIMC;
        this.estado = estado;
        this.usuario = usuario;
        this.peso = peso;
    }

    
    
    /** 
     * Obtiene el id del imc.
     * @return Long que es el valor del id.
     */
    public Long getId() {
        return this.id;
    }

    
    /**
     * Establece el valor del id de imc.
     * 
     * @param id parametro de tipo Long.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha del imc.
     * @return un valor de tipo LocalDate.
     */
    public LocalDate getFechaIMC() {
        return this.fechaIMC;
    }

    /**
     * Establece la fecha del imc.
     * @param fechaIMC parametro de tipo LocalDate.
     */
    public void setFechaIMC(LocalDate fechaIMC) {
        this.fechaIMC = fechaIMC;
    }

    /**
     * Obtiene el estado del imc.
     * @return true o false.
     */
    public boolean isEstado() {
        return this.estado;
    }

    /**
     * Obtiene el estado del imc.
     * @return true o false.
     */
    public boolean getEstado() {
        return this.estado;
    }

    /**
     * Establece el estado del imc.
     * @param estado parametro de tipo boolean.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el usuario del imc.
     * @return un objeto del tipo Usuario.
     */
    public Usuario getUsuario() {
        return this.usuario;
    }

    /**
     * Establece un objeto del tipo Usuario a imc.
     * @param usuario parametro de tipo Usuario
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el peso del imc.
     * @return el peso del tipo double.
     */
    public double getPeso() {
        return this.peso;
    }

    /**
     * Establece el peso del imc.
     * @param peso parametro del tipo double.
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Método que calcula el IMC de una persona según su peso y su altura.
     * el peso tiene que ser en [Kg].
     * la estatua tiene que ser en [m].
     * @return un mensaje con el resultado del calculo.
     */
    public String calcularIMC() {
        double imc = this.peso / Math.pow(this.usuario.getEstatura(), 2);
        if (imc < 18.5) {
            return "IMC: " + imc + " Está por debajo de su peso normal.";
        } else {
            if (imc >= 18.5 && imc <= 25) {
                return "IMC: " + imc + " Está en su peso normal.";
            } else {
                return "Tiene sobrepeso";
            }
        }
    }

}
