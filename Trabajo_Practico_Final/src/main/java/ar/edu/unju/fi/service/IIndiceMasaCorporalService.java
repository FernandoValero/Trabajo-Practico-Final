package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.IndiceMasaCorporal;
import ar.edu.unju.fi.entity.Usuario;

public interface IIndiceMasaCorporalService {
    
    /**
     * Método que retorna una lista de objetod de tipo IndiceMasaCorporal.
     * 
     * @return una lista de objetos del tipo IndiceMasaCorporal.
     */
    List<IndiceMasaCorporal> getListaImc();


	/**
     * Método que genera una lista de IndiceMasaCorporal según su usuario y su estado.
     * 
     * @param usuario Objeto del tipo Usuario.
     * @param estado valor del tipo boolean que representa el estado del imc
     * @return retorna una lista de objetos del tipo IndiceMasaCorporal.
     */
    List<IndiceMasaCorporal> getListaByUsuarioAndEstado(Usuario usuario, boolean estado);
	
    /**
     * Método que busca un indice de masa corporal según su Id.
     * 
     * @param id del tipo Long 
     * @return retorna un objeto del tipo IndiceMasaCorporal.
     */
    IndiceMasaCorporal getImcById(Long id);
	
    /**
     * Método que guarda un objeto del tipo IndiceMasaCorporal.
     * 
     * @param imc del tipo IndiceMasaCorporal.
     */
    void guardarImc(IndiceMasaCorporal imc);
	
    /**
     * Método que elimina un objeto de tipo IndiceMasaCorporal, según su Id.
     * 
     * @param id del tipo Long que representa el Id del objeto a eliminar.
     */    
    void eliminarById(Long id);
	
    /**
     * Método que retorna un objeto del tipo IndiceMasaCorporal.
     * 
     * @return un objeto del tipo IndiceMasaCorporal.
     */
    IndiceMasaCorporal getImc();
}
