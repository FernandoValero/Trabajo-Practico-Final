package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Ingrediente;
import jakarta.validation.Valid;

public interface IIngredienteService {

	/**
	 *Devuelve la lista de Ingredientes
	 */
	List<Ingrediente> getListaIngrediente();
	
	/**
	 *Metodo que permite guardar un ingrediente
	 *@param receta, valor a guardar
	 */
	void guardar (@Valid Ingrediente ingrediente);
	
	/**
	 *Metodo para buscar un ingrediente que coincida con el id por parametro
	 *@param id, ingrediente a buscar
	 */
	Ingrediente getBy(Long id);
	
	
	/**
	 *Metodo que permite eliminar un Ingrediente
	 * *@param ingredienteEncontrado, ingrediente a eliminar
	 */
	void eliminar (Ingrediente ingredienteEncontrado);
	
	/**
	 *Devuelve la instancia de un ingrediente
	 */
	Ingrediente getIngrediente();

}
